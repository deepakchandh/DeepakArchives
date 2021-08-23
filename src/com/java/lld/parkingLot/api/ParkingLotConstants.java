//$Id$
package com.java.lld.parkingLot.api;

public class ParkingLotConstants {

	public enum VehicleType {
		CAR(1), TRUCK(2), ELECTRIC(3), VAN(4), MOTORBIKE(5);

		int id;
		VehicleType(int id) {
			this.id = id;
		}
		public int getId() {
			return this.id;
		}
	}

	public enum ParkingSpotType {
		HANDICAPPED(1), COMPACT(2), LARGE(3), MOTORBIKE(4), ELECTRIC(5);
		
		int id;
		ParkingSpotType(int id) {
			this.id = id;
		}
	}

	public enum AccountStatus {
		ACTIVE(1), BLOCKED(2), BANNED(3), COMPROMISED(4), ARCHIVED(5), UNKNOWN(6);
		int id;
		AccountStatus(int id) {
			this.id = id;
		}
	}

	public enum ParkingTicketStatus {
		ACTIVE(1),
		INACTIVE(2);

		int id;
		ParkingTicketStatus(int id) {
			this.id = id;
		}

		public int getId() {
			return this.id;
		}
	}

}
