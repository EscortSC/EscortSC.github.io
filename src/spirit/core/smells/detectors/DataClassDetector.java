package spirit.core.smells.detectors;

import spirit.core.smells.CodeSmell;
import spirit.core.smells.DataClass;
import spirit.core.smells.detectors.CodeSmellDetector;
import spirit.core.smells.detectors.configurationByProject.DataClassDetectionConfiguration;
import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.NodeMetrics;

public class DataClassDetector extends CodeSmellDetector {
	private DataClassDetectionConfiguration metricConfiguration;

	public DataClassDetector(DataClassDetectionConfiguration metricConfiguration) {
		this.metricConfiguration = metricConfiguration;
	}

	public boolean codeSmellVerify(NodeMetrics classMetrics) {
		ClassMetrics cMetrics=(ClassMetrics)classMetrics;		
		if (classMetrics.getMetric("NOAM") != null
				&& classMetrics.getMetric("NOPA") != null) {
			int sum = classMetrics.getMetric("NOAM").intValue()
					+ classMetrics.getMetric("NOPA").intValue();
			if (classMetrics.getMetric("WOC") != null
					&& (double) classMetrics.getMetric("WOC").floatValue() < this.metricConfiguration
							.getWOC_Less_OneThird().doubleValue()
					&& ((double) sum > this.metricConfiguration
							.getNOAP_SOAP_Greater_Few().doubleValue()
							&& classMetrics.getMetric("WMC") != null
							&& (double) classMetrics.getMetric("WMC")
									.floatValue() >= this.metricConfiguration
									.getWMC_Less_High().doubleValue() || (double) sum > this.metricConfiguration
							.getNOAP_SOAP_Greater_Many().doubleValue()
							&& classMetrics.getMetric("WMC") != null
							&& (double) classMetrics.getMetric("WMC")
									.floatValue() >= this.metricConfiguration
									.getWMC_Less_VeryHigh().doubleValue())) {
				return true;
			}
		}

		return false;
	}

	public CodeSmell codeSmellDetected(NodeMetrics classMetrics) {
		return new DataClass((ClassMetrics) classMetrics);
	}
}