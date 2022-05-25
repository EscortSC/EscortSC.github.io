/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.List;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.Modifier;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class NumProtMembersInParent implements IAttribute {
	List<String> nameOfClasses;

	public NumProtMembersInParent(List<String> nameOfClasses) {
		this.nameOfClasses = nameOfClasses;
	}

	public void calculate(ClassMetrics node) {
		int protMembers = 0;
		if (node.getDeclaration().resolveBinding().getSuperclass() != null
				&& this.nameOfClasses.contains(node.getDeclaration()
						.resolveBinding().getSuperclass().getBinaryName())) {
			ITypeBinding declaration = node.getDeclaration().resolveBinding()
					.getSuperclass();
			IMethodBinding[] methods = declaration.getDeclaredMethods();
			int arg6;
			if (methods != null) {
				IMethodBinding[] arg7 = methods;
				arg6 = methods.length;

				for (int field = 0; field < arg6; ++field) {
					IMethodBinding fields = arg7[field];
					if (Modifier.isProtected(fields.getModifiers())) {
						++protMembers;
					}
				}
			}

			IVariableBinding[] arg9 = declaration.getDeclaredFields();
			if (arg9 != null) {
				IVariableBinding[] arg8 = arg9;
				int arg11 = arg9.length;

				for (arg6 = 0; arg6 < arg11; ++arg6) {
					IVariableBinding arg10 = arg8[arg6];
					if (Modifier.isProtected(arg10.getModifiers())) {
						++protMembers;
					}
				}
			}
		}

		node.setMetric(this.getName(), (float) protMembers);
	}

	public String getName() {
		return "NProtM";
	}

	public void calculate(MethodMetrics node) {
	}
}