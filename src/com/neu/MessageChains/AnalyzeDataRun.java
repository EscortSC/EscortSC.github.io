package com.neu.MessageChains;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.neu.codeSmells.LongParameterList;

import DataProcess.ReadPath;

public class AnalyzeDataRun {
	static String path ="C:\\Fanyi\\dataset\\unzip\\rest-assured";
	static String analyzepath = "C:\\Fanyi\\dataset\\file\\rest-assured";

	public static void main(String[] args) {
		
		
		List<String> allfilepath = new ArrayList<String>();
		TestPrint TP = new TestPrint();
		allfilepath = TP.scanFiles(path);
		for (int k = 0; k < allfilepath.size(); k++) {
			ReadPath rp = new ReadPath(allfilepath.get(k));
			ArrayList<String> paths = rp.getPath();
			LongParameterList LPL = new LongParameterList();
			ArrayList<String> methodname = new ArrayList<>();
			methodname = LPL.searchLongParamenterListAntipattern(paths);
			String[] temp = allfilepath.get(k).split("\\\\");
			String tempTXT = temp[temp.length - 1];
			try {
				/* read Txt file */

				File writename = new File(analyzepath + "\\\\" + tempTXT + ".txt");// Parsing path
				writename.createNewFile();
				BufferedWriter out = new BufferedWriter(new FileWriter(writename));
				for (int i = 0; i < methodname.size(); i++) {
					out.write(methodname.get(i) + "\r\n");
					// System.out.println(i);
				}
				out.flush(); 
				out.close(); 
			} catch (Exception e) {
				e.printStackTrace();

			}
			paths.clear();
			System.out.println(k);
		}
	
	

	}

}
