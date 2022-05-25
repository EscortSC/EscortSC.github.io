package com.neu.DataClumps;

import java.util.Set;

import com.neu.codeSmells.DataClumps;
import com.neu.invocation.matrics.GetAllMethods;

import DataProcess.ReadPath;

public class DataClumpsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetAllMethods gam =new GetAllMethods();
		ReadPath rp=new ReadPath("D:\\smell\\Project\\Wicket-7.1.0\\org\\apache\\wicket\\core\\util\\crypt");
		System.out.println(rp.getPath().size());
		DataClumps dct=new DataClumps(rp.getPath());
		Set<String> candidateDataClumps=dct.getCandidateDataClumps();
		for(String dataClumps:candidateDataClumps) {
			System.out.println("DataClumps;"+dataClumps);
		}
	

	}

}
