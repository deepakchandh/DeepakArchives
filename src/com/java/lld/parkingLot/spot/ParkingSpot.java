//$Id$
package com.java.lld.parkingLot.spot;

import com.java.lld.parkingLot.api.ParkingLotConstants.ParkingSpotType;
import com.java.lld.parkingLot.api.ParkingLotConstants.VehicleType;

public abstract class ParkingSpot {

	private String number;
	private boolean free;
	private VehicleType vehicle;
	private final ParkingSpotType type;

	public abstract boolean IsFree();

	public ParkingSpot(ParkingSpotType type) {
		this.type = type;
	}

	public void assignVehicle(VehicleType vehicle) {
		this.vehicle = vehicle;
		free = false;
	}

	public void removeVehicle() {
		this.vehicle = null;
		free = true;
	}
}
