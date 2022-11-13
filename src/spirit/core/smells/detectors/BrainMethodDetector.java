/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors;

import spirit.core.smells.BrainMethod;
import spirit.core.smells.CodeSmell;
import spirit.core.smells.detectors.CodeSmellDetector;
import spirit.core.smells.detectors.configurationByProject.BrainMethodDetectionConfiguration;
import spirit.metrics.storage.MethodMetrics;
import spirit.metrics.storage.NodeMetrics;

public class BrainMethodDetector extends CodeSmellDetector {
	private BrainMethodDetectionConfiguration metricsConfiguration;

	public BrainMethodDetector(
			BrainMethodDetectionConfiguration metricsConfiguration) {
		this.metricsConfiguration = metricsConfiguration;
	}

	public boolean codeSmellVerify(NodeMetrics methodMetric) {
		if (methodMetric.getMetric("LOC") != null
				&& (double) methodMetric.getMetric("LOC").floatValue() > this.metricsConfiguration
						.getLOC_Greater_VeryHigh().doubleValue()
				&& methodMetric.getMetric("MNL") != null
				&& (double) methodMetric.getMetric("MNL").floatValue() >= this.metricsConfiguration
						.getMAXNESTING_GreaterEqual_DEEP().doubleValue()
				&& methodMetric.getMetric("WMC") != null
				&& (double) methodMetric.getMetric("WMC").floatValue() >= this.metricsConfiguration
						.getWMC_GreaterEqual_Many().doubleValue()
				&& methodMetric.getMetric("NOF") != null
				&& (double) methodMetric.getMetric("NOF").floatValue() >= this.metricsConfiguration
						.getNOF_GreaterEqual_SMemCap().doubleValue()) {
			methodMetric.setAttribute("BM", Boolean.valueOf(true));
			return true;
		} else {
			return false;
		}
	}

	public CodeSmell codeSmellDetected(NodeMetrics methodMetric) {
		return new BrainMethod((MethodMetrics) methodMetric);
	}
}