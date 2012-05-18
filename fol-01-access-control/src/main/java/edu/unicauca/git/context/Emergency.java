package edu.unicauca.git.context;

public class Emergency {
	
	private String type;
	
	public Emergency(){	
	}
	
	public Emergency(String type){
		this.setType(type);
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}	

}
