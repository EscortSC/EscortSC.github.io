/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.List;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class NumberOfFields implements IAttribute {
	public void calculate(ClassMetrics node) {
		TypeDeclaration declaration = node.getDeclaration();
		node.setMetric(this.getName(), (float) declaration.getFields().length);
	}

	public String getName() {
		return "NOF";
	}

	public void calculate(MethodMetrics node) {
		List fields = (List) node.getAttribute("NOAV");
		if (fields != null) {
			node.setMetric(this.getName(), (float) fields.size());
		}

	}
}