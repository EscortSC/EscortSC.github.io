/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;
import com.neu.calculate.PackageObject;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class NumOverrideMethods implements IAttribute {
	List<String> nameOfClasses;
	List<ClassObject> classes = SaveFileAction.classesArrList;

	public NumOverrideMethods(List<String> nameOfClasses) {
		this.nameOfClasses = nameOfClasses;
	}

	public void calculate(ClassMetrics node) {
		float overrides = 0.0F;
		float ratio = 0.0F;
		float publicOverrides = 0.0F;
		float publicOverridesAncestor = 0.0F;
		if (node.getDeclaration().getSuperclassType() != null) {
			String parentName = node.getDeclaration().getSuperclassType().toString();
			Set<String> methodNames = this.getMethodNames(node.getClassObject().getFeatureList());
			for(String parent:node.getClassObject().getOutboundClassList()) {
				if(parent.endsWith(parentName)) {
					for (ClassObject co : classes) {
						if (co.getName().equals(parent)) {
							Set<String> pMethodNames = this.getMethodNames(co.getFeatureList());
							float override = this.getOveride(methodNames, pMethodNames);
							overrides += override;
							float perc = override / pMethodNames.size();
							if (perc > ratio)
								ratio = perc;
							break;
						}
					}
					break;
				}
			}
		}

		node.setMetric(this.getName(), ratio);
		node.setMetric("NOvM", overrides);
		node.setMetric("NPOvM", publicOverrides);
		node.setMetric("NPOvMAns", publicOverridesAncestor);
	}

	public String getName() {
		return "BOvR";
	}

	public Set<String> getMethodNames(List<Feature> features) {
		Set<String> methods = new HashSet<String>();
		for (Feature fe : features) {
			if (fe.getName().contains("(")) {
				int index = fe.getName().indexOf("(");
				String noPara = fe.getName().substring(0, index);
				int from = noPara.lastIndexOf(".");
				methods.add(noPara.substring(from+1));
			}
		}
		return methods;
	}

	public float getOveride(Set<String> childNames, Set<String> parentNames) {
		float override = 0;
		for (String child : childNames) {
			if (parentNames.contains(child))
				override++;
		}
		return override;
	}

	public void calculate(MethodMetrics node) {
	}
}