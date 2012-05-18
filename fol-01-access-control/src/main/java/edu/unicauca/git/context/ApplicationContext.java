package edu.unicauca.git.context;

import edu.unicauca.git.model.ehr.Patient;
import edu.unicauca.git.model.rbcc.User;

public class ApplicationContext {
	
	private User user;
	private Patient patient;	
	
	public ApplicationContext(){
		//TODO Code to define ApplicationContext attributes		
	}

	public ApplicationContext(User user, Patient patient){
		this.user = user;
		this.patient = patient;
	}
	
	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setPatient(Patient patient){
		this.patient = patient;
	}

	public Patient getPatient(){
		return patient;
	}

}
