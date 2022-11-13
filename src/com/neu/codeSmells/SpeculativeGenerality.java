package com.neu.codeSmells;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.Refactor.classparser.ClassObject;
import com.jeantessier.dependencyfinder.gui.SaveFileAction;
import com.neu.codeSmell.calculate.CObject;
import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.PackageObject;

import DataProcess.JdtAstUtil;

public class SpeculativeGenerality {
	private ArrayList<String> candidateSpeculativeGeneralities;
	private ArrayList<TypeDeclaration> abstractTypes;
	private List<ClassObject> classesArrList;

	public SpeculativeGenerality() {
		classesArrList = SaveFileAction.classesArrList;
		candidateSpeculativeGeneralities = new ArrayList<String>();
		abstractTypes = new ArrayList<TypeDeclaration>();
	}

	public ArrayList<String> searchSpeculativeGeneralities(ArrayList<String> paths) {
		ArrayList<TypeDeclaration> types = new ArrayList<TypeDeclaration>();
		for (String path : paths) {
			CompilationUnit comp1 = JdtAstUtil.getCompilationUnit(path);
			ClaObject co = new ClaObject();
			comp1.accept(co);
			if (co.getType() == null)
				continue;
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
			List modifiers = type.modifiers();
			Iterator arg10 = modifiers.iterator();
			while (arg10.hasNext()) {
				IExtendedModifier modifier = (IExtendedModifier) arg10.next();
				if (modifier.isModifier() && ((Modifier) modifier).isAbstract()) {
					for (ClassObject claOb : classesArrList) {
						if (claOb.getName().equals(className)) {
							if (claOb.getInboundClassList().size() < 2 && !this.candidateSpeculativeGeneralities
									.contains(className + ";" + claOb.getInboundClassList().size()))
								this.candidateSpeculativeGeneralities
										.add(className + ";" + claOb.getInboundClassList().size());
						}
					}
				}
			}
		}

		return this.candidateSpeculativeGeneralities;

	}

}
