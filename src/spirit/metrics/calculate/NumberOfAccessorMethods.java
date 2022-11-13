/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.Iterator;
import java.util.List;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class NumberOfAccessorMethods implements IAttribute {
	private static final String GET = "get";
	private static final String SET = "set";

	public void calculate(ClassMetrics node) {
		TypeDeclaration declaration = node.getDeclaration();
		MethodDeclaration[] lmethod = declaration.getMethods();
		int noam = 0;
		MethodDeclaration[] arg7 = lmethod;
		int arg6 = lmethod.length;

		for (int arg5 = 0; arg5 < arg6; ++arg5) {
			MethodDeclaration method = arg7[arg5];
			if (this.isPublicAndNonStatic(method)
					&& (method.getName().getFullyQualifiedName()
							.startsWith("get") || method.getName()
							.getFullyQualifiedName().startsWith("set"))) {
				++noam;
			}
		}

		node.setMetric(this.getName(), (float) noam);
	}

	private boolean isPublicAndNonStatic(MethodDeclaration method) {
		List modifiers = method.modifiers();
		boolean isPublic = false;
		boolean isStatic = false;
		Iterator arg5 = modifiers.iterator();

		while (arg5.hasNext()) {
			IExtendedModifier modifier = (IExtendedModifier) arg5.next();
			if (modifier.isModifier()) {
				if (((Modifier) modifier).isPublic()) {
					isPublic = true;
				}

				if (((Modifier) modifier).isStatic()) {
					isStatic = true;
				}
			}
		}

		if (isPublic && !isStatic) {
			return true;
		} else {
			return false;
		}
	}

	public String getName() {
		return "NOAM";
	}

	public void calculate(MethodMetrics node) {
	}
}