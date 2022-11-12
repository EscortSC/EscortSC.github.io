/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.List;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class ChangingClasses implements IAttribute {

	public void calculate(ClassMetrics node) {
	}

	public String getName() {
		return "CC";
	}

	public void calculate(MethodMetrics node) {
		if (node.getAttribute("ListOfClassInvoking") != null) {
			List listOfClassInvoking = (List) node
					.getAttribute("ListOfClassInvoking");
			node.setMetric(this.getName(), (float) listOfClassInvoking.size());
		}

	}


}