package edu.unicauca.git.context;

import java.util.Calendar;

import edu.unicauca.git.model.ehr.Patient;
import edu.unicauca.git.model.rbcc.User;

public class Appointment {
	
	private User user;
	private Patient patient;
	private Calendar initDate;
	private Calendar endDate;
	
	public Appointment(){
		
	}
	
	public Appointment(User user, Patient patient, Calendar initDate, Calendar endDate){
		this.setUser(user);
		this.setPatient(patient);
		this.setInitDate(initDate);
		this.setEndDate(endDate);
	}

	public void setUser(User user) {
		this.user = user;		
	}

	public User getUser() {
		return user;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setInitDate(Calendar initDate) {
		this.initDate = initDate;
	}

	public Calendar getInitDate() {
		return initDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}	
	
}
