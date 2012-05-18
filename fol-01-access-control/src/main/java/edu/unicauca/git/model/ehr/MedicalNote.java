package edu.unicauca.git.model.ehr;

import javax.persistence.Column;
import javax.persistence.Entity;

import edu.unicauca.git.model.rbcc.User;

@Entity
public class MedicalNote extends Record{
	
	@Column(length=2000)
	private String note;
	
	protected MedicalNote(){
		
	}
	
	public MedicalNote(String note, User user){
		super(user);
		this.setNote(note);	
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
