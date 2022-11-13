/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors;

import spirit.core.smells.CodeSmell;
import spirit.core.smells.GodClass;
import spirit.core.smells.detectors.CodeSmellDetector;
import spirit.core.smells.detectors.configurationByProject.GodClassDetectionConfiguration;
import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.NodeMetrics;

public class GodClassDetector extends CodeSmellDetector {
	private GodClassDetectionConfiguration metricConfiguration;

	public GodClassDetector(GodClassDetectionConfiguration metricConfiguration) {
		this.metricConfiguration = metricConfiguration;
	}

	public boolean codeSmellVerify(NodeMetrics classMetrics) {
		if (classMetrics.getMetric("ATFD") != null
				&& (double) classMetrics.getMetric("ATFD").floatValue() > this.metricConfiguration
						.getATFD_Greater_FEW().doubleValue()
				&& classMetrics.getMetric("TCC") != null
				&& (double) classMetrics.getMetric("TCC").floatValue() < this.metricConfiguration
						.getTCC_Less_OneThird().doubleValue()
				&& classMetrics.getMetric("WMC") != null
				&& (double) classMetrics.getMetric("WMC").floatValue() >= this.metricConfiguration
						.getWMC_GreaterEqual_VeryHigh().doubleValue()) {
			classMetrics.setAttribute("GC", Boolean.valueOf(true));
			return true;
		} else {
			return false;
		}
	}

	public CodeSmell codeSmellDetected(NodeMetrics classMetrics) {
		return new GodClass((ClassMetrics) classMetrics);
	}
}