package com.neu.invocation.matrics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;

public class CMethodMatrics {
	private List<String> classNames;
	private List<ClassObject> classObjects;
	public static List<String> methodNames;
	public static List<Feature> allMethods;
	private String relativePath;
	private int[][] CMethodMatrics;

	public CMethodMatrics() {
		classNames = SaveFileAction.classname;
		classObjects = SaveFileAction.classesArrList;
		relativePath = SaveFileAction.relativePath;
		this.methodNames = GetAllMethods.allMethodNames;
		this.allMethods =GetAllMethods.allFeatures;
		this.CreateMatrics();
		this.print();
	}

	public void CreateMatrics() {
		this.CMethodMatrics = new int[classNames.size()][methodNames.size()];
		for (ClassObject co : classObjects) {
			if (classNames.contains(co.getName())) {
				int i = classNames.indexOf(co.getName());
				List<Feature> features = co.getFeatureList();
				for (Feature fe : features) {
					if (fe.getName().contains("(")) {
						List<String> outBounds = fe.getOutboundFeatureList();
						for (String outBound : outBounds) {
							for (int j = 0; j < allMethods.size(); j++) {
								if (outBound.equals(allMethods.get(j).getName())) {
									CMethodMatrics[i][j] = 1;
								}
							}
						}
					}
				}
			}
		}
	}

	public void print() {
		try {
			File writename = new File("D:\\smell\\output\\" + relativePath + "\\classToMethodMatrix.txt");
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write("  " + ",");
			for (String methodName : methodNames) {
				String method = methodName.replace(",", ";");
				out.write(method + ",");
			}
			out.write('\n');
			for (int j = 0; j < classNames.size(); j++) {
				out.write(classNames.get(j) + ",");
				for (int k = 0; k < methodNames.size(); k++) {
					out.write(CMethodMatrics[j][k] + ",");
				}
				out.write('\n');
			}
			out.flush();
			out.close();
		}

		catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

}
