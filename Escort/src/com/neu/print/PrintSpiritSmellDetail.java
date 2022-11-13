package com.neu.print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.calculate.CObject;
import com.neu.calculate.ClaObject;
import com.neu.calculate.PackageObject;

import spirit.metrics.analizer.SpiritVisitor;
import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class PrintSpiritSmellDetail {
	private HashMap<TypeDeclaration, ClassMetrics> classesMetrics = SpiritVisitor.classesMetrics;

	public PrintSpiritSmellDetail() {
	}

	private List<String> printCLevelMetrics(List<String> metrics) {
		List<String> clMetrics = new ArrayList<>();
		for (Entry<TypeDeclaration, ClassMetrics> value : classesMetrics.entrySet()) {
			String me = "";
			ClaObject co = new ClaObject();
			ASTNode self = value.getKey();
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
			me = className;
			for (String metric : metrics) {
				me = me + "," + value.getValue().getMetric(metric);
			}
			clMetrics.add(me);
		}
		return clMetrics;
	}

	private List<String> printMLevelMetrics(List<String> metrics) {
		List<String> mlMetrics = new ArrayList<>();
		for (Entry<TypeDeclaration, ClassMetrics> value : classesMetrics.entrySet()) {
			ClaObject co = new ClaObject();
			ASTNode self = value.getKey();
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
			List<MethodMetrics> methodMetrics = value.getValue().getMethodsMetrics();
			if (methodMetrics.size() == 0)
				continue;
			int indexLaa = 0, indexATFD = 0, indexFDP = 0;
			double minLaa = Integer.MAX_VALUE;
			double minFDP = Integer.MAX_VALUE;
			double maxATFD = Integer.MIN_VALUE;

			for (int i = 0; i < methodMetrics.size(); i++) {
				double laa = methodMetrics.get(i).getMetric("LAA");
				double atfd = methodMetrics.get(i).getMetric("ATFD");
				double fdp = methodMetrics.get(i).getMetric("FDP");
				if (laa < minLaa) {
					minLaa = laa;
					indexLaa = i;
				}
				if (atfd > maxATFD) {
					maxATFD = atfd;
					indexATFD = i;
				}
				if (fdp < minFDP) {
					minFDP = fdp;
					indexFDP = i;
				}
			}
			double meanLAA = (methodMetrics.get(indexFDP).getMetric("LAA")
					+ methodMetrics.get(indexATFD).getMetric("LAA") + methodMetrics.get(indexLaa).getMetric("LAA")) / 3;
			double meanFDP = (methodMetrics.get(indexFDP).getMetric("FDP")
					+ methodMetrics.get(indexATFD).getMetric("FDP") + methodMetrics.get(indexLaa).getMetric("FDP")) / 3;
			double meanATFD = (methodMetrics.get(indexFDP).getMetric("ATFD")
					+ methodMetrics.get(indexATFD).getMetric("ATFD") + methodMetrics.get(indexLaa).getMetric("ATFD"))
					/ 3;
			System.out.println(
					className + "," + value.getValue().getMetric("ATFD") + "," + value.getValue().getMetric("TCC") + ","
							+ value.getValue().getMetric("WMC") + "," + meanLAA + "," + meanATFD + "," + meanFDP);
		}
		return mlMetrics;
	}

}
