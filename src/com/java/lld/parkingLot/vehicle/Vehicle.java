//$Id$
package com.java.lld.parkingLot.vehicle;

import com.java.lld.parkingLot.api.ParkingLotConstants;
import com.java.lld.parkingLot.api.ParkingLotConstants.ParkingTicketStatus;
import com.java.lld.parkingLot.api.ParkingLotConstants.VehicleType;

public abstract class Vehicle {

	private String licenseNumber;
	private final VehicleType type;
	private ParkingTicketStatus ticket;

	public Vehicle(VehicleType type) {
		this.type = type;
	}
	public void assignTicket(ParkingTicketStatus ticket) {
		this.ticket = ticket;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}


}
