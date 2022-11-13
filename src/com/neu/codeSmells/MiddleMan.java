package com.neu.codeSmells;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.codeSmell.calculate.CObject;
import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.MiddleManVisitor;
import com.neu.codeSmell.calculate.PackageObject;

import DataProcess.JdtAstUtil;

public class MiddleMan {
	ArrayList<String> candidateMiddleMan;
	private MiddleManVisitor visitor1;

	public MiddleMan() {
		candidateMiddleMan = new ArrayList<String>();

	}

	public ArrayList<String> searchMiddleMan(ArrayList<String> paths) {
		for (String path : paths) {
			CompilationUnit comp1 = JdtAstUtil.getCompilationUnit(path);
			ClaObject co = new ClaObject();
			comp1.accept(co);
			if (co.getType() != null) {
				TypeDeclaration type = co.getType();
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
				visitor1 = new MiddleManVisitor();
				comp1.accept(visitor1);
				int numMethod = visitor1.getMethods().size();
				int count = visitor1.getCount();
				float count1 = count;
				if (numMethod != 0 && count1 / numMethod > 0.66 && count1 / numMethod < 1)
					candidateMiddleMan.add(className + ";" + count1 / numMethod);
			}
		}

		return this.candidateMiddleMan;

	}

}
