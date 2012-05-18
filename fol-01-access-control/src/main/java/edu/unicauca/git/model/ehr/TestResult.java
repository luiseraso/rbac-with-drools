package edu.unicauca.git.model.ehr;

import javax.persistence.Column;
import javax.persistence.Entity;

import edu.unicauca.git.model.rbcc.User;

@Entity
public class TestResult extends Record{
		
	@Column(length=100)
	private String test;
	@Column(length=2000, name="test_diagnosis")
	private String diagnosis;	
	
	protected TestResult(){
		
	}
	
	public TestResult(String test, String diagnosis, User user){
		super(user);
		this.setTest(test);
		this.setDiagnosis(diagnosis);
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
}
