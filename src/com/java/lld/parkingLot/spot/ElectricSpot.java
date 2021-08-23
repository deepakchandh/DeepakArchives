//$Id$
package com.java.lld.parkingLot.spot;

import com.java.lld.parkingLot.api.ParkingLotConstants.ParkingSpotType;

public class ElectricSpot extends ParkingSpot{

	public ElectricSpot() {
	    super(ParkingSpotType.ELECTRIC);
	  }

	@Override
	public boolean IsFree() {
		// TODO Auto-generated method stub
		return false;
	}
}
