/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class PercentageOfAddedServices implements IAttribute {
	public void calculate(ClassMetrics node) {
		float numAddServices = 0.0F;
		if (node.getMetric("NAS") != null && node.getMetric("NOPM") != null
				&& node.getMetric("NOPM").floatValue() > 0.0F) {
			numAddServices = node.getMetric("NAS").floatValue()
					/ node.getMetric("NOPM").floatValue();
		}

		node.setMetric(this.getName(), numAddServices);
	}

	public String getName() {
		return "PNAS";
	}

	public void calculate(MethodMetrics node) {
	}
}