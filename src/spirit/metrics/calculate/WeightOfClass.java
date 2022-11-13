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



public class WeightOfClass implements IAttribute {
	private static final String GET = "get";
	private static final String SET = "set";

	public void calculate(ClassMetrics node) {
		TypeDeclaration declaration = node.getDeclaration();
		MethodDeclaration[] methods = declaration.getMethods();
		int nom = 0;
		int den = 0;
		if (methods != null) {
			MethodDeclaration[] arg8 = methods;
			int arg7 = methods.length;

			for (int arg6 = 0; arg6 < arg7; ++arg6) {
				MethodDeclaration woc = arg8[arg6];
				if (this.isPublicAndNonStatic(woc) && !woc.isConstructor()) {
					++nom;
					String nameOfMethod = woc.getName().getFullyQualifiedName();
					if (nameOfMethod.startsWith("get")
							|| nameOfMethod.startsWith("set")) {
						++den;
					}
				}
			}
		}

		nom += node.getMetric("NOPA").intValue();
		float arg10 = 1.0F;
		if (nom > 0) {
			arg10 = (float) den / (float) nom;
		} else if (den == 0) {
			arg10 = 0.0F;
		}

		node.setMetric(this.getName(), arg10);
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
		return "WOC";
	}

	public void calculate(MethodMetrics node) {
	}
}