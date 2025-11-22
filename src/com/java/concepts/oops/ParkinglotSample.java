package com.java.concepts.oops;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

enum VehicleType {
    CAR,
    BIKE,
    TRUCK
}


enum ParkingSlotStatus {
    AVAILABLE,
    OCCUPIED
}


enum PaymentMethod {
    CASH,
    UPI
}


interface IChargesService {
    double calculateCharges(VehicleType vehicleType, LocalDateTime entryTime, LocalDateTime exitTime);
}


class ChargesServiceImpl implements IChargesService {
    private static final Map<VehicleType, Double> rates = new HashMap<>();

    static {
        rates.put(VehicleType.CAR, 10.0);
        rates.put(VehicleType.BIKE, 5.0);
        rates.put(VehicleType.TRUCK, 15.0);
    }

    @Override
    public double calculateCharges(VehicleType vehicleType, LocalDateTime entryTime, LocalDateTime exitTime) {
        Duration duration = Duration.between(entryTime, exitTime);
        long hours = duration.toHours();
        if (duration.toMinutes() % 60 != 0) {
            hours += 1;
        }
        return hours * rates.getOrDefault(vehicleType, 10.0);
    }
}

interface PaymentStrategy {
    void processPayment(double amount);
}


class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing cash payment of $" + amount);

    }
}


class UpiPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing UPI payment of $" + amount);

    }
}


class DatabaseConnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5555/postgres?currentSchema=parkinglot";
    private static final String USER = "postgres";
    private static final String PASS = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}

// Vehicle Class
class Vehicle {
    private UUID vehicleId;
    private String licensePlateNumber;
    private VehicleType vehicleType;

    public Vehicle(String licensePlateNumber, VehicleType vehicleType) {
        this.vehicleId = UUID.randomUUID();
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleType = vehicleType;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}

// ParkingSpot Class
class ParkingSpot {
    private String spotId;
    private ParkingSlotStatus status;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, ParkingSlotStatus status) {
        this.spotId = spotId;
        this.status = status;
    }

    public String getSpotId() {
        return spotId;
    }

    public ParkingSlotStatus getStatus() {
        return status;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.status = ParkingSlotStatus.OCCUPIED;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.status = ParkingSlotStatus.AVAILABLE;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}


interface IRepository<T> {
    void save(T entity) throws SQLException;
    T findById(UUID id) throws SQLException;
    void update(T entity) throws SQLException;
}


class ParkingSpotRepository {
    public ParkingSpot findAvailableSpot() throws SQLException {
        String sql = "SELECT * FROM ParkingSpots WHERE status = 'AVAILABLE' LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String spotId = rs.getString("spot_id");
                ParkingSlotStatus status = ParkingSlotStatus.valueOf(rs.getString("status"));
                return new ParkingSpot(spotId, status);
            }
        }
        return null;
    }

    public void updateSpot(ParkingSpot spot) throws SQLException {
        String sql = "UPDATE ParkingSpots SET status = ? WHERE spot_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, spot.getStatus().name());
            pstmt.setString(2, spot.getSpotId());
            pstmt.executeUpdate();
        }
    }
}

interface ParkingSpotStrategy {
    Optional<ParkingSpot> findSpot();
}

class NearestParkingSpotStrategy implements ParkingSpotStrategy {
    private ParkingSpotRepository parkingSpotRepository;

    public NearestParkingSpotStrategy(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Override
    public Optional<ParkingSpot> findSpot() {
        try {
            return Optional.ofNullable(parkingSpotRepository.findAvailableSpot());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding nearest available spot", e);
        }
    }
}

class Ticket {
    private UUID ticketId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String parkingSpotId;
    private Vehicle vehicle;
    private double parkingCharge;

    public Ticket(String parkingSpotId, Vehicle vehicle) {
        this.ticketId = UUID.randomUUID();
        this.entryTime = LocalDateTime.now();
        this.parkingSpotId = parkingSpotId;
        this.vehicle = vehicle;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getParkingCharge() {
        return parkingCharge;
    }

    public void setParkingCharge(double parkingCharge) {
        this.parkingCharge = parkingCharge;
    }
}

class TicketRepository implements IRepository<Ticket> {
    @Override
    public void save(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO Tickets (ticket_id, entry_time, parking_spot_id, vehicle_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, ticket.getTicketId());
            pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(ticket.getEntryTime()));
            pstmt.setString(3, ticket.getParkingSpotId());
            pstmt.setObject(4, ticket.getVehicle().getVehicleId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Ticket findById(UUID ticketId) throws SQLException {
        String sql = "SELECT * FROM Tickets WHERE ticket_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, ticketId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                LocalDateTime entryTime = rs.getTimestamp("entry_time").toLocalDateTime();
                String parkingSpotId = rs.getString("parking_spot_id");
                UUID vehicleId = UUID.fromString(rs.getString("vehicle_id"));
                Vehicle vehicle = new VehicleRepository().findById(vehicleId);
                Ticket ticket = new Ticket(parkingSpotId, vehicle);
                ticket.setExitTime(entryTime);
                return ticket;
            }
        }
        return null;
    }

    @Override
    public void update(Ticket ticket) throws SQLException {
        String sql = "UPDATE Tickets SET exit_time = ?, parking_charge = ? WHERE ticket_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(ticket.getExitTime()));
            pstmt.setDouble(2, ticket.getParkingCharge());
            pstmt.setObject(3, ticket.getTicketId());
            pstmt.executeUpdate();
        }
    }
}


interface ITicketService {
    Ticket createTicket(String parkingSpotId, Vehicle vehicle) throws SQLException;
    void closeTicket(Ticket ticket, PaymentMethod paymentMethod) throws SQLException;
    Ticket getTicketById(UUID ticketId) throws SQLException;
}


class ITicketServiceImpl implements ITicketService {
    private TicketRepository ticketRepository;
    private IChargesService chargesService;

    public ITicketServiceImpl(TicketRepository ticketRepository, IChargesService chargesService) {
        this.ticketRepository = ticketRepository;
        this.chargesService = chargesService;
    }

    @Override
    public Ticket createTicket(String parkingSpotId, Vehicle vehicle) throws SQLException {
        Ticket ticket = new Ticket(parkingSpotId, vehicle);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public void closeTicket(Ticket ticket, PaymentMethod paymentMethod) throws SQLException {
        ticket.setExitTime(LocalDateTime.now());
        double charge = chargesService.calculateCharges(
                ticket.getVehicle().getVehicleType(),
                ticket.getEntryTime(),
                ticket.getExitTime()
        );
        ticket.setParkingCharge(charge);
        ticketRepository.update(ticket);

        PaymentStrategy paymentStrategy = getPaymentStrategy(paymentMethod);
        paymentStrategy.processPayment(charge);
    }

    private PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case CASH:
                return new CashPaymentStrategy();
            case UPI:
                return new UpiPaymentStrategy();
            default:
                throw new IllegalArgumentException("Invalid payment method");
        }
    }

    @Override
    public Ticket getTicketById(UUID ticketId) throws SQLException {
        return ticketRepository.findById(ticketId);
    }
}


class ParkingLot {
    private static ParkingLot instance = null;
    private ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepository();
    private ITicketService ticketService;

    private ParkingLot() throws SQLException {
        this.ticketService = new ITicketServiceImpl(new TicketRepository(), new ChargesServiceImpl());
    }

    public static synchronized ParkingLot getInstance() throws SQLException {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public Ticket assignParkingSpot(Vehicle vehicle, ParkingSpotStrategy strategy) throws SQLException {
        Optional<ParkingSpot> spotOptional = strategy.findSpot();
        if (!spotOptional.isPresent()) {
            throw new RuntimeException("No parking spots available");
        }
        ParkingSpot spot = spotOptional.get();
        spot.parkVehicle(vehicle);
        parkingSpotRepository.updateSpot(spot);
        return ticketService.createTicket(spot.getSpotId(), vehicle);
    }

    public void releaseParkingSpot(Ticket ticket, PaymentMethod paymentMethod) throws SQLException {
        ParkingSpot spot = parkingSpotRepository.findAvailableSpot(); // Adjusted for simplicity
        if (spot != null) {
            spot.removeVehicle();
            parkingSpotRepository.updateSpot(spot);
            ticketService.closeTicket(ticket, paymentMethod);
        }
    }
}

class VehicleRepository implements IRepository<Vehicle> {
    @Override
    public void save(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO Vehicles (vehicle_id, license_plate_number, vehicle_type) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, vehicle.getVehicleId());
            pstmt.setString(2, vehicle.getLicensePlateNumber());
            pstmt.setString(3, vehicle.getVehicleType().name());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Vehicle findById(UUID vehicleId) throws SQLException {
        String sql = "SELECT * FROM Vehicles WHERE vehicle_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, vehicleId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String licensePlate = rs.getString("license_plate_number");
                VehicleType vehicleType = VehicleType.valueOf(rs.getString("vehicle_type"));
                return new Vehicle(licensePlate, vehicleType);
            }
        }
        return null;
    }

    @Override
    public void update(Vehicle entity) throws SQLException {}
}



public class ParkinglotSample {

    public static void main(String[] args) {
        try {
            ParkingLot parkingLot = ParkingLot.getInstance();
            Vehicle vehicle = new Vehicle("ABC123", VehicleType.CAR);
            Ticket ticket = parkingLot.assignParkingSpot(vehicle, new NearestParkingSpotStrategy(new ParkingSpotRepository()));
            System.out.println("Ticket issued: " + ticket.getTicketId());

            parkingLot.releaseParkingSpot(ticket, PaymentMethod.CASH);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

