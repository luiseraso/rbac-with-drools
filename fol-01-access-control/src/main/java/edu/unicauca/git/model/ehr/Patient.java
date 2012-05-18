package edu.unicauca.git.model.ehr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.unicauca.git.model.rbcc.User;

@Entity(name="ehr_patient")
public class Patient {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private String name;
	private char gender;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="patient_id")
	private List<PatientRecord> patientRecord;	
		
	protected Patient(){
		
	}
	
	public Patient(String name, char gender, Date birthday){
		this.name=name;
		this.gender=gender;
		this.birthday=birthday;
		this.patientRecord = new ArrayList<PatientRecord>();
	}

	public PatientRecord addPatientRecord(String issue, User pysician){
		PatientRecord recordIssue = new PatientRecord(issue, pysician);
		patientRecord.add(recordIssue);
		return recordIssue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<PatientRecord> getPatientRecord() {
		return patientRecord;
	}

	public void setPatientRecord(List<PatientRecord> patientRecord) {
		this.patientRecord = patientRecord;
	}
	
	public String toString(){
		return "Patient{id="+id+"; name="+name+"; gender= "+gender+"; birthday="+birthday+"}";
	}
	
	
}
