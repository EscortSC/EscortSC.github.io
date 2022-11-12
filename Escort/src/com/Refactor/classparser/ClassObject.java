package com.Refactor.classparser;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Refactor.NonInheritance.Methodfeature;

public class ClassObject {
	String name;
	List<String> inboundClassList = new ArrayList<String>();
	List<String> inboundFeatureList = new ArrayList<String>();
	List<String> outboundClassList = new ArrayList<String>();
	List<String> outboundFeatureList = new ArrayList<String>();
	List<Methodfeature> methodlines = new ArrayList<Methodfeature>();
	public List<Feature> FeatureList = new ArrayList<Feature>();
	List<String> OtheroutboundClassList = new ArrayList<String>();
	public List<String> getOtheroutboundClassList() {
		return OtheroutboundClassList;
	}

	public void setOtheroutboundClassList(List<String> otheroutboundClassList) {
		OtheroutboundClassList = otheroutboundClassList;
	}

	double connectivity = 0;
	double LCOM = 0;
	double CBO = 0;
	double MPC = 0;
	double C3 = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getInboundClassList() {
		return inboundClassList;
	}

	public void setInboundClassList(List<String> inboundClassList) {
		this.inboundClassList = inboundClassList;
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

	public List<String> getOutboundFeatureList() {
		return outboundFeatureList;
	}

	public void setOutboundFeatureList(List<String> outboundFeatureList) {
		this.outboundFeatureList = outboundFeatureList;
	}

	public List<Feature> getFeatureList() {
		return FeatureList;
	}

	public void setFeatureList(List<Feature> featureList) {
		FeatureList = featureList;
	}

}
