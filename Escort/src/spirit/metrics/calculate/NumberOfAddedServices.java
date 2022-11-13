/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;



public class NumberOfAddedServices implements IAttribute {
	public void calculate(ClassMetrics node) {
		int numAddServices = 0;
		if (node.getMetric("NPOvMAns") != null
				&& node.getMetric("NOPM") != null) {
			numAddServices = (int) (node.getMetric("NOPM").floatValue() - node
					.getMetric("NPOvMAns").floatValue());
		}

		node.setMetric(this.getName(), (float) numAddServices);
	}

	public String getName() {
		return "NAS";
	}

	public void calculate(MethodMetrics node) {
	}
}