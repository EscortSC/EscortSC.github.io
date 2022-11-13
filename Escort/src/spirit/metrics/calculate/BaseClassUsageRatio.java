/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class BaseClassUsageRatio implements IAttribute {
	public void calculate(ClassMetrics node) {
		float ratio = 1.0F;
		float uses = 0.0F;
		if (node.getMetric("NProtM") != null
				&& node.getMetric("NProtM").floatValue() > 0.0F) {
			List ListOfLocalFieldUsed = (List) node
					.getAttribute("ListOfLocalFieldUsed");
			List ListOfLocalMethodInvoked = (List) node
					.getAttribute("ListOfLocalMethodInvoked");
			ITypeBinding declaration = node.getDeclaration().resolveBinding()
					.getSuperclass();
			IMethodBinding[] methods = declaration.getDeclaredMethods();
			int arg9;
			if (methods != null && ListOfLocalMethodInvoked != null) {
				IMethodBinding[] arg10 = methods;
				arg9 = methods.length;

				for (int field = 0; field < arg9; ++field) {
					IMethodBinding fields = arg10[field];
					if (Modifier.isProtected(fields.getModifiers())) {
						Iterator fieldAcc = ListOfLocalMethodInvoked.iterator();

						while (fieldAcc.hasNext()) {
							MethodInvocation methodInv = (MethodInvocation) fieldAcc
									.next();
							if (methodInv.resolveMethodBinding().getKey()
									.equals(fields.getKey())) {
								++uses;
								break;
							}
						}
					}
				}
			}

			IVariableBinding[] arg14 = declaration.getDeclaredFields();
			if (arg14 != null && ListOfLocalFieldUsed != null) {
				IVariableBinding[] arg17 = arg14;
				int arg16 = arg14.length;

				for (arg9 = 0; arg9 < arg16; ++arg9) {
					IVariableBinding arg15 = arg17[arg9];
					if (Modifier.isProtected(arg15.getModifiers())) {
						Iterator arg13 = ListOfLocalFieldUsed.iterator();

						while (arg13.hasNext()) {
							FieldAccess arg18 = (FieldAccess) arg13.next();
							if (arg18.resolveFieldBinding().getKey()
									.equals(arg15.getKey())) {
								++uses;
								break;
							}
						}
					}
				}
			}

			ratio = uses / node.getMetric("NProtM").floatValue();
		}

		node.setMetric(this.getName(), ratio);
	}

	public String getName() {
		return "BUR";
	}

	@Override
	public void calculate(MethodMetrics node) {
		// TODO Auto-generated method stub

	}

}