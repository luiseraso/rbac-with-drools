package edu.unicauca.git.context;

public class ActionPermission {
	
	private String action;
	private Object source;
	private Object target;
	private boolean response;
	
	public ActionPermission(String action){
		this.action = action;
	}
	
	public ActionPermission(String action, Object source, Object target){
		this.action = action;
		this.setSource(source);
		this.target = target;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setSource(Object source) {
		this.source = source;
	}

	public Object getSource() {
		return source;
	}
	
	public void setTarget(Object target) {
		this.target = target;
	}

	public Object getTarget() {
		return target;
	}
	
	public void setResponse(boolean response) {
		this.response = response;
	}
	
	public boolean isResponse() {
		return response;
	}
	
}
