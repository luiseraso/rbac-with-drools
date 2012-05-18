package edu.unicauca.git.model.ehr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.unicauca.git.model.rbcc.User;

@Entity(name="ehr_patient_record")
public class PatientRecord {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String issue;
	private boolean state;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;
	private User physician;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="patient_record_id")
	private List<Record> records;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="ehr_jn_precord_team")
	private List<User> team;
	
	protected PatientRecord(){
		
	}

	protected PatientRecord(String issue, User phyisician){
		
		this.issue=issue;
		this.state=true;
		this.startDate=new Date();
		this.physician=phyisician;
		this.records=new ArrayList<Record>();
		this.team=new ArrayList<User>();
		
		team.add(physician);		
	}

	public void addImage(String url, String diagnosis, User user){
		Image img = new Image(url,diagnosis,user);
		records.add(img);
	}
	
	public void addMedicalNote(String note, User user){
		MedicalNote medNote = new MedicalNote(note, user);
		records.add(medNote);
	}
	
	public void addTestResult(String test, String diagnosis, User user){
		TestResult testResult = new TestResult(test, diagnosis, user);
		records.add(testResult);
	}
	
	public void addPhyisicianToTeam(User u){
		if(!team.contains(u)){
			team.add(u);
		}
	}
	
	public void removePhyisicianToTeam(User u){
		if(!team.contains(u)){
			team.remove(u);
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public User getPhysician() {
		return physician;
	}

	public void setPhysician(User physician) {
		this.physician = physician;
	}

	public List<User> getTeam() {
		return team;
	}

	public void setTeam(List<User> team) {
		this.team = team;
	}
	
}
