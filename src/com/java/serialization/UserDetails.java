//$Id$
package com.java.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public final class UserDetails implements Serializable {

	public UserDetails(String aFirstName, String aLastName, int aAccountNumber,
			Date aDateOpened) 
	{
		super();
		setFirstName(aFirstName);
		setLastName(aLastName);
		setAccountNumber(aAccountNumber);
		setDateOpened(aDateOpened);
	}

	// The default constructor
	public UserDetails() {
		this("FirstName", "LastName", 0, new Date(System.currentTimeMillis()));
	}

	public final String getFirstName() {
		return fFirstName;
	}

	public final String getLastName() {
		return fLastName;
	}

	public final int getAccountNumber() {
		return fAccountNumber;
	}

	public final Date getDateOpened() {
		return new Date(fDateOpened.getTime());
	}

	public final void setFirstName(String aNewFirstName) {
//		verifyNameProperty(aNewFirstName);
		fFirstName = aNewFirstName;
	}

	public final void setLastName(String aNewLastName) {
//		verifyNameProperty(aNewLastName);
		fLastName = aNewLastName;
	}

	public final void setAccountNumber(int aNewAccountNumber) {
		validateAccountNumber(aNewAccountNumber);
		fAccountNumber = aNewAccountNumber;
	}

	public final void setDateOpened(Date aNewDate) {
		// make a defensive copy of the mutable date object
		Date newDate = new Date(aNewDate.getTime());
		validateAccountOpenDate(newDate);
		fDateOpened = newDate;
	}

	private String fFirstName;

	private String fLastName;

	private int fAccountNumber;

	private Date fDateOpened;

	private static final long serialVersionUID = 7526471155622776147L;

	private void verifyUserDetails() {
		validateAccountNumber(fAccountNumber);
//		verifyNameProperty(fFirstName);
//		verifyNameProperty(fLastName);
		validateAccountOpenDate(fDateOpened);
	}

	private void validateAccountNumber(int aAccountNumber) {
		if (aAccountNumber < 0) {
			String message = "Account Number must be greater than or equal to 0.";
			throw new IllegalArgumentException(message);
		}
	}

	private void validateAccountOpenDate(Date aDateOpened) {
		if (aDateOpened.getTime() < 0) {
			throw new IllegalArgumentException(
					"Date Opened must be after 1970.");
		}
	}

	/**
	 * Always treat deserialization as a full-blown constructor, by validating
	 * the final state of the de-serialized object.
	 */
	private void readObject(ObjectInputStream aInputStream)
			throws ClassNotFoundException, IOException {
		// always perform the default deserialization first
		aInputStream.defaultReadObject();

		// make defensive copy of the mutable Date field
		fDateOpened = new Date(fDateOpened.getTime());

		// ensure that object state has not been corrupted or tampered with
		// malicious code
		verifyUserDetails();
	}

	/**
	 * This is the default implementation of writeObject. Customise if
	 * necessary.
	 */
	private void writeObject(ObjectOutputStream aOutputStream)
			throws IOException {
		// perform the default serialization for all non-transient, non-static
		// fields
		aOutputStream.defaultWriteObject();
	}
}