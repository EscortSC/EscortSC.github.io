/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.Iterator;
import java.util.List;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class TightClassCohesion implements IAttribute {
	public void calculate(ClassMetrics node) {
		node.setMetric(this.getName(), this.calculateTCC(node, node
				.getDeclaration().getMethods().length));
	}

	public float calculateTCC(ClassMetrics node, int numberOfPublicMethods) {
		return (float) this.calculateNDC(node)
				/ this.calculateNP(numberOfPublicMethods);
	}

	public String getName() {
		return "TCC";
	}

	public void calculate(MethodMetrics node) {
		List a=(List)node.getAttribute("ListOfClassInvoked");
	}

	public int calculateNDC(ClassMetrics node) {
		int countPairMethods = 0;
		List methodsMetrics = node.getMethodsMetrics();

		for (int index = 0; index + 1 < methodsMetrics.size(); ++index) {
			List methodFieldsNames1 = (List) ((MethodMetrics) methodsMetrics
					.get(index)).getAttribute("MCFA");

			for (int index2 = index + 1; index2 < methodsMetrics.size(); ++index2) {
				List methodFieldsNames2 = (List) ((MethodMetrics) methodsMetrics
						.get(index2)).getAttribute("MCFA");
				Iterator arg8 = methodFieldsNames1.iterator();

				while (arg8.hasNext()) {
					String m1 = (String) arg8.next();
					if (methodFieldsNames2.contains(m1)) {
						++countPairMethods;
						break;
					}
				}
			}
		}

		return countPairMethods;
	}

	public float calculateNP(int numberOfPublicMethods) {
		return (float) (numberOfPublicMethods * (numberOfPublicMethods - 1)) / 2.0F;
	}
}