package com.neu.codeSmell.calculate;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.PackageDeclaration;

public class PObject extends ASTVisitor{
	String packageName="";
	 public boolean visit(PackageDeclaration node) {
	         packageName=node.getName().toString();
	        return false;
	 }
	 public String getPackageName(){
		 return packageName;
	 }

}
