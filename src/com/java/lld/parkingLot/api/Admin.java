//$Id$
package com.java.lld.parkingLot.api;

public class Admin extends Account{

	
	@Override
	public boolean resetPassword() {
		userName = "admin";
		password = "7908099";
		return false;
	}

}
