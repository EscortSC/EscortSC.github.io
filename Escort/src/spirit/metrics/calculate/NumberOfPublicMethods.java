/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class NumberOfPublicMethods implements IAttribute {
	public void calculate(ClassMetrics node) {
		TypeDeclaration declaration = node.getDeclaration();
		MethodDeclaration[] methods = declaration.getMethods();
		int publicMethods = 0;
		if (methods != null) {
			MethodDeclaration[] arg7 = methods;
			int arg6 = methods.length;

			for (int arg5 = 0; arg5 < arg6; ++arg5) {
				MethodDeclaration method = arg7[arg5];
				if (!method.isConstructor()
						&& Modifier.isPublic(method.getModifiers())) {
					++publicMethods;
				}
			}
		}

		node.setMetric(this.getName(), (float) publicMethods);
	}

	public String getName() {
		return "NOPM";
	}

	public void calculate(MethodMetrics node) {
	}
}