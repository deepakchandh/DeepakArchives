package com.java.backtracking;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * BikeRental.java
 *
 * Single-file JDBC-based Bike Rental application.
 *
 * - public class BikeRental contains ONLY the main method (psvm).
 * - All other classes are package-private top-level classes in this file.
 *
 * Before running:
 * - Ensure a PostgreSQL instance is reachable and credentials below are correct,
 *   or change DB_URL/DB_USER/DB_PASS to match your DB.
 *
 * This file creates the schema on startup if it doesn't exist.
 *
 * Production notes (for later):
 * - Replace System.out with a logging framework (SLF4J).
 * - Replace direct JDBC with a proper DAO framework (JPA/MyBatis) for maintainability.
 * - Add migration tool (Flyway) instead of in-code schema creation.
 */
public class BikeRental {
    public static void main(String[] args) {
        // DB config - adjust for your environment
        final String DB_URL = "jdbc:postgresql://localhost:5432/bikerental";
        final String DB_USER = "postgres";
        final String DB_PASS = "password";

        Database db = new Database(DB_URL, DB_USER, DB_PASS);

        try {
            db.ensureSchema();
        } catch (SQLException e) {
            System.err.println("Failed to ensure DB schema: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        ProductRepository productRepo = new ProductRepository(db);
        CustomerRepository customerRepo = new CustomerRepository(db);
        RentalRepository rentalRepo = new RentalRepository(db);

        // Supply rates from main (caller)
        Map<ProductType, BigDecimal> rates = new EnumMap<>(ProductType.class);
        rates.put(ProductType.BIKE, BigDecimal.valueOf(5.0));      // per hour
        rates.put(ProductType.SCOOTER, BigDecimal.valueOf(8.0));   // per hour
        BigDecimal defaultRate = BigDecimal.valueOf(10.0);

        IChargesService chargesService = new ChargesServiceImpl(rates, defaultRate);

        // Payment strategies
        Map<PaymentMethod, PaymentStrategy> paymentStrategies = new EnumMap<>(PaymentMethod.class);
        paymentStrategies.put(PaymentMethod.CASH, new CashPaymentStrategy(db));
        paymentStrategies.put(PaymentMethod.UPI, new UpiPaymentStrategy(db));

        RentalService rentalService = new RentalService(db, productRepo, customerRepo, rentalRepo, chargesService, paymentStrategies);

        try {
            // Seed sample data (id values populated by DB)
            Bike bike1 = new Bike("BIKE-SKU-001", "Trek FX", BigDecimal.valueOf(5.0), BikeSize.MEDIUM);
            Bike bike2 = new Bike("BIKE-SKU-002", "Giant Escape", BigDecimal.valueOf(5.0), BikeSize.SMALL);
            Scooter scooter1 = new Scooter("SCT-SKU-001", "Xiaomi M365", BigDecimal.valueOf(8.0), ScooterMotor.ELECTRIC);

            productRepo.insertProduct(bike1);
            productRepo.insertProduct(bike2);
            productRepo.insertProduct(scooter1);

            Customer cust = new Customer("Alice", "alice@example.com", "9999999999");
            customerRepo.insert(cust);

            // Rent a small bike to customer
            long productToRent = bike2.id; // small bike
            long customerId = cust.id;

            System.out.println("Attempting to rent productId=" + productToRent + " to customerId=" + customerId);
            Rental rental = rentalService.rentProduct(productToRent, customerId, 1); // 1 day

            // Return immediately (demo)
            BigDecimal charge = rentalService.returnProduct(rental.id, PaymentMethod.CASH);

            System.out.println("Final charge for rental " + rental.id + " = " + charge);

            // List available products
            List<Product> avail = productRepo.findAvailable();
            System.out.println("Available products after return:");
            for (Product p : avail) {
                System.out.println(" - id=" + p.id + " sku=" + p.sku + " name=" + p.name + " available=" + p.available);
            }

            // Get customer balance
            Optional<Customer> c2 = customerRepo.findById(customerId);
            c2.ifPresent(custNow -> System.out.println("Customer balance: " + custNow.balance));

        } catch (SQLException ex) {
            System.err.println("DB error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

/* =========================
   DB Helper
   ========================= */
class Database {
    private final String url;
    private final String user;
    private final String pass;

    Database(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    void ensureSchema() throws SQLException {
        String createProducts =
                "CREATE TABLE IF NOT EXISTS products (\n" +
                        "  id BIGSERIAL PRIMARY KEY,\n" +
                        "  sku VARCHAR(64) UNIQUE NOT NULL,\n" +
                        "  name TEXT NOT NULL,\n" +
                        "  product_type VARCHAR(20) NOT NULL,\n" +
                        "  rental_rate NUMERIC(10,2) NOT NULL DEFAULT 0,\n" +
                        "  available BOOLEAN NOT NULL DEFAULT true,\n" +
                        "  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()\n" +
                        ");";

        String createBikes =
                "CREATE TABLE IF NOT EXISTS bikes (\n" +
                        "  product_id BIGINT PRIMARY KEY REFERENCES products(id) ON DELETE CASCADE,\n" +
                        "  size VARCHAR(10) NOT NULL\n" +
                        ");";

        String createScooters =
                "CREATE TABLE IF NOT EXISTS scooters (\n" +
                        "  product_id BIGINT PRIMARY KEY REFERENCES products(id) ON DELETE CASCADE,\n" +
                        "  motor_type VARCHAR(20) NOT NULL\n" +
                        ");";

        String createCustomers =
                "CREATE TABLE IF NOT EXISTS customers (\n" +
                        "  id BIGSERIAL PRIMARY KEY,\n" +
                        "  name TEXT NOT NULL,\n" +
                        "  email TEXT,\n" +
                        "  phone VARCHAR(20),\n" +
                        "  balance NUMERIC(12,2) NOT NULL DEFAULT 0,\n" +
                        "  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()\n" +
                        ");";

        String createRentals =
                "CREATE TABLE IF NOT EXISTS rentals (\n" +
                        "  id BIGSERIAL PRIMARY KEY,\n" +
                        "  product_id BIGINT NOT NULL REFERENCES products(id),\n" +
                        "  customer_id BIGINT NOT NULL REFERENCES customers(id),\n" +
                        "  start_at TIMESTAMP WITH TIME ZONE NOT NULL,\n" +
                        "  due_at TIMESTAMP WITH TIME ZONE NOT NULL,\n" +
                        "  returned_at TIMESTAMP WITH TIME ZONE,\n" +
                        "  charge NUMERIC(10,2) DEFAULT 0,\n" +
                        "  status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',\n" +
                        "  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()\n" +
                        ");";

        try (Connection c = getConnection();
             Statement s = c.createStatement()) {
            s.execute(createProducts);
            s.execute(createBikes);
            s.execute(createScooters);
            s.execute(createCustomers);
            s.execute(createRentals);
        }
    }
}

/* =========================
   Enums & Interfaces
   ========================= */
enum ProductType { BIKE, SCOOTER }
enum BikeSize { SMALL, MEDIUM, LARGE }
enum ScooterMotor { ELECTRIC, GAS }
enum PaymentMethod { CASH, UPI }

interface PaymentStrategy {
    void processPayment(long customerId, BigDecimal amount) throws SQLException;
}

interface IChargesService {
    BigDecimal calculateCharges(ProductType type, Instant start, Instant end);
}

/* =========================
   Domain classes
   ========================= */
abstract class Product {
    long id;
    String sku;
    String name;
    BigDecimal rentalRate;
    boolean available;

    Product(String sku, String name, BigDecimal rentalRate) {
        this.sku = sku;
        this.name = name;
        this.rentalRate = rentalRate;
        this.available = true;
    }
}

class Bike extends Product {
    BikeSize size;
    Bike(String sku, String name, BigDecimal rentalRate, BikeSize size) {
        super(sku, name, rentalRate);
        this.size = size;
    }
}

class Scooter extends Product {
    ScooterMotor motorType;
    Scooter(String sku, String name, BigDecimal rentalRate, ScooterMotor motorType) {
        super(sku, name, rentalRate);
        this.motorType = motorType;
    }
}

class Customer {
    long id;
    String name;
    String email;
    String phone;
    BigDecimal balance;

    Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = BigDecimal.ZERO;
    }
}

class Rental {
    long id;
    long productId;
    long customerId;
    Instant startAt;
    Instant dueAt;
    Instant returnedAt;
    BigDecimal charge;
    String status;
}

/* =========================
   Repositories (JDBC)
   ========================= */
class ProductRepository {
    private final Database db;

    ProductRepository(Database db) { this.db = db; }

    Product insertProduct(Product p) throws SQLException {
        String insertProductSQL = "INSERT INTO products (sku, name, product_type, rental_rate, available) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertProductSQL)) {
            ps.setString(1, p.sku);
            ps.setString(2, p.name);
            ps.setString(3, p instanceof Bike ? "BIKE" : "SCOOTER");
            ps.setBigDecimal(4, p.rentalRate);
            ps.setBoolean(5, p.available);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p.id = rs.getLong(1);
                } else {
                    throw new SQLException("Failed to insert product");
                }
            }
        }

        if (p instanceof Bike) {
            String sql = "INSERT INTO bikes (product_id, size) VALUES (?, ?)";
            try (Connection conn = db.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, p.id);
                ps.setString(2, ((Bike) p).size.name());
                ps.executeUpdate();
            }
        } else if (p instanceof Scooter) {
            String sql = "INSERT INTO scooters (product_id, motor_type) VALUES (?, ?)";
            try (Connection conn = db.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, p.id);
                ps.setString(2, ((Scooter) p).motorType.name());
                ps.executeUpdate();
            }
        }
        return p;
    }

    Optional<Product> findById(long id) throws SQLException {
        String sql = "SELECT id, sku, name, product_type, rental_rate, available FROM products WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String type = rs.getString("product_type");
                    Product p;
                    if ("BIKE".equalsIgnoreCase(type)) {
                        Bike b = new Bike(rs.getString("sku"), rs.getString("name"), rs.getBigDecimal("rental_rate"), BikeSize.MEDIUM);
                        b.id = rs.getLong("id");
                        b.available = rs.getBoolean("available");
                        // load bike-specific fields
                        try (PreparedStatement ps2 = db.getConnection().prepareStatement("SELECT size FROM bikes WHERE product_id = ?")) {
                            ps2.setLong(1, id);
                            try (ResultSet rs2 = ps2.executeQuery()) {
                                if (rs2.next()) {
                                    b.size = BikeSize.valueOf(rs2.getString("size"));
                                }
                            }
                        }
                        p = b;
                    } else {
                        Scooter s = new Scooter(rs.getString("sku"), rs.getString("name"), rs.getBigDecimal("rental_rate"), ScooterMotor.ELECTRIC);
                        s.id = rs.getLong("id");
                        s.available = rs.getBoolean("available");
                        try (PreparedStatement ps2 = db.getConnection().prepareStatement("SELECT motor_type FROM scooters WHERE product_id = ?")) {
                            ps2.setLong(1, id);
                            try (ResultSet rs2 = ps2.executeQuery()) {
                                if (rs2.next()) {
                                    s.motorType = ScooterMotor.valueOf(rs2.getString("motor_type"));
                                }
                            }
                        }
                        p = s;
                    }
                    return Optional.of(p);
                }
            }
        }
        return Optional.empty();
    }

    List<Product> findAvailable() throws SQLException {
        String sql = "SELECT id FROM products WHERE available = true";
        List<Product> out = new ArrayList<>();
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                findById(id).ifPresent(out::add);
            }
        }
        return out;
    }

    void setAvailability(long productId, boolean available) throws SQLException {
        String sql = "UPDATE products SET available = ? WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, available);
            ps.setLong(2, productId);
            ps.executeUpdate();
        }
    }
}

class CustomerRepository {
    private final Database db;
    CustomerRepository(Database db) { this.db = db; }

    Customer insert(Customer c) throws SQLException {
        String sql = "INSERT INTO customers (name, email, phone, balance) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.name);
            ps.setString(2, c.email);
            ps.setString(3, c.phone);
            ps.setBigDecimal(4, c.balance);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) c.id = rs.getLong(1);
                else throw new SQLException("Failed to insert customer");
            }
        }
        return c;
    }

    Optional<Customer> findById(long id) throws SQLException {
        String sql = "SELECT id, name, email, phone, balance FROM customers WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer(rs.getString("name"), rs.getString("email"), rs.getString("phone"));
                    c.id = rs.getLong("id");
                    c.balance = rs.getBigDecimal("balance");
                    return Optional.of(c);
                }
            }
        }
        return Optional.empty();
    }

    void adjustBalance(long customerId, BigDecimal delta) throws SQLException {
        String sql = "UPDATE customers SET balance = balance + ? WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, delta);
            ps.setLong(2, customerId);
            ps.executeUpdate();
        }
    }
}

class RentalRepository {
    private final Database db;
    RentalRepository(Database db) { this.db = db; }

    Rental insert(Rental r, Connection txConn) throws SQLException {
        String sql = "INSERT INTO rentals (product_id, customer_id, start_at, due_at, status) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement ps = txConn.prepareStatement(sql)) {
            ps.setLong(1, r.productId);
            ps.setLong(2, r.customerId);
            ps.setTimestamp(3, Timestamp.from(r.startAt));
            ps.setTimestamp(4, Timestamp.from(r.dueAt));
            ps.setString(5, r.status);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) r.id = rs.getLong(1);
                else throw new SQLException("Failed to insert rental");
            }
        }
        return r;
    }

    Optional<Rental> findById(long rentalId) throws SQLException {
        String sql = "SELECT id, product_id, customer_id, start_at, due_at, returned_at, charge, status FROM rentals WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, rentalId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Rental r = new Rental();
                    r.id = rs.getLong("id");
                    r.productId = rs.getLong("product_id");
                    r.customerId = rs.getLong("customer_id");
                    r.startAt = rs.getTimestamp("start_at").toInstant();
                    r.dueAt = rs.getTimestamp("due_at").toInstant();
                    Timestamp rt = rs.getTimestamp("returned_at");
                    if (rt != null) r.returnedAt = rt.toInstant();
                    r.charge = rs.getBigDecimal("charge");
                    r.status = rs.getString("status");
                    return Optional.of(r);
                }
            }
        }
        return Optional.empty();
    }

    void updateReturn(long rentalId, Instant returnedAt, BigDecimal charge, Connection txConn) throws SQLException {
        String sql = "UPDATE rentals SET returned_at = ?, charge = ?, status = ? WHERE id = ?";
        try (PreparedStatement ps = txConn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.from(returnedAt));
            ps.setBigDecimal(2, charge);
            ps.setString(3, "RETURNED");
            ps.setLong(4, rentalId);
            ps.executeUpdate();
        }
    }
}

/* =========================
   ChargesService (configurable rates)
   ========================= */
class ChargesServiceImpl implements IChargesService {
    private final Map<ProductType, BigDecimal> rates;
    private final BigDecimal defaultRate;

    ChargesServiceImpl(Map<ProductType, BigDecimal> rates, BigDecimal defaultRate) {
        this.rates = new EnumMap<>(ProductType.class);
        this.rates.putAll(rates);
        this.defaultRate = defaultRate;
    }

    @Override
    public BigDecimal calculateCharges(ProductType type, Instant start, Instant end) {
        if (end.isBefore(start)) throw new IllegalArgumentException("Exit before entry");
        long minutes = Duration.between(start, end).toMinutes();
        long hours = (long) Math.ceil(minutes / 60.0);
        BigDecimal rate = rates.getOrDefault(type, defaultRate);
        return rate.multiply(BigDecimal.valueOf(hours));
    }
}

/* =========================
   Payment strategies
   ========================= */
class CashPaymentStrategy implements PaymentStrategy {
    private final Database db;
    CashPaymentStrategy(Database db) { this.db = db; }

    @Override
    public void processPayment(long customerId, BigDecimal amount) throws SQLException {
        System.out.println("[Payment] Cash processed for customer " + customerId + ": " + amount);
        // Real implementation: record payment receipts, audit logs, possibly update external systems
    }
}

class UpiPaymentStrategy implements PaymentStrategy {
    private final Database db;
    UpiPaymentStrategy(Database db) { this.db = db; }

    @Override
    public void processPayment(long customerId, BigDecimal amount) throws SQLException {
        System.out.println("[Payment] UPI processed for customer " + customerId + ": " + amount);
        // Real implementation: call payment gateway, handle callbacks, retries, etc.
    }
}

/* =========================
   Rental Service (transactional)
   ========================= */
class RentalService {
    private final Database db;
    private final ProductRepository productRepo;
    private final CustomerRepository customerRepo;
    private final RentalRepository rentalRepo;
    private final IChargesService chargesService;
    private final Map<PaymentMethod, PaymentStrategy> paymentStrategies;

    RentalService(Database db,
                  ProductRepository productRepo,
                  CustomerRepository customerRepo,
                  RentalRepository rentalRepo,
                  IChargesService chargesService,
                  Map<PaymentMethod, PaymentStrategy> paymentStrategies) {
        this.db = db;
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
        this.rentalRepo = rentalRepo;
        this.chargesService = chargesService;
        this.paymentStrategies = paymentStrategies;
    }

    /**
     * Rent a product:
     * - Validate availability
     * - Start DB transaction
     * - Set product.available = false (row lock)
     * - Create rental record
     * - Commit
     */
    public Rental rentProduct(long productId, long customerId, int days) throws SQLException {
        Product product = productRepo.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        if (!product.available) throw new IllegalStateException("Product not available");

        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        try (Connection tx = db.getConnection()) {
            tx.setAutoCommit(false);
            try {
                String lockSql = "SELECT available FROM products WHERE id = ? FOR UPDATE";
                try (PreparedStatement ps = tx.prepareStatement(lockSql)) {
                    ps.setLong(1, productId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            boolean available = rs.getBoolean("available");
                            if (!available) {
                                tx.rollback();
                                throw new IllegalStateException("Product not available (concurrent)");
                            }
                        } else {
                            tx.rollback();
                            throw new IllegalArgumentException("Product not found during lock");
                        }
                    }
                }

                String upd = "UPDATE products SET available = false WHERE id = ?";
                try (PreparedStatement ps = tx.prepareStatement(upd)) {
                    ps.setLong(1, productId);
                    ps.executeUpdate();
                }

                Rental rental = new Rental();
                rental.productId = productId;
                rental.customerId = customerId;
                rental.startAt = Instant.now();
                rental.dueAt = rental.startAt.plusSeconds(days * 24L * 3600L);
                rental.status = "ACTIVE";

                rentalRepo.insert(rental, tx);

                tx.commit();
                System.out.println("[Rental] Created rental id=" + rental.id + " for product=" + productId + " customer=" + customerId);
                return rental;
            } catch (Exception ex) {
                tx.rollback();
                throw ex;
            } finally {
                tx.setAutoCommit(true);
            }
        }
    }

    /**
     * Return a product:
     * - Get rental
     * - Start transaction
     * - Calculate charge
     * - Update rental (returned_at, charge, status)
     * - Set product.available = true
     * - Update customer balance
     * - Commit
     * - Process payment (outside transaction)
     */
    public BigDecimal returnProduct(long rentalId, PaymentMethod paymentMethod) throws SQLException {
        Rental rental = rentalRepo.findById(rentalId).orElseThrow(() -> new IllegalArgumentException("Rental not found"));
        if (!"ACTIVE".equalsIgnoreCase(rental.status)) throw new IllegalStateException("Rental is not active");

        Product product = productRepo.findById(rental.productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Instant now = Instant.now();
        BigDecimal charge = chargesService.calculateCharges(
                (product instanceof Bike) ? ProductType.BIKE : ProductType.SCOOTER,
                rental.startAt,
                now
        );

        try (Connection tx = db.getConnection()) {
            tx.setAutoCommit(false);
            try {
                rentalRepo.updateReturn(rentalId, now, charge, tx);

                String upd = "UPDATE products SET available = true WHERE id = ?";
                try (PreparedStatement ps = tx.prepareStatement(upd)) {
                    ps.setLong(1, rental.productId);
                    ps.executeUpdate();
                }

                String updBal = "UPDATE customers SET balance = balance + ? WHERE id = ?";
                try (PreparedStatement ps = tx.prepareStatement(updBal)) {
                    ps.setBigDecimal(1, charge);
                    ps.setLong(2, rental.customerId);
                    ps.executeUpdate();
                }

                tx.commit();
            } catch (Exception ex) {
                tx.rollback();
                throw ex;
            } finally {
                tx.setAutoCommit(true);
            }
        }

        PaymentStrategy strategy = paymentStrategies.get(paymentMethod);
        if (strategy == null) throw new IllegalArgumentException("Unsupported payment method");
        strategy.processPayment(rental.customerId, charge);

        System.out.println("[Return] Rental " + rentalId + " closed. Charge: " + charge);
        return charge;
    }
}

