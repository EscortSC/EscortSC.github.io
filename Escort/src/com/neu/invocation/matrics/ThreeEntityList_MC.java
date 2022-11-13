package com.neu.invocation.matrics;

import java.util.ArrayList;
import java.util.List;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;

public class ThreeEntityList_MC extends ThreeEntityList {
	private List<String> classNames = GetAllMethods.allClassNames;
	private List<ClassObject> classObjects = SaveFileAction.classesArrList;
	private List<Feature> allMethods = GetAllMethods.allFeatures;
	private List<String> allMethodNames = GetAllMethods.allMethodNames;
	private List<ClassObject> feaAndClass = GetAllMethods.FeaAndClass;

	public ThreeEntityList_MC() {
	//	this.printThreeEntity("ThreeEntityList_MC", setAllList());
		//this.printThreeEntity("InherianceList_MC", this.setInherianceList());
		this.printThreeEntity("InvokeList_MC", this.setInvokeList());
	}

	@Override
	List<List<String>> setAllList() {
		// TODO Auto-generated method stub
		List<List<String>> allEntityAndBounds = new ArrayList<List<String>>();
		for (int i = 0; i < allMethods.size(); i++) {
			Feature method = allMethods.get(i);
			String methodName = allMethodNames.get(i);
			List<String> entityOutBounds = new ArrayList<String>();
			List<String> entityInBounds = new ArrayList<String>();
			entityOutBounds.add(methodName);
			entityInBounds.add(methodName);
			String[] st1 = method.getName().split("\\(");
			int index = st1[0].lastIndexOf(".");
			String classNameForMethod = st1[0].substring(0, index);
			String sMethodName = method.getName().substring(index + 1);
			this.ImplementAndExtends(feaAndClass.get(i).getOutboundClassList(), sMethodName, entityOutBounds);
			this.addBounds(method.getOutboundClassList(), method.getOutboundFeatureList(), entityOutBounds,
					classNameForMethod);
			this.addBounds(method.getInboundClassList(), method.getInboundFeatureList(), entityInBounds,
					classNameForMethod);
			allEntityAndBounds.add(entityOutBounds);
			allEntityAndBounds.add(entityInBounds);
		}
		return allEntityAndBounds;
	}

	private void addBounds(List<String> boundC, List<String> boundF, List<String> bounds, String classNameForMethod) {
		for (String OutBound : boundF) {
			String[] str = OutBound.split("\\(");
			int lastIndex = str[0].lastIndexOf(".");
			String mName = str[0].substring(0, lastIndex);
			if (!classNameForMethod.equals(mName) && classNames.contains(mName) && (!(bounds.contains(mName)))) {
				bounds.add(mName);
			}
		}
		for (String cName : boundC) {
			if (classNames.contains(cName) && (!(bounds.contains(cName)))) {
				bounds.add(cName);
			}
		}
	}

	private void ImplementAndExtends(List<String> inhers, String sMethodName, List<String> bounds) {
		for (String her : inhers) {
			ClassObject co = classObjects.get(classNames.indexOf(her));
			this.ImplementAndExtends(co.getOutboundClassList(), sMethodName, bounds);
			for (Feature fe : co.getFeatureList()) {
				if (fe.getName().endsWith(sMethodName) && !(bounds.contains(co.getName()))) {
					bounds.add(co.getName());
					break;
				}
			}
		}
	}

	@Override
	List<List<String>> setInherianceList() {
		List<List<String>> allEntityAndBounds = new ArrayList<List<String>>();
		for (int i = 0; i < allMethods.size(); i++) {
			Feature method = allMethods.get(i);
			String methodName = allMethodNames.get(i);
			List<String> entityOutBounds = new ArrayList<String>();
			List<String> entityInBounds = new ArrayList<String>();
			entityOutBounds.add(methodName);
			entityInBounds.add(methodName);
			String[] st1 = method.getName().split("\\(");
			int index = st1[0].lastIndexOf(".");
			String sMethodName = method.getName().substring(index + 1);
			this.ImplementAndExtends(feaAndClass.get(i).getOutboundClassList(), sMethodName, entityOutBounds);
			allEntityAndBounds.add(entityOutBounds);
			allEntityAndBounds.add(entityInBounds);
		}
		return allEntityAndBounds;
	}

	@Override
	List<List<String>> setInvokeList() {
		List<List<String>> allEntityAndBounds = new ArrayList<List<String>>();
		for (int i = 0; i < allMethods.size(); i++) {
			Feature method = allMethods.get(i);
			String methodName = allMethodNames.get(i);
			List<String> entityOutBounds = new ArrayList<String>();
			List<String> entityInBounds = new ArrayList<String>();
			entityOutBounds.add(methodName);
			entityInBounds.add(methodName);
			String[] st1 = method.getName().split("\\(");
			int index = st1[0].lastIndexOf(".");
			String classNameForMethod = st1[0].substring(0, index);
			this.addBounds(method.getOutboundClassList(), method.getOutboundFeatureList(), entityOutBounds,
					classNameForMethod);
			this.addBounds(method.getInboundClassList(), method.getInboundFeatureList(), entityInBounds,
					classNameForMethod);
			allEntityAndBounds.add(entityOutBounds);
			allEntityAndBounds.add(entityInBounds);
		}
		return allEntityAndBounds;
	}

}
