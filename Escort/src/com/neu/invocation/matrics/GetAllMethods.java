package com.neu.invocation.matrics;

import java.util.ArrayList;
import java.util.List;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;

public class GetAllMethods {
	public static List<String> allMethodNames = new ArrayList<String>();
	public static List<String> allOriMethodNames = new ArrayList<String>();
	public static List<Feature> allFeatures = new ArrayList<Feature>();
	public static List<ClassObject> FeaAndClass = new ArrayList<ClassObject>();
	public static List<String> allClassNames = new ArrayList<String>();
	private List<ClassObject> classObjects;

	public GetAllMethods() {
		this.classObjects = SaveFileAction.classesArrList;
		this.setAllFeatures();
		this.setAllOriMethodNames();
		this.setMethodNames();
		this.setAllClassNames();

	}

	private void setAllClassNames() {
		for (int k = 0; k < classObjects.size(); k++) {
			allClassNames.add(classObjects.get(k).getName());
		}
	}

	private void setAllOriMethodNames() {
		for (Feature fe : allFeatures) {
			allOriMethodNames.add(fe.getName());
		}
	}

	private void setAllFeatures() {
		for (ClassObject co : classObjects) {
			List<Feature> featureList = co.getFeatureList();
			for (Feature fe : featureList) {
				if (fe.getName().contains("(") && fe.getName().contains(")")) {
					allFeatures.add(fe);
					FeaAndClass.add(co);
				}
			}
		}
	}

	public void setMethodNames() {
		for (Feature fe : allFeatures) {
			String paramenters = "";
			String str1[] = fe.getName().split("\\(");
			String str2[] = str1[1].split("\\)");
			if (str2.length > 0) {
				String str3[] = str2[0].split("\\, ");
				for (int k = 0; k < str3.length; k++) {
					String str4[] = str3[k].split("\\.");
					paramenters = paramenters + str4[str4.length - 1] + ",";
				}
				paramenters = paramenters.substring(0, paramenters.length() - 1);
			}
			allMethodNames.add(str1[0] + "(" + paramenters + ")");
		}

	}

}
