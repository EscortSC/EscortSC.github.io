/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors;

import java.util.Iterator;

import spirit.core.smells.BrainClass;
import spirit.core.smells.CodeSmell;
import spirit.core.smells.detectors.CodeSmellDetector;
import spirit.core.smells.detectors.configurationByProject.BrainClassDetectionConfiguration;
import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;
import spirit.metrics.storage.NodeMetrics;


public class BrainClassDetector extends CodeSmellDetector {
	private BrainClassDetectionConfiguration metricsConfiguration;

	public BrainClassDetector(
			BrainClassDetectionConfiguration metricsConfiguration) {
		this.metricsConfiguration = metricsConfiguration;
	}

	public boolean codeSmellVerify(NodeMetrics classMetrics) {
		boolean isGodclass = classMetrics.getAttribute("BM") != null
				? ((Boolean) classMetrics.getAttribute("BM")).booleanValue()
				: false;
		return !isGodclass
				&& (this.isVeryLarge(classMetrics) || this
						.isExtremelyLarge(classMetrics))
				&& this.isVeryComplexAndNonCohesive(classMetrics);
	}

	public CodeSmell codeSmellDetected(NodeMetrics classMetrics) {
		return new BrainClass((ClassMetrics) classMetrics);
	}

	public int countBrainMethods(NodeMetrics classMetrics) {
		int countBrainMethods = 0;
		Iterator arg3 = ((ClassMetrics) classMetrics).getMethodsMetrics()
				.iterator();

		while (arg3.hasNext()) {
			MethodMetrics methodMetrics = (MethodMetrics) arg3.next();
			if (methodMetrics.getAttribute("BM") != null
					&& ((Boolean) methodMetrics.getAttribute("BM"))
							.booleanValue()) {
				++countBrainMethods;
			}
		}

		return countBrainMethods;
	}

	private boolean isVeryLarge(NodeMetrics classMetrics) {
		return classMetrics.getMetric("LOC") != null
				&& (double) classMetrics.getMetric("LOC").floatValue() >= this.metricsConfiguration
						.getLOC_GreaterEqual_VeryHigh().doubleValue()
				&& this.countBrainMethods(classMetrics) > 1;
	}

	private boolean isExtremelyLarge(NodeMetrics classMetrics) {
		return classMetrics.getMetric("LOC") != null
				&& (double) classMetrics.getMetric("LOC").floatValue() >= this.metricsConfiguration
						.getLOC_GreaterEqual_2xVeryHigh().doubleValue()
				&& classMetrics.getMetric("WMC") != null
				&& (double) classMetrics.getMetric("WMC").floatValue() >= this.metricsConfiguration
						.getWMC_GreaterEqual_2xVeryHigh().doubleValue()
				&& this.countBrainMethods(classMetrics) == 1;
	}

	private boolean isVeryComplexAndNonCohesive(NodeMetrics classMetrics) {
		return classMetrics.getMetric("TCC") != null
				&& (double) classMetrics.getMetric("TCC").floatValue() < this.metricsConfiguration
						.getTCC_Less_Half().doubleValue()
				&& classMetrics.getMetric("WMC") != null
				&& (double) classMetrics.getMetric("WMC").floatValue() >= this.metricsConfiguration
						.getWMC_GreaterEqual_VeryHigh().doubleValue();
	}
}