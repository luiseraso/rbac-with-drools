package edu.unicauca.git.model.ehr;

import javax.persistence.Column;
import javax.persistence.Entity;

import edu.unicauca.git.model.rbcc.User;

@Entity
public class Image extends Record {
	
	@Column(length=100)
	private String url;
	@Column(length=2000,name="image_diagnosis")
	private String diagnosis;	
	
	protected Image(){
		
	}	
	
	public Image(String url, String diagnosis, User user){
		super(user);
		this.url=url;
		this.diagnosis=diagnosis;		
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
}
