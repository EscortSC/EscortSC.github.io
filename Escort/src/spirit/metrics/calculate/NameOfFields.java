/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class NameOfFields extends ASTVisitor implements IAttribute {
	List<String> methodFieldsNames = new ArrayList();
	List<String> methodAccessFieldsNames = new ArrayList();
	List<String> parametersNames = new ArrayList();
	List<String> classAccessWithThis = new ArrayList();
	List<String> methodVariableAccess = new ArrayList();
	private static final String THIS = "this";

	public void calculate(ClassMetrics node) {
		ArrayList classFieldsNames = new ArrayList();
		TypeDeclaration classDeclaration = node.getDeclaration();
		FieldDeclaration[] methodFieldsNames1;
		int arg5 = (methodFieldsNames1 = classDeclaration.getFields()).length;

		for (int methodMetrics = 0; methodMetrics < arg5; ++methodMetrics) {
			FieldDeclaration methodsMetrics = methodFieldsNames1[methodMetrics];
			List i = methodsMetrics.fragments();
			Iterator arg9 = i.iterator();

			while (arg9.hasNext()) {
				VariableDeclarationFragment name = (VariableDeclarationFragment) arg9
						.next();
				classFieldsNames.add(name.getName().getFullyQualifiedName());
			}
		}

		node.setAttribute("FON", classFieldsNames);
		List arg10 = node.getMethodsMetrics();
		Iterator arg12 = arg10.iterator();

		while (arg12.hasNext()) {
			MethodMetrics arg11 = (MethodMetrics) arg12.next();
			List arg13 = (List) arg11.getAttribute("MFA");

			for (int arg14 = 0; arg14 < arg13.size(); ++arg14) {
				String arg15 = (String) arg13.get(arg14);
				if (!classFieldsNames.contains(arg15)) {
					arg13.remove(arg15);
					--arg14;
				}
			}

			arg11.setAttribute("MCFA", arg13);
		}

	}

	public String getName() {
		return "NOF";
	}

	public void calculate(MethodMetrics node) {
		this.methodAccessFieldsNames = new ArrayList();
		this.methodFieldsNames = new ArrayList();
		this.parametersNames = new ArrayList();
		this.classAccessWithThis = new ArrayList();
		this.methodVariableAccess = new ArrayList();
		List parameters = node.getDeclaration().parameters();
		Iterator arg3 = parameters.iterator();

		while (arg3.hasNext()) {
			VariableDeclaration name = (VariableDeclaration) arg3.next();
			this.parametersNames.add(name.getName().getFullyQualifiedName());
		}

		if (node.getDeclaration().getBody() != null) {
			node.getDeclaration().getBody().accept(this);
			node.setAttribute("FON", this.methodFieldsNames);
			this.methodVariableAccess.addAll(this.methodAccessFieldsNames);
			arg3 = this.methodFieldsNames.iterator();

			String name1;
			while (arg3.hasNext()) {
				name1 = (String) arg3.next();
				if (this.methodAccessFieldsNames.contains(name1)) {
					this.methodAccessFieldsNames.remove(name1);
				}

				if (!this.methodVariableAccess.contains(name1)) {
					this.methodVariableAccess.add(name1);
				}
			}

			arg3 = this.parametersNames.iterator();

			while (arg3.hasNext()) {
				name1 = (String) arg3.next();
				if (this.methodAccessFieldsNames.contains(name1)) {
					this.methodAccessFieldsNames.remove(name1);
				}

				if (!this.methodVariableAccess.contains(name1)) {
					this.methodVariableAccess.add(name1);
				}
			}

			arg3 = this.classAccessWithThis.iterator();

			while (arg3.hasNext()) {
				name1 = (String) arg3.next();
				if (!this.methodAccessFieldsNames.contains(name1)) {
					this.methodAccessFieldsNames.add(name1);
				}

				if (!this.methodVariableAccess.contains(name1)) {
					this.methodVariableAccess.add(name1);
				}
			}
		}

		node.setAttribute("MFA", this.methodAccessFieldsNames);//所有变量相关的语句中去除局部变量，方法参数，增加类中Field
		node.setAttribute("NOAV", this.methodVariableAccess);//变量相关的语句中增加局部变量，方法参数
	}

	public boolean visit(VariableDeclarationStatement node) {
		List declarations = node.fragments();
		if (declarations != null) {
			Iterator arg3 = declarations.iterator();

			while (arg3.hasNext()) {
				VariableDeclarationFragment declaration = (VariableDeclarationFragment) arg3
						.next();
				this.methodFieldsNames.add(declaration.getName()
						.getFullyQualifiedName());
			}
		}

		return true;
	}

	/*public boolean visit(SimpleName node) {
		if (!this.methodAccessFieldsNames
				.contains(node.getFullyQualifiedName())) {
			this.methodAccessFieldsNames.add(node.getFullyQualifiedName());
		}

		return true;
	}*/

	public boolean visit(FieldAccess node) {
		if (node.getExpression() != null) {
			String exp = node.getExpression().toString();
			if ("this".equals(exp)) {
				this.classAccessWithThis.add(node.getName()
						.getFullyQualifiedName());
			}
		}

		return true;
	}
}