/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.Iterator;
import java.util.List;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class AverageMethodWeight implements IAttribute {
	public void calculate(ClassMetrics node) {
		float avgMethodWeight = 1.0F;
		List methods = node.getMethodsMetrics();
		Iterator arg4 = methods.iterator();

		while (arg4.hasNext()) {
			MethodMetrics method = (MethodMetrics) arg4.next();
			if (method.getMetric("WMC") != null) {
				avgMethodWeight += method.getMetric("WMC").floatValue();
			}
		}

		if (methods.size() != 0) {
			avgMethodWeight /= (float) methods.size();
		}

		node.setMetric(this.getName(), avgMethodWeight);
	}

	public String getName() {
		return "AMW";
	}

	public void calculate(MethodMetrics node) {
	}




}