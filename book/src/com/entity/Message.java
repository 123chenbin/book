package com.entity;

public class Message {
	private int id;
	private String name;
	private String umessage;
	private int state;
	private String admessage;
	private int adstate;
	
	
	
	public int getAdstate() {
		return adstate;
	}
	public void setAdstate(int adstate) {
		this.adstate = adstate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUmessage() {
		return umessage;
	}
	public void setUmessage(String umessage) {
		this.umessage = umessage;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAdmessage() {
		return admessage;
	}
	public void setAdmessage(String admessage) {
		this.admessage = admessage;
	}
	
	
}
