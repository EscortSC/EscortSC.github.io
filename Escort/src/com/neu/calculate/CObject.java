package com.neu.calculate;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class CObject extends ASTVisitor {
	private String className;

	@Override
	public boolean visit(TypeDeclaration node) {
		this.className = node.getName().toString();
		return false;
	}

	public String getClassName() {
		return this.className;
	}

}