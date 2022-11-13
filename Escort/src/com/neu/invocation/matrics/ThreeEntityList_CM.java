package com.neu.invocation.matrics;

import java.util.ArrayList;
import java.util.List;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;

public class ThreeEntityList_CM extends ThreeEntityList {
	private List<String> allOriMethodNames = GetAllMethods.allOriMethodNames;
	private List<String> allMethodNames = GetAllMethods.allMethodNames;
	private List<ClassObject> classObjects = SaveFileAction.classesArrList;
	private List<String> classNames = GetAllMethods.allClassNames;

	public ThreeEntityList_CM() {
		printThreeEntity("ThreeEntityList_CM", this.setAllList());
		printThreeEntity("InherianceList_CM", this.setInherianceList());
		printThreeEntity("InvokeList_CM", this.setInvokeList());
	}

	@Override
	List<List<String>> setAllList() {
		// TODO Auto-generated method stub
		List<List<String>> allEntityAndBounds = new ArrayList<List<String>>();
		for (ClassObject co : classObjects) {
			List<String> entityOutBounds = new ArrayList<String>();
			List<String> entityInBounds = new ArrayList<String>();
			entityOutBounds.add(co.getName());
			entityInBounds.add(co.getName());
			this.implementAndExtends(co.getOutboundClassList(), entityOutBounds);
			this.invokes(co.FeatureList, entityOutBounds, entityInBounds, co.getName());
			allEntityAndBounds.add(entityOutBounds);
			allEntityAndBounds.add(entityInBounds);
		}
		return allEntityAndBounds;
	}

	private void invokes(List<Feature> features, List<String> outBounds, List<String> inBounds, String className) {
		for (Feature fe : features) {
			for (String out : fe.getOutboundMethodList()) {
				int index = allOriMethodNames.indexOf(out);
				if (index >= 0 && (!(out.startsWith(className))) && !(outBounds.contains(allMethodNames.get(index)))) {
					outBounds.add(allMethodNames.get(index));
				}
			}
			for (String in : fe.getInboundMethodList()) {
				int index = allOriMethodNames.indexOf(in);
				if (index >= 0 && (!(in.startsWith(className))) && !(inBounds.contains(allMethodNames.get(index)))) {
					inBounds.add(allMethodNames.get(index));
				}
			}
		}
	}

	private void implementAndExtends(List<String> inhers, List<String> bounds) {
		for (String her : inhers) {
			ClassObject co = classObjects.get(classNames.indexOf(her));
			this.implementAndExtends(co.getOutboundClassList(), bounds);
			for (Feature fep : co.getFeatureList()) {
				String method = fep.getName();
				if (this.allOriMethodNames.contains(method)) {
					int index = this.allOriMethodNames.indexOf(method);
					if (!(bounds.contains(allMethodNames.get(index)))) {
						bounds.add(allMethodNames.get(index));
					}
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
			List<String> entityInBounds = new ArrayList<String>();
			entityOutBounds.add(co.getName());
			entityInBounds.add(co.getName());
			this.implementAndExtends(co.getOutboundClassList(), entityOutBounds);
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
			List<String> entityInBounds = new ArrayList<String>();
			entityOutBounds.add(co.getName());
			entityInBounds.add(co.getName());
			this.invokes(co.FeatureList, entityOutBounds, entityInBounds, co.getName());
			allEntityAndBounds.add(entityOutBounds);
			allEntityAndBounds.add(entityInBounds);
		}
		return allEntityAndBounds;
	}

}
