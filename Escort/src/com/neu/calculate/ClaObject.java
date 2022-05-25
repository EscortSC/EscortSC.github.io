package com.neu.calculate;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class ClaObject extends ASTVisitor {
	 private TypeDeclaration type;
	 private ArrayList<MethodDeclaration> methods=new ArrayList<MethodDeclaration>();
	 private ArrayList<FieldDeclaration> fields=new ArrayList<FieldDeclaration>();

	 @Override
	    public boolean visit(TypeDeclaration node) {
	        type=node;
	        return true;
	    }
	 @Override
	    public boolean visit(MethodDeclaration node) {
	        methods.add(node);
	        return true;
	    }

	 @Override
	    public boolean visit(FieldDeclaration node) {
		 fields.add(node);
		 return true;
	 }

	 public TypeDeclaration getType(){
		 return this.type;
	 }

	 public ArrayList<MethodDeclaration> getMethods(){
		 return this.methods;
	 }

	 public ArrayList<FieldDeclaration> getFields(){
		 return this.fields;

	 }


}
