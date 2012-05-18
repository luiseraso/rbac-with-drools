package edu.unicauca.git.context;

public class Consent {
	
	private String purpose;
	private boolean response;
	
	public Consent(){		
	}
	
	public Consent(String purpose){
		this.setPurpose(purpose);
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	public boolean isResponse() {
		return response;
	}
	
}
