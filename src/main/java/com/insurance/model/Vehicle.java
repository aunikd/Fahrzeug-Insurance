package com.insurance.model;



import java.util.Date;
import java.util.List;
import java.util.Set;


import org.springframework.lang.NonNull;

// Vehicle pojo entity
public class Vehicle {
	

private int vehicle_id;

private String vehicle_purchase_date;

private String vehicle_policy_type;

private String vehicle_type;

private String vehicle_company;

private String vehicle_model;

private String vehicle_engine;

public int getVehicle_id() {
	return vehicle_id;
}

public void setVehicle_id(int vehicle_id) {
	this.vehicle_id = vehicle_id;
}

public String getVehicle_type() {
	return vehicle_type;
}

public void setVehicle_type(String vehicle_type) {
	this.vehicle_type = vehicle_type;
}

public String getVehicle_company() {
	return vehicle_company;
}

public void setVehicle_company(String vehicle_company) {
	this.vehicle_company = vehicle_company;
}

public String getVehicle_model() {
	return vehicle_model;
}

public void setVehicle_model(String vehicle_model) {
	this.vehicle_model = vehicle_model;
}

public String getVehicle_engine() {
	return vehicle_engine;
}

public void setVehicle_engine(String vehicle_engine) {
	this.vehicle_engine = vehicle_engine;
}

public String getVehicle_purchase_date() {
	return vehicle_purchase_date;
}

public void setVehicle_purchase_date(String vehicle_purchase_date) {
	this.vehicle_purchase_date = vehicle_purchase_date;
}

public String getVehicle_policy_type() {
	return vehicle_policy_type;
}

public void setVehicle_policy_type(String vehicle_policy_type) {
	this.vehicle_policy_type = vehicle_policy_type;
}









}
