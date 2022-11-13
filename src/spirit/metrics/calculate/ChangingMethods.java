/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.List;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class ChangingMethods implements IAttribute {
	public void calculate(ClassMetrics node) {
	}

	public String getName() {
		return "CM";
	}

	public void calculate(MethodMetrics node) {
		if (node.getAttribute("ListOfMethodInvoking") != null) {
			List listOfMethodInvoking = (List) node
					.getAttribute("ListOfMethodInvoking");
			node.setMetric(this.getName(), (float) listOfMethodInvoking.size());
		}

	}


}