/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors;

import spirit.core.smells.CodeSmell;
import spirit.core.smells.IntensiveCoupling;
import spirit.core.smells.detectors.CodeSmellDetector;
import spirit.core.smells.detectors.configurationByProject.IntensiveCouplingDetectionConfiguration;
import spirit.metrics.storage.MethodMetrics;
import spirit.metrics.storage.NodeMetrics;

public class IntensiveCouplingDetector extends CodeSmellDetector {
	private IntensiveCouplingDetectionConfiguration metricConfiguration;

	public IntensiveCouplingDetector(
			IntensiveCouplingDetectionConfiguration metricConfiguration) {
		this.metricConfiguration = metricConfiguration;
	}

	public boolean codeSmellVerify(NodeMetrics methodMetric) {
		return methodMetric.getMetric("MNL") != null
				&& (double) methodMetric.getMetric("MNL").floatValue() > this.metricConfiguration
						.getMAXNESTING_Greater_SHALLOW().doubleValue()
				&& (methodMetric.getMetric("CDISP") != null
						&& (double) methodMetric.getMetric("CDISP")
								.floatValue() < this.metricConfiguration
								.getCDISP_Less_OneQuarter().doubleValue()
						&& methodMetric.getMetric("CINT") != null
						&& (double) methodMetric.getMetric("CINT").floatValue() > this.metricConfiguration
								.getCINT_Greater_Few().doubleValue() || methodMetric
						.getMetric("CDISP") != null
						&& (double) methodMetric.getMetric("CDISP")
								.floatValue() < this.metricConfiguration
								.getCDISP_Less_Half().doubleValue()
						&& methodMetric.getMetric("CINT") != null
						&& (double) methodMetric.getMetric("CINT").floatValue() > this.metricConfiguration
								.getCINT_Greater_SMemCap().doubleValue());
	}

	public CodeSmell codeSmellDetected(NodeMetrics methodMetric) {
		return new IntensiveCoupling((MethodMetrics) methodMetric);
	}
}