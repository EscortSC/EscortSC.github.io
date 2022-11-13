/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors;

import spirit.core.smells.CodeSmell;
import spirit.core.smells.FeatureEnvy;
import spirit.core.smells.detectors.CodeSmellDetector;
import spirit.core.smells.detectors.configurationByProject.FeatureEnvyDetectionConfiguration;
import spirit.metrics.storage.MethodMetrics;
import spirit.metrics.storage.NodeMetrics;

public class FeatureEnvyDetector extends CodeSmellDetector {
	private FeatureEnvyDetectionConfiguration metricConfiguration;

	public FeatureEnvyDetector(
			FeatureEnvyDetectionConfiguration metricConfiguration) {
		this.metricConfiguration = metricConfiguration;
	}

	public boolean codeSmellVerify(NodeMetrics methodMetric) {
		return methodMetric.getMetric("ATFD") != null
				&& (double) methodMetric.getMetric("ATFD").floatValue() > this.metricConfiguration
						.getATFD_Greater_Few().doubleValue()
				&& methodMetric.getMetric("LAA") != null
				&& (double) methodMetric.getMetric("LAA").floatValue() < this.metricConfiguration
						.getLAA_Less_OneThird().doubleValue()
				&& methodMetric.getMetric("FDP") != null
				&& (double) methodMetric.getMetric("FDP").floatValue() <= this.metricConfiguration
						.getFDP_LessEqual_FEW().doubleValue();
	}

	public CodeSmell codeSmellDetected(NodeMetrics methodMetric) {
		return new FeatureEnvy((MethodMetrics) methodMetric);
	}
}