/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class NumOverrideMethodsInAncesters implements IAttribute {
	List<String> nameOfClasses;

	public NumOverrideMethodsInAncesters(List<String> nameOfClasses) {
		this.nameOfClasses = nameOfClasses;
	}

	public void calculate(ClassMetrics node) {
		float publicOverrides = 0.0F;
		ITypeBinding ancestor = node.getDeclaration().resolveBinding()
				.getSuperclass();

		ArrayList ancestorMethods;
		for (ancestorMethods = new ArrayList(); ancestor != null
				&& this.nameOfClasses.contains(ancestor.getBinaryName()); ancestor = ancestor
				.getSuperclass()) {
			ancestorMethods
					.addAll(Arrays.asList(ancestor.getDeclaredMethods()));
		}

		ITypeBinding child = node.getDeclaration().resolveBinding();
		IMethodBinding[] childMethods = child.getDeclaredMethods();
		if (ancestorMethods != null && childMethods != null) {
			IMethodBinding[] arg9 = childMethods;
			int arg8 = childMethods.length;

			for (int arg7 = 0; arg7 < arg8; ++arg7) {
				IMethodBinding childMethod = arg9[arg7];
				Iterator arg11 = ancestorMethods.iterator();

				while (arg11.hasNext()) {
					IMethodBinding ancestorMethod = (IMethodBinding) arg11
							.next();
					if (!childMethod.isConstructor()
							&& childMethod.overrides(ancestorMethod)) {
						if (Modifier.isPublic(childMethod.getModifiers())) {
							++publicOverrides;
						}
						break;
					}
				}
			}
		}

		node.setMetric(this.getName(), publicOverrides);
	}

	public String getName() {
		return "NPOvMAns";
	}

	public void calculate(MethodMetrics node) {
	}
}