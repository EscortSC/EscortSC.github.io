/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class NumberOfMethods implements IAttribute {
	public void calculate(ClassMetrics node) {
		TypeDeclaration declaration = node.getDeclaration();
		node.setMetric(this.getName(), (float) declaration.getMethods().length);
	}

	public String getName() {
		return "NOM";
	}

	public void calculate(MethodMetrics node) {
	}
}