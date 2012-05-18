package edu.unicauca.git.context;

import java.util.Calendar;

public class SystemContext {

	private String ip;	
	private String location;
	private String device;
	private Calendar accessTime;
	
	public SystemContext(){
		//TODO Code to define SystemContext attributes
	}
	
	public SystemContext(String ip, String location, String device){		
		this(ip, location, device, null);
	}

	public SystemContext(String ip, String location, String device, Calendar accessTime){
		this.ip=ip;
		this.device=device;
		this.location=location;
		
		if(accessTime != null){
			this.accessTime=accessTime;
		}else{
			this.accessTime= Calendar.getInstance();
		}
						
		/*
		//Example to define location from ip address
		System.out.println(ip);
		String[] net = ip.split("\\."); 
		
		if( "192".equals(net[0]) && "168".equals("168") ){
			if( "10".equals(net[2]) ) this.location="emergency";
			else if( "20".equals(net[2]) ) this.location="triage";
			else if( "30".equals(net[2]) ) this.location="surgery";
			else this.location="general";
		}else{
			this.location="unknown";
		}
		*/			
	}	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Calendar getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Calendar accessTime) {
		this.accessTime = accessTime;
	}
	
}
