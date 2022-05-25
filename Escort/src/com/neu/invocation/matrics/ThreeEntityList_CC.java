package com.neu.invocation.matrics;

import java.util.ArrayList;
import java.util.List;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;

public class ThreeEntityList_CC extends ThreeEntityList {
	private List<String> classNames = GetAllMethods.allClassNames;
	private List<ClassObject> classObjects = SaveFileAction.classesArrList;

	public ThreeEntityList_CC() {
		this.printThreeEntity("ThreeEntityList_CC", this.setAllList());
		this.printThreeEntity("InherianceList_CC", this.setInherianceList());
		this.printThreeEntity("InvokeList_CC", this.setInvokeList());

	}

	@Override
	List<List<String>> setAllList() {
		List<List<String>> allEntityAndBounds = new ArrayList<List<String>>();
		for (ClassObject co : classObjects) {
			List<String> entityOutBounds = new ArrayList<String>();
			entityOutBounds.add(co.getName());
			List<String> entityInBounds = new ArrayList<String>();
			entityInBounds.add(co.getName());
			this.implemenAndExtends(co.getOutboundClassList(), entityOutBounds);
			this.invokes(co.getFeatureList(), entityOutBounds, entityInBounds);
			allEntityAndBounds.add(entityOutBounds);
			allEntityAndBounds.add(entityInBounds);
		}
		return allEntityAndBounds;
	}

	private void implemenAndExtends(List<String> ancestors, List<String> bounds) {
		for (String ant : ancestors) {
			if (classNames.contains(ant)) {
				ClassObject co=classObjects.get(classNames.indexOf(ant));
				this.implemenAndExtends(co.getOutboundClassList(), bounds);
				bounds.add(ant);
			}
		}
	}

	private void invokes(List<Feature> features, List<String> outBounds, List<String> inBounds) {
		for (Feature fe : features) {
			for (String bound : fe.getOutboundFeatureList()) {
				String[] str = bound.split("\\(");
				int index = str[0].lastIndexOf(".");
				String className = str[0].substring(0, index);
				if (classNames.contains(className) && !(outBounds.contains(className))) {
					outBounds.add(className);
				}
			}
			for (String boundc : fe.getOutboundClassList()) {
				if (classNames.contains(boundc) && !(outBounds.contains(boundc))) {
					outBounds.add(boundc);
				}
			}
			for (String bound : fe.getInboundFeatureList()) {
				String[] str = bound.split("\\(");
				int index = str[0].lastIndexOf(".");
				String className = str[0].substring(0, index);
				if (classNames.contains(className) && !(outBounds.contains(className))) {
					inBounds.add(className);
				}
			}

		}
	}

	@Override
	List<List<String>> setInherianceList() {
		// TODO Auto-generated method stub
		List<List<String>> allEntityAndBounds = new ArrayList<List<String>>();
		for (ClassObject co : classObjects) {
			List<String> entityOutBounds = new ArrayList<String>();
			entityOutBounds.add(co.getName());
			List<String> entityInBounds = new ArrayList<String>();
			entityInBounds.add(co.getName());
			this.implemenAndExtends(co.getOutboundClassList(), entityOutBounds);
			allEntityAndBounds.add(entityOutBounds);
			allEntityAndBounds.add(entityInBounds);
		}
		return allEntityAndBounds;
	}

	@Override
	List<List<String>> setInvokeList() {
		// TODO Auto-generated method stub
		List<List<String>> allEntityAndBounds = new ArrayList<List<String>>();
		for (ClassObject co : classObjects) {
			List<String> entityOutBounds = new ArrayList<String>();
			entityOutBounds.add(co.getName());
			List<String> entityInBounds = new ArrayList<String>();
			entityInBounds.add(co.getName());
			this.invokes(co.getFeatureList(), entityOutBounds, entityInBounds);
			allEntityAndBounds.add(entityOutBounds);
			allEntityAndBounds.add(entityInBounds);
		}
		return allEntityAndBounds;
	}

}
