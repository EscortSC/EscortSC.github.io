/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors;

import java.util.List;

import spirit.core.smells.CodeSmell;
import spirit.core.smells.TraditionBreaker;
import spirit.core.smells.detectors.CodeSmellDetector;
import spirit.core.smells.detectors.configurationByProject.TraditionBreakerDetectionConfiguration;
import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.NodeMetrics;

public class TraditionBreakerDetector extends CodeSmellDetector {
	private TraditionBreakerDetectionConfiguration metricConfiguration;

	public TraditionBreakerDetector(
			TraditionBreakerDetectionConfiguration metricConfiguration) {
		this.metricConfiguration = metricConfiguration;
	}

	public boolean codeSmellVerify(NodeMetrics classMetrics) {
		return this.isChild(classMetrics)
				&& this.excessiveIncreaseOfChildClassInterface((ClassMetrics) classMetrics)
				&& this.childClassHasSubstantialSizeAndComplexity((ClassMetrics) classMetrics)
				&& this.parentClassIsNeitherSmallNorDumb((ClassMetrics) classMetrics);
	}

	public CodeSmell codeSmellDetected(NodeMetrics classMetrics) {
		return new TraditionBreaker((ClassMetrics) classMetrics);
	}

	public boolean excessiveIncreaseOfChildClassInterface(
			ClassMetrics classMetrics) {
		return classMetrics.getMetric("NAS") != null
				&& (double) classMetrics.getMetric("NAS").floatValue() >= this.metricConfiguration
						.getNAS_GreaterEqual_NOMAvg().doubleValue()
				&& classMetrics.getMetric("PNAS") != null
				&& (double) classMetrics.getMetric("PNAS").floatValue() >= this.metricConfiguration
						.getPNAS_GreaterEqual_TWO_THIRD().doubleValue();
	}

	public boolean childClassHasSubstantialSizeAndComplexity(
			ClassMetrics classMetrics) {
		return (classMetrics.getMetric("AMW") != null
				&& (double) classMetrics.getMetric("AMW").floatValue() > this.metricConfiguration
						.getAMW_Greater_AMWAvg().doubleValue() || classMetrics
				.getMetric("WMC") != null
				&& (double) classMetrics.getMetric("WMC").floatValue() >= this.metricConfiguration
						.getWMC_GreaterEqual_VeryHigh().doubleValue())
				&& classMetrics.getMetric("NOM") != null
				&& (double) classMetrics.getMetric("NOM").floatValue() >= this.metricConfiguration
						.getNOM_GreatherEqual_High().doubleValue();
	}

	public boolean parentClassIsNeitherSmallNorDumb(ClassMetrics classMetrics) {
		return classMetrics.getMetric("AMW") != null
				&& (double) classMetrics.getMetric("AMW").floatValue() > this.metricConfiguration
						.getAMW_Greater_AMWAvg_2().doubleValue()
				&& classMetrics.getMetric("WMC") != null
				&& (double) classMetrics.getMetric("WMC").floatValue() > this.metricConfiguration
						.getWMC_Greater_VeryHighDiv2().doubleValue()
				&& classMetrics.getMetric("NOM") != null
				&& (double) classMetrics.getMetric("NOM").floatValue() >= this.metricConfiguration
						.getNOM_GreatherEqual_HighDiv2().doubleValue();
	}

	private boolean isChild(NodeMetrics classMetrics) {
		List nameOfClasses = (List) classMetrics.getAttribute("nameOfClasses");
		return ((ClassMetrics) classMetrics).getDeclaration().resolveBinding()
				.getSuperclass() != null
				&& nameOfClasses.contains(((ClassMetrics) classMetrics)
						.getDeclaration().resolveBinding().getSuperclass()
						.getBinaryName());
	}
}