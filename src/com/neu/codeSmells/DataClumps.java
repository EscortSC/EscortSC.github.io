package com.neu.codeSmells;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.DataClumps.ClumpGroup;
import com.neu.DataClumps.ClumpSignature;
import com.neu.DataClumps.ClumpSpider;
import com.neu.DataClumps.DataClumpCollection;
import com.neu.DataClumps.DataClumpDetetion;
import com.neu.codeSmell.calculate.CObject;
import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.PackageObject;
import com.neu.invocation.matrics.GetAllMethods;

import DataProcess.JdtAstUtil;

public class DataClumps {
	private Set<String> candidateDataClumps;
	private List<String> allMethodNames;

	public DataClumps(ArrayList<String> paths) {
		this.allMethodNames = GetAllMethods.allMethodNames;
		this.candidateDataClumps = searchDataClumps(paths);
	}

	private Set<String> searchDataClumps(ArrayList<String> paths) {
		Set<String> candidateDataClump = new HashSet<String>();
		for (String path : paths) {
			CompilationUnit comp = JdtAstUtil.getCompilationUnit(path);
			ClaObject co = new ClaObject();
			comp.accept(co);
			TypeDeclaration type = co.getType();
			if (type == null)
				continue;
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
			List<MethodDeclaration> methods = co.getMethods();

			DataClumpDetetion dcd = new DataClumpDetetion();
			ClumpSpider dataClump = dcd.calculateComplexity(methods);
			DataClumpCollection colloection = (DataClumpCollection) dataClump.getColloection();
			Map<ClumpSignature, ClumpGroup> clumps = colloection.getClumps();
			for (Entry<ClumpSignature, ClumpGroup> value : clumps.entrySet()) {
				Set<String> names = value.getKey().getNames();
				Set<MethodDeclaration> clumpMethods = value.getValue().getMethods();
				if (names.size() >= 3 && clumpMethods.size() > 2) {
					for (MethodDeclaration method : clumpMethods) {
						String fullName = className + "." + method.getName();
						List parameters = method.parameters();
						for (String methodName : allMethodNames) {
							if (methodName.startsWith(fullName)) {
								int paraCount = 0;
								int firstKH = methodName.indexOf("(");
								int tailKH = methodName.indexOf(")");
								String paraList = methodName.substring(firstKH + 1, tailKH);
								String[] para = paraList.split(",");
								if (para.length == parameters.size()) {
									for (int i = 0; i < parameters.size(); i++) {
										if (parameters.get(i).toString().contains(para[i]) && !(parameters.get(i)
												.toString().trim().contains("<" + para[i] + ">"))) {
											paraCount++;
										} else
											break;
									}
									if (paraCount == parameters.size()) {
										candidateDataClump.add(methodName);
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		return candidateDataClump;
	}

	public Set<String> getCandidateDataClumps() {
		return this.candidateDataClumps;
	}
}
