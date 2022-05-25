/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;



public class NumberOfLinesOfCode implements IAttribute {
	public void calculate(ClassMetrics node) {
		String classCode = node.getDeclaration().toString();
		node.setMetric(this.getName(), (float) countLines(classCode));
	}

	public String getName() {
		return "LOC";
	}

	public void calculate(MethodMetrics node) {
		if (node.getDeclaration().getBody() != null) {
			String methodCode = node.getDeclaration().getBody().toString();
			node.setMetric(this.getName(), (float) countLines(methodCode));
		} else {
			node.setMetric(this.getName(), 0.0F);
		}

	}

	private static int countLines(String str) {
		String[] lines = str.split("\r\n|\r|\n");
		return lines.length;
	}
}