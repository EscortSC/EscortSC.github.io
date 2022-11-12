package com.neu.invocation.matrics;

import java.util.ArrayList;
import java.util.List;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;

public class ThreeEntityList_MM extends ThreeEntityList {
	private List<String> allMethodNames = GetAllMethods.allMethodNames;
	private List<ClassObject> feaAndClass = GetAllMethods.FeaAndClass;
	private List<Feature> allMethods = GetAllMethods.allFeatures;
	private List<ClassObject> classObjects = SaveFileAction.classesArrList;
	private List<String> classNames = GetAllMethods.allClassNames;

	public ThreeEntityList_MM() {
		this.printThreeEntity("ThreeEntityList_MM", setAllList());
		this.printThreeEntity("InherianceList_MM", this.setInherianceList());
		this.printThreeEntity("InvokeList_MM", this.setInvokeList());
	}

	@Override
	List<List<String>> setAllList() {
		// TODO Auto-generated method stub
		List<List<String>> allEntityAndBounds = new ArrayList<List<String>>();
		for (int i = 0; i < allMethods.size(); i++) {
			Feature method = allMethods.get(i);
			List<String> entityOutBounds = new ArrayList<String>();
			entityOutBounds.add(allMethodNames.get(i));
			List<String> entityInBounds = new ArrayList<String>();
			entityInBounds.add(allMethodNames.get(i));
			String[] st1 = allMethodNames.get(i).split("\\(");
			int index = st1[0].lastIndexOf(".");
			String sMethodName = allMethodNames.get(i).substring(index + 1);
			this.ImplementAndExtends(feaAndClass.get(i).getOutboundClassList(), sMethodName, entityOutBounds);
			List<String> outBoundList = method.getOutboundMethodList();
			addBounds(outBoundList, entityOutBounds);
			List<String> inBoundsList = method.getInboundMethodList();
			addBounds(inBoundsList, entityInBounds);
			allEntityAndBounds.add(entityOutBounds);
			allEntityAndBounds.add(entityInBounds);
		}
		return allEntityAndBounds;
	}

	private void addBounds(List<String> boundList, List<String> bounds) {
		for (String outBound : boundList) {
			if (outBound.contains("(") && outBound.contains(")")) {
				String paramenters = "";
				String str1[] = outBound.split("\\(");
				String str2[] = str1[1].split("\\)");
				if (str2.length > 0) {
					String str3[] = str2[0].split("\\, ");
					for (int k = 0; k < str3.length; k++) {
						String str4[] = str3[k].split("\\.");
						paramenters = paramenters + str4[str4.length - 1] + ",";
					}
					paramenters = paramenters.substring(0, paramenters.length() - 1);
				}
				String method1Name = str1[0] + "(" + paramenters + ")";
				if (allMethodNames.contains(method1Name) && (!(bounds.contains(method1Name)))) {
					bounds.add(method1Name);
				}
			}
		}
	}

	private void ImplementAndExtends(List<String> inhers, String sMethodName, List<String> bounds) {
		for (String her : inhers) {
			ClassObject co = classObjects.get(classNames.indexOf(her));
			this.ImplementAndExtends(co.getOutboundClassList(), sMethodName, bounds);
			for (Feature fe : co.getFeatureList()) {
				String method=fe.getName();
				if (method.contains("(") && method.contains(")")) {
					String paramenters = "";
					String str1[] = method.split("\\(");
					String str2[] = str1[1].split("\\)");
					if (str2.length > 0) {
						String str3[] = str2[0].split("\\, ");
						for (int k = 0; k < str3.length; k++) {
							String str4[] = str3[k].split("\\.");
							paramenters = paramenters + str4[str4.length - 1] + ",";
						}
						paramenters = paramenters.substring(0, paramenters.length() - 1);
					}
					String method1Name = str1[0] + "(" + paramenters + ")";
					if (method1Name.contains(sMethodName)&& (allMethodNames.contains(method1Name)) && (!(bounds.contains(method1Name)))) {
						bounds.add(method1Name);
						break;
					}
				}
			}
		}
	}

	@Override
	List<List<String>> setInvokeList() {
		List<List<String>> allEntityAndBounds = new ArrayList<List<String>>();
		for (int i = 0; i < allMethods.size(); i++) {
			Feature method = allMethods.get(i);
			List<String> entityOutBounds = new ArrayList<String>();
			entityOutBounds.add(allMethodNames.get(i));
			List<String> entityInBounds = new ArrayList<String>();
			entityInBounds.add(allMethodNames.get(i));
			List<String> outBoundList = method.getOutboundMethodList();
			addBounds(outBoundList, entityOutBounds);
			List<String> inBoundsList = method.getInboundMethodList();
			addBounds(inBoundsList, entityInBounds);
			allEntityAndBounds.add(entityOutBounds);
			allEntityAndBounds.add(entityInBounds);
		}
		return allEntityAndBounds;
	}

	@Override
	List<List<String>> setInherianceList() {
		List<List<String>> allEntityAndBounds = new ArrayList<List<String>>();
		for (int i = 0; i < allMethods.size(); i++) {
			List<String> entityOutBounds = new ArrayList<String>();
			entityOutBounds.add(allMethodNames.get(i));
			List<String> entityInBounds = new ArrayList<String>();
			entityInBounds.add(allMethodNames.get(i));
			String[] st1 = allMethodNames.get(i).split("\\(");
			int index = st1[0].lastIndexOf(".");
			String sMethodName = allMethodNames.get(i).substring(index + 1);
			this.ImplementAndExtends(feaAndClass.get(i).getOutboundClassList(), sMethodName, entityOutBounds);
			allEntityAndBounds.add(entityOutBounds);
			allEntityAndBounds.add(entityInBounds);
		}
		return allEntityAndBounds;
	}

}
