package com.neu.invocation.matrics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;

public class PrintCMList {
	List<ClassObject> classObject = SaveFileAction.classesArrList;

	public PrintCMList() {
		String relativePath = SaveFileAction.relativePath;
		this.printCMList(relativePath);
	}

	private void printCMList(String relativePath) {
		try {
			File writename = new File("D:\\smell\\output\\" + relativePath + "\\CMList.txt");
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			for (ClassObject co : classObject) {
				out.write("class " + co.getName().replace(".", "/") + '\n');
				List<Feature> features = co.FeatureList;
				for (Feature fe : features) {
					if (fe.getName().contains("(")) {
						String methodName =this.getShortName(fe.getName());
						String paraList = this.getShortParaList(fe.getName());
						out.write("method " + methodName +" " + paraList +" 0"+ '\n');
					}
				}
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private String getShortName(String longMethodName) {
		String[] str1 = longMethodName.split("\\(");
		int point = str1[0].lastIndexOf(".");
		String shortName = str1[0].substring(point+1);
		return shortName;
	}

	private String getShortParaList(String longMethodName) {
		String shortParaList = "";
		String[] str1 = longMethodName.split("\\(");
		if (str1[1].length() == 1) {
			shortParaList = "()";
			return shortParaList;
		} else {
			String[] str2 = str1[1].split("\\)");
			String[] paraList = str2[0].split(",");
			for (String para : paraList) {
				int pointP = para.lastIndexOf(".");
				String parameter=para.substring(pointP+1).trim();
				shortParaList += "," + parameter;
			}
			shortParaList = "(" + shortParaList.substring(1) + ")";
			return shortParaList;
		}

	}

}
