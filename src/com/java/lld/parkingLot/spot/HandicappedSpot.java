//$Id$
package com.java.lld.parkingLot.spot;

import com.java.lld.parkingLot.api.ParkingLotConstants.ParkingSpotType;

public class HandicappedSpot extends ParkingSpot{

	private static Integer count = 20;
	
	public HandicappedSpot() {
		super(ParkingSpotType.HANDICAPPED);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean IsFree() {
		return false;
	}

	public Integer remainingCount(){
		return count;
	}
	
	public void allotASpace() {
		--count;
	}
	
}
