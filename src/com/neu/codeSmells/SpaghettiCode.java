package com.neu.codeSmells;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import com.neu.codeSmell.calculate.CObject;
import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.MethodsForClass;
import com.neu.codeSmell.calculate.NumOfFields;
import com.neu.codeSmell.calculate.PackageObject;

import DataProcess.JdtAstUtil;

public class SpaghettiCode {
	private ArrayList<String> candidateSpaghettiCodes;

	public SpaghettiCode() {
		candidateSpaghettiCodes = new ArrayList<String>();
	}

	public ArrayList<String> searchCandidateSpaghettiCodes(ArrayList<String> paths) {
		for (String path : paths) {
			CompilationUnit comp1 = JdtAstUtil.getCompilationUnit(path);
			ClaObject mfc = new ClaObject();
			comp1.accept(mfc);
			if (mfc.getType() != null) {
				TypeDeclaration type = mfc.getType();
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
				if (className.startsWith("."))
					continue;
				ArrayList<MethodDeclaration> methods = mfc.getMethods();
				ArrayList<FieldDeclaration> fields = mfc.getFields();
				int count = 0;
				for (MethodDeclaration method : methods) {
					boolean found = this.checkIsSpaghetti(fields, method);
					if (found) {
						// System.out.println(className+","+method.getName());
						count++;
					}
				}
				if (count > 0) {
					if (!this.isIn(className)) {
						this.candidateSpaghettiCodes.add(className + ";" + count + ";" + this.LOC(comp1));
					}
				}
			}

		}

		return this.candidateSpaghettiCodes;
	}

	private boolean checkIsSpaghetti(ArrayList<FieldDeclaration> fields, MethodDeclaration method) {
		boolean isSpaghe = false;
		int loc = this.LOC(method);
		if (loc > 50 && method.parameters().size() == 0) {
			for (FieldDeclaration field : fields) {
				for (Object obj : field.fragments()) {
					VariableDeclarationFragment v = (VariableDeclarationFragment) obj;
					Pattern pattern = Pattern.compile("[^A-Za-z]" + v.getName() + "[^A-Za-z]");
					Matcher matcher = pattern.matcher(method.toString());
					if (matcher.find()) {
						isSpaghe = true;
						return true;
					}
				}
			}
		}
		return isSpaghe;
	}

	private int LOC(MethodDeclaration method) {
		int loc = 0;
		String source = method.toString();
		String regex = "[\n]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while (matcher.find())
			loc++;
		return loc + 1;
	}

	private int LOC(CompilationUnit cu) {
		int loc = 0;
		String source = cu.toString();
		String regex = "[\n]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while (matcher.find())
			loc++;
		return loc + 1;
	}

	private boolean isIn(String path) {
		for (String SpaghettiCode : candidateSpaghettiCodes) {
			String[] str = SpaghettiCode.split(",");
			if (str[0].equals(path))
				return true;
		}
		return false;
	}

}
