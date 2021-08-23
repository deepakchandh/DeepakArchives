//$Id$
package com.java.lld.parkingLot.spot;

import com.java.lld.parkingLot.api.ParkingLotConstants.ParkingSpotType;

public class CompactSpot extends ParkingSpot{

	public CompactSpot() {
	    super(ParkingSpotType.COMPACT);
	  }

	@Override
	public boolean IsFree() {
		// TODO Auto-generated method stub
		return false;
	}
}
