package testAuto.Model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jayway.jsonpath.JsonPath;

public class RabbitMQ {

	private String name;
	private String features;
	private String state;
	private int ready;
	private int unacked;
	private int total;	
	private String incoming;	
	private String deliverget;
	private String ack;
	private String created_at;
	private String updated_at;

	public RabbitMQ() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public RabbitMQ(String name, String features, String state, int ready, int unacked, int total, String deliverget,
			String ack, String created_at, String updated_at) {
		super();
		this.name = name;
		this.features = features;
		this.state = state;
		this.ready = ready;
		this.unacked = unacked;
		this.total = total;
		this.deliverget = deliverget;
		this.ack = ack;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFeatures() {
		return features;
	}


	public void setFeatures(String features) {
		this.features = features;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getReady() {
		return ready;
	}


	public void setReady(int ready) {
		this.ready = ready;
	}


	public int getUnacked() {
		return unacked;
	}


	public void setUnacked(int unacked) {
		this.unacked = unacked;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public String getDeliverget() {
		return deliverget;
	}


	public void setDeliverget(String deliverget) {
		this.deliverget = deliverget;
	}


	public String getAck() {
		return ack;
	}


	public void setAck(String ack) {
		this.ack = ack;
	}


	public String getCreated_at() {
		return created_at;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}


	public String getUpdated_at() {
		return updated_at;
	}


	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}	


	public String getIncoming() {
		return incoming;
	}


	public void setIncoming(String incoming) {
		this.incoming = incoming;
	}


	@Override
	public String toString() {
		return "RabbitMQ [name=" + name + ", features=" + features + ", state=" + state + ", ready=" + ready
				+ ", unacked=" + unacked + ", total=" + total + ", incoming=" + incoming + ", deliverget="
				+ deliverget + ", ack=" + ack + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	

}
