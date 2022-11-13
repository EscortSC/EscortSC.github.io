package com.neu.calculate;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class NumOfFields extends ASTVisitor{
	ArrayList<FieldDeclaration> fields=new ArrayList<FieldDeclaration>();
	ArrayList<FieldDeclaration> publicFields=new ArrayList<FieldDeclaration>();

	@Override
    public boolean visit(FieldDeclaration node) {
		fields.add(node);
        for (Object obj: node.fragments()) {
            VariableDeclarationFragment v = (VariableDeclarationFragment)obj;
            node.modifiers();
            if(v.toString().contains("public")) publicFields.add(node);
        }
        return false;
    }

	public ArrayList<FieldDeclaration> getFields(){
		return this.fields;
	}

	public int getPublicFieldNum(){
		return this.publicFields.size();
	}


}
