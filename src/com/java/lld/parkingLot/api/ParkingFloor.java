//$Id$
package com.java.lld.parkingLot.api;

import java.util.HashMap;

import com.java.lld.parkingLot.api.ParkingLotConstants.ParkingSpotType;
import com.java.lld.parkingLot.spot.CompactSpot;
import com.java.lld.parkingLot.spot.ElectricSpot;
import com.java.lld.parkingLot.spot.HandicappedSpot;

public class ParkingFloor {

	private String name;
	private HashMap<String, HandicappedSpot> handicappedSpots;
	private HashMap<String, CompactSpot> compactSpots;
	private HashMap<String, ElectricSpot> electricSpots;


	public ParkingFloor(String name) {
		this.name = name;
	}
	
	/*public void addParkingSpot(ParkingSpotType spot) {
		switch (spot) {
		case COMPACT:
			handicappedSpots.put(spot.getNumber(), spot);
			break;

		default:
			break;
		}
	}*/
	
}
