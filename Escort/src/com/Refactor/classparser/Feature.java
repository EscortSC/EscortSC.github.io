package com.Refactor.classparser;
import java.util.ArrayList;
import java.util.List;

public class Feature {
	String name;
	List<String> inboundClassList = new ArrayList<String>();
	List<String> inboundAttributeList = new ArrayList<String>();
	List<String> inboundMethodList = new ArrayList<String>();
	List<String> inboundFeatureList = new ArrayList<String>();
	List<String> outboundClassList = new ArrayList<String>();
	ArrayList<String> outboundAttributeList = new ArrayList<String>();
	List<String> outboundMethodList = new ArrayList<String>();
	List<String> outboundFeatureList = new ArrayList<String>();
	ArrayList<String> extract = new ArrayList<String>();
	boolean dead = false;
	public int methodlength = 0;

	int methodlinesbe = 0;
	int methodlinesaf = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getMethodlength() {
		return methodlength;
	}

	public void setMethodlength(int methodlength) {
		this.methodlength = methodlength;
	}


	public List<String> getInboundClassList() {
		return inboundClassList;
	}

	public void setInboundClassList(List<String> inboundClassList) {
		this.inboundClassList = inboundClassList;
	}

	public List<String> getInboundAttributeList() {
		return inboundAttributeList;
	}

	public void setInboundAttributeList(List<String> inboundAttributeList) {
		this.inboundAttributeList = inboundAttributeList;
	}

	public List<String> getInboundMethodList() {
		return inboundMethodList;
	}

	public void setInboundMethodList(List<String> inboundMethodList) {
		this.inboundMethodList = inboundMethodList;
	}

	public List<String> getInboundFeatureList() {
		return inboundFeatureList;
	}

	public void setInboundFeatureList(List<String> inboundFeatureList) {
		this.inboundFeatureList = inboundFeatureList;
	}

	public List<String> getOutboundClassList() {
		return outboundClassList;
	}

	public void setOutboundClassList(List<String> outboundClassList) {
		this.outboundClassList = outboundClassList;
	}

	public List<String> getOutboundAttributeList() {
		return outboundAttributeList;
	}

	public void setOutboundAttributeList(ArrayList<String> outboundAttributeList) {
		this.outboundAttributeList = outboundAttributeList;
	}

	public List<String> getOutboundMethodList() {
		return outboundMethodList;
	}

	public void setOutboundMethodList(List<String> outboundMethodList) {
		this.outboundMethodList = outboundMethodList;
	}

	public List<String> getOutboundFeatureList() {
		return outboundFeatureList;
	}

	public void setOutboundFeatureList(List<String> outboundFeatureList) {
		this.outboundFeatureList = outboundFeatureList;
	}

	public ArrayList<String> getExtract() {
		return extract;
	}

	public void setExtract(ArrayList<String> extract) {
		this.extract = extract;
	}

	public int getMethodlinesbe() {
		return methodlinesbe;
	}

	public void setMethodlinesbe(int methodlinesbe) {
		this.methodlinesbe = methodlinesbe;
	}

	public int getMethodlinesaf() {
		return methodlinesaf;
	}

	public void setMethodlinesaf(int methodlinesaf) {
		this.methodlinesaf = methodlinesaf;
	}

}
