package com.neu.codeSmells;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.codeSmell.calculate.CObject;
import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.NumOfFields;
import com.neu.codeSmell.calculate.PackageObject;

import DataProcess.JdtAstUtil;

public class ClassDataShouldBePrivate {
	private ArrayList<String> candidateClassDataShouldBePrivates;

	public ClassDataShouldBePrivate() {
		this.candidateClassDataShouldBePrivates = new ArrayList<String>();
	}

	public ArrayList<String> searchClassDataShouldBePrivates(ArrayList<String> paths) {
		for (String path : paths) {
			CompilationUnit comp1 = JdtAstUtil.getCompilationUnit(path);
			ClaObject co = new ClaObject();
			comp1.accept(co);
			TypeDeclaration type = co.getType();
			if (type == null)
				continue;
			NumOfFields nof = new NumOfFields();
			comp1.accept(nof);
			ArrayList<FieldDeclaration> fields = nof.getFields();
			int numOfFields = fields.size();
			int numOfPublicFields = nof.getPublicFieldNum();
			double numOfPFields = numOfPublicFields;
			if (numOfFields > 3 && (numOfPFields / numOfFields > 0.5)) {
				ASTNode self = type;
				ASTNode parent = self.getParent();
				while (parent.getNodeType() == 55) {
					self = parent;
					parent = parent.getParent();
				}
				PackageObject po = new PackageObject();
				CObject coo = new CObject();
				parent.accept(po);
				self.accept(coo);
				String className = po.getPackageName() + "." + coo.getClassName();
				this.candidateClassDataShouldBePrivates.add(className + ";" + numOfFields + ";" + numOfPublicFields);
			}

		}
		return this.candidateClassDataShouldBePrivates;
	}

}
