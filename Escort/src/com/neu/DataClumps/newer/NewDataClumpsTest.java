package com.neu.DataClumps.newer;

import com.neu.invocation.matrics.GetAllMethods;

import DataProcess.ReadPath;

public class NewDataClumpsTest {
	public static void main(String[] args) {
		//List<String> paths=new LinkedList<String>();
		//paths.add("D:\\smell\\Project\\Derby");
		ReadPath rp=new ReadPath("D:\\smell\\Project\\Derby");
	//	ReadPath rp=new ReadPath("D:\\smell\\Project\\Struts");
		GetAllMethods gam=new GetAllMethods();
		DataClumpsDetector dataclumps=new DataClumpsDetector(rp.getPath());
	
	}

}
