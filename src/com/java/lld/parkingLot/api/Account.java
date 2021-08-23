//$Id$
package com.java.lld.parkingLot.api;

import com.java.lld.parkingLot.api.ParkingLotConstants.AccountStatus;

public abstract class Account {
	public static String userName;
	public String password;
	private AccountStatus status;
	private Person person;

	public abstract boolean resetPassword();

}
