package com.neu.codeSmells;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.Refactor.classparser.Feature;
import com.neu.codeSmell.calculate.CObject;
import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.PackageObject;
import com.neu.invocation.matrics.GetAllMethods;

import DataProcess.JdtAstUtil;

public class MessageChain {
	private List<String> allFeatureNames;
	private List<Feature> allFeatures;
	private HashMap<String, Integer> methodChains;
	private ArrayList<MethodDeclaration> analyzedMethods;
	private ArrayList<String> candidateMessageChains;
	private Queue<String> messageQ;
	private List isUnique;
	private List isNumber;

	public MessageChain() {
		candidateMessageChains = new ArrayList<String>();
		allFeatureNames = GetAllMethods.allMethodNames;
		allFeatures = GetAllMethods.allFeatures;
	}

	public ArrayList<String> searchMessageChain(ArrayList<String> paths) {
		this.getMethodChains(paths);
		this.computer(methodChains);
		// this.colculate(methodChains);
		return this.candidateMessageChains;
	}

	private HashMap<String, Integer> getMethodChains(ArrayList<String> paths) {
		methodChains = new HashMap<String, Integer>();
		for (String path : paths) {
			CompilationUnit comp1 = JdtAstUtil.getCompilationUnit(path);
			ClaObject dv1 = new ClaObject();
			comp1.accept(dv1);
			if (dv1.getType() != null) {
				TypeDeclaration type = dv1.getType();
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
				for (MethodDeclaration node : dv1.getMethods())
					if ((!node.getName().toString().equals("execute")) && (!node.getName().toString().equals("init"))
							&& (!node.getName().toString().equals("printElementDecl"))
							&& (!node.getName().toString().contains("Compile"))
							&& (!node.getName().toString().contains("copyFilesToDestination"))
							&& (!node.getName().toString().equals("testSupportsCharacters"))) {
						Iterator it = node.parameters().iterator();
						String parameters = "(";
						while (it.hasNext()) {
							String name = it.next().toString();
							String[] str = name.split(" ");
							parameters = parameters + str[0] + ",";
						}
						if (parameters.length() > 1)
							parameters = parameters.substring(0, parameters.length() - 1);
						parameters = parameters + ")";
						String methodName = className + "." + node.getName().toString() + parameters;
						int count = this.analyzeMethod(methodName);
						if (count > 8) {
							methodChains.put(methodName, count);
						}
					}
			}
		}
		return methodChains;
	}

	private int analyzeMethod(String methodName) {
		messageQ = new LinkedList<String>();
		isUnique = new ArrayList<String>();
		isNumber = new ArrayList<Integer>();
		messageQ.offer(methodName);
		isUnique.add(methodName);
		isNumber.add(1);
		while (messageQ.peek() != null) {
			String method = messageQ.remove();
			for (int i = 0; i < allFeatureNames.size(); i++) {
				if (allFeatureNames.get(i).equals(method)) {

					Feature fe = allFeatures.get(i);
					List<String> outBounds = fe.getOutboundFeatureList();
					List<String> outTemp = new ArrayList<String>();
					for (String str : outBounds) {
						outTemp.add(str);
					}
					for (String outBound : outTemp) {
						if (!outBound.contains("(") || isUnique.contains(outBound)) {
							outBounds.remove(outBound);
						}
					}
					isNumber.add(outBounds.size());
					for (String outBound : outBounds) {
						String paramenters = "";
						String str1[] = fe.getName().split("\\(");
						String str2[] = str1[1].split("\\)");
						if (str2.length > 0) {
							String str3[] = str2[0].split("\\, ");
							for (int k = 0; k < str3.length; k++) {
								String str4[] = str3[k].split("\\.");
								paramenters = paramenters + str4[str4.length - 1] + ",";
							}

							paramenters = paramenters.substring(0, paramenters.length() - 1);
						}
						messageQ.add(str1[0] + "(" + paramenters + ")");
						isUnique.add(outBound);
					}

				}
			}
		}
		return isUnique.size();

	}

	private int getDeepLevel(int counter, List<Integer> isNum) {
		int deepLevel = 0;
		int[] shuzu = new int[isNum.size()];
		for (int t = 0; t < isNum.size(); t++) {
			shuzu[t] = isNum.get(t);
		}
		if (isNum.size() != 0) {
			int i = 0;
			int sum = 0;
			int temp = shuzu[0];
			while (sum < counter && temp < shuzu.length) {
				sum = 0;
				for (; i < temp; i++) {
					sum += shuzu[i];
				}
				deepLevel++;
				i = temp;
				temp = sum + i;

			}
		}

		return deepLevel;

	}

	public void computer(HashMap<String, Integer> methodChains) {
		int max = max(methodChains);
		if (max != -1) {
			double upperHinge = upperHinge(methodChains);
			for (Entry<String, Integer> value : methodChains.entrySet()) {
				if ((value.getValue() > upperHinge) && (value.getValue() < max)) {
					candidateMessageChains.add(value.getKey());
				}
			}
		}
	}

	public void colculate(HashMap<String, Integer> methodChains) {
		for (Entry<String, Integer> value : methodChains.entrySet()) {
			if (value.getValue() > 5)
				candidateMessageChains.add(value.getKey());
		}
	}

	private Double median(ArrayList<Integer> values) {
		Collections.sort(values);
		if (values.size() == 0)
			return 0.0;
		else if ((values.size() % 2 == 0))
			return (double) ((values.get((values.size() / 2) - 1) + (values.get(values.size() / 2)))) / 2;
		else
			return (double) (values.get(values.size() / 2));
	}

	private int max(HashMap<String, Integer> methodChains) {
		Vector<Integer> valuesForAnalysis = new Vector<Integer>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (int v : methodChains.values())
			valuesForAnalysis.add(v);

		for (Integer i : valuesForAnalysis)
			values.add(i);

		if (values.size() > 0)
			return Collections.max(values);
		else
			return -1;
	}

	private Double upperHinge(HashMap<String, Integer> methodChains) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		ArrayList<Integer> valuesForUpperHinge = new ArrayList<Integer>();

		Vector<Integer> valuesForAnalysis = new Vector<Integer>();

		for (int v : methodChains.values()) {
			valuesForAnalysis.add(v);
		}

		for (Integer i : valuesForAnalysis)
			values.add(i);

		int max = max(methodChains);
		double median = median(values);

		for (Integer value : valuesForAnalysis)
			if ((value > median) && (value < max))
				valuesForUpperHinge.add(value);

		return median(valuesForUpperHinge);
	}

}
