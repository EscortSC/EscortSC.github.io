package com.neu.calculate;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodsForClass extends ASTVisitor{
	private ArrayList<MethodDeclaration> methods;
	
	public MethodsForClass(){
		methods=new ArrayList<MethodDeclaration>();	
	}

	 @Override
	    public boolean visit(MethodDeclaration node) {
	        methods.add(node);
	        return false;
	    }
	 public ArrayList<MethodDeclaration> getMethods(){
		 return this.methods;
	 }

}
