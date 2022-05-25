/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class SuperClassHierarchy implements IAttribute {
	List<String> nameOfClasses;

	public SuperClassHierarchy(List<String> nameOfClasses) {
		this.nameOfClasses = nameOfClasses;
	}

	public void calculate(ClassMetrics classMetrics) {
		classMetrics.setAttribute(this.getName(), classMetrics.getClassObject().getOutboundClassList());
	}

	public String getName() {
		return "SC";
	}

	public void calculate(MethodMetrics node) {
	}
}