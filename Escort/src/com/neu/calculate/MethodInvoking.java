package com.neu.calculate;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class MethodInvoking extends ASTVisitor{
	private ArrayList<MethodInvocation>   list= new ArrayList<MethodInvocation>();
	private String className="";
	private int fanOut=0;
	
	public ArrayList<MethodInvocation> getList(){
		return this.list;
	}

	public boolean visit(TypeDeclaration node){
		className=node.getName().toString();
		return true;
	}

	public boolean visit(MethodInvocation node){
	    list.add(node);			
		return false;
	}

	public int getFanOut(){
		return this.fanOut;
	}




}
