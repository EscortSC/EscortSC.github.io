package com.neu.DataClumps.newer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.DataClumps.para.combination.ParseGraph;
import com.neu.calculate.CObject;
import com.neu.calculate.ClaObject;
import com.neu.calculate.PackageObject;
import com.neu.invocation.matrics.GetAllMethods;

import DataProcess.JdtAstUtil;

public class DataClumpsDetector {
	private List<String> AllMethods;
	private Set<String> candidateDataClumps = new HashSet<String>();

	public DataClumpsDetector(List<String> paths) {
		AllMethods = GetAllMethods.allMethodNames;
		this.searchCandidateDataClumps(paths);
		// this.printDetail();
	}

	public void searchCandidateDataClumps(List<String> paths) {
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
			Map<Integer, Set<MethodDeclaration>> clumpColloction = this.checkPerClass(co.getMethods());
			Set<MethodDeclaration> validMethods = new HashSet<MethodDeclaration>();
			for (Set<MethodDeclaration> value : clumpColloction.values()) {
				if (value.size() > 2) {
					validMethods.addAll(value);
				}
			}
			for (MethodDeclaration method : validMethods) {
				String fullMethodName = className + "." + method.getName().toString();
				List parameters = method.parameters();
				String fullPara = "";
				Iterator itera = method.parameters().iterator();
				while (itera.hasNext()) {
					fullPara = fullPara + "," + itera.next().toString();
				}
				for (String methodName : AllMethods) {
					if (methodName.startsWith(fullMethodName)) {
						int paraCount = 0;
						int firstKH = methodName.indexOf("(");
						int tailKH = methodName.indexOf(")");
						String paraList = methodName.substring(firstKH + 1, tailKH);
						String[] para = paraList.split(",");
						if (para.length == parameters.size()) {
							for (int i = 0; i < parameters.size(); i++) {
								if (parameters.get(i).toString().contains(para[i])
										&& (!parameters.get(i).toString().trim().contains("<" + para[i] + ">"))) {
									paraCount++;
								} else
									break;
							}
						}
						if (paraCount == parameters.size()) {
							candidateDataClumps.add(methodName);
							System.out.println("DataClumps;" + methodName);
						}
					}
				}
			}
		}
		System.out.println("finish");
	}

	private void printDetail() {
		for (String dataClumps : candidateDataClumps) {
			System.out.println("DataClumps;" + dataClumps);
		}
	}

	private Map<Integer, Set<MethodDeclaration>> checkPerClass(List<MethodDeclaration> methods) {
		Map<Integer, Set<MethodDeclaration>> clumpColloction = new HashMap<Integer, Set<MethodDeclaration>>();
		for (MethodDeclaration method : methods) {
			List<ClumpSignature> sigs = this.setParaComGroup(method);
			for (ClumpSignature sig : sigs) {
				Set<MethodDeclaration> itemMethods = clumpColloction.get(sig.getInt());
				if (itemMethods == null) {
					itemMethods = new HashSet<MethodDeclaration>();
				}
				itemMethods.add(method);
				clumpColloction.put(sig.getInt(), itemMethods);
			}
		}
		return clumpColloction;
	}

	private List<ClumpSignature> setParaComGroup(MethodDeclaration method) {
		List<ClumpSignature> sigs = new LinkedList<ClumpSignature>();
		if (method.parameters().size() <= 3)
			return sigs;
		Iterator itera = method.parameters().iterator();
		String[] paraList = new String[method.parameters().size()];
		int count = 0;
		while (itera.hasNext()) {
			String para = itera.next().toString();
			int point = para.lastIndexOf(" ");
			paraList[count++] = para.substring(0, point);
		}
		ParseGraph pg = new ParseGraph(paraList);
		List<List<String>> paraComGroup = pg.getAllPaths();
		for (List<String> itemParaList : paraComGroup) {
			ClumpSignature clump = new ClumpSignature(itemParaList);
			sigs.add(clump);
		}
		return sigs;
	}

	class ClumpSignature {
		private int signature;
		private List<String> names;

		public int getInt() {
			return signature;
		}

		private ClumpSignature(List<String> ns) {
			int s = 0;
			for (String name : ns)
				s += name.hashCode();
			this.signature = s;
			this.names = ns;
		}
	}

}
