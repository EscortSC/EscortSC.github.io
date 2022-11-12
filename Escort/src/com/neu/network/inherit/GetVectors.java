package com.neu.network.inherit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.calculate.CObject;
import com.neu.calculate.ClaObject;
import com.neu.calculate.PackageObject;

import DataProcess.JdtAstUtil;
import DataProcess.ReadPath;

public class GetVectors {
	private List<String> InheritVectors = new ArrayList<String>();

	public GetVectors(String proName) {
		this.setVectors(proName);
	}

	public List<String> getInheritVectors() {
		return this.InheritVectors;
	}

	private void setVectors(String proName) {
		ReadPath rp = new ReadPath("D:\\smell\\Project\\" + proName);
		List<String> paths = rp.getPath();
		for (String path : paths) {
			CompilationUnit comp1 = JdtAstUtil.getCompilationUnit(path);
			ClaObject co = new ClaObject();
			comp1.accept(co);
			if (co.getType() == null)
				continue;
			TypeDeclaration type = co.getType();
			List modifiers = type.modifiers();
			Iterator arg10 = modifiers.iterator();
			while (arg10.hasNext()) {
				IExtendedModifier modifier = (IExtendedModifier) arg10.next();
				if (modifier.isModifier() && ((Modifier) modifier).isAbstract()) {
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
					InheritVectors.add(className);
				}
			}
		}
	}

}
