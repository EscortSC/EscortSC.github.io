/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class LocalityOfAttributesAccesses implements IAttribute {
	public void calculate(ClassMetrics node) {
	}

	public String getName() {
		return "LAA";
	}

	public void calculate(MethodMetrics node) {
		float laa = 1.0F;
		if (node.getMetric("ATLD") != null
				&& node.getMetric("ATFD") != null
				&& node.getMetric("ATLD").floatValue()
						+ node.getMetric("ATFD").floatValue() != 0.0F) {
			laa = node.getMetric("ATLD").floatValue()
					/ (node.getMetric("ATLD").floatValue() + node.getMetric(
							"ATFD").floatValue());
		}

		node.setMetric(this.getName(), laa);
	}
}