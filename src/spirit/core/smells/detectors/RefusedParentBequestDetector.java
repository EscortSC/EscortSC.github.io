/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors;

import java.util.List;

import com.jeantessier.dependencyfinder.gui.SaveFileAction;

import spirit.core.smells.CodeSmell;
import spirit.core.smells.RefusedParentBequest;
import spirit.core.smells.detectors.CodeSmellDetector;
import spirit.core.smells.detectors.configurationByProject.RefusedParentBequestDetectionConfiguration;
import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.NodeMetrics;

public class RefusedParentBequestDetector extends CodeSmellDetector {
	private RefusedParentBequestDetectionConfiguration metricConfiguration;

	public RefusedParentBequestDetector(

			RefusedParentBequestDetectionConfiguration metricConfiguration) {
		this.metricConfiguration = metricConfiguration;
	}

	public boolean codeSmellVerify(NodeMetrics classMetrics) {
		return this.isChild(classMetrics) && this.childClassIgnoresBequest((ClassMetrics) classMetrics)
				&& this.childClassIsNotSmallAndSimple((ClassMetrics) classMetrics);
	}

	public CodeSmell codeSmellDetected(NodeMetrics classMetrics) {
		return new RefusedParentBequest((ClassMetrics) classMetrics);
	}

	public boolean childClassIgnoresBequest(ClassMetrics classMetrics) {
		/*
		 * return classMetrics.getMetric("NProtM") != null && (double)
		 * classMetrics.getMetric("NProtM").floatValue() > this.metricConfiguration
		 * .getNProtM_Greater_Few().doubleValue() && classMetrics.getMetric("BUR") !=
		 * null && (double) classMetrics.getMetric("BUR").floatValue() <
		 * this.metricConfiguration .getBUR_Less_OneThird().doubleValue() ||
		 * classMetrics.getMetric("BOvR") != null && (double)
		 * classMetrics.getMetric("BOvR").floatValue() < this.metricConfiguration
		 * .getBOvR_Less_OneThird().doubleValue();
		 */
		return (double) classMetrics.getMetric("BOvR").floatValue() >0.33;
	}

	public boolean childClassIsNotSmallAndSimple(ClassMetrics classMetrics) {
		return (classMetrics.getMetric("AMW") != null
				&& (double) classMetrics.getMetric("AMW").floatValue() > this.metricConfiguration
						.getAMW_Greater_AMWAvg().doubleValue()
				|| classMetrics.getMetric("WMC") != null
						&& (double) classMetrics.getMetric("WMC").floatValue() > this.metricConfiguration
								.getWMC_Greater_WMCAvg().doubleValue())
				&& classMetrics.getMetric("NOM") != null
				&& (double) classMetrics.getMetric("NOM").floatValue() > this.metricConfiguration
						.getNOM_Greater_NOMAvg().doubleValue();
	}

	private boolean isChild(NodeMetrics classMetrics) {
		ClassMetrics node = (ClassMetrics) classMetrics;
		if(node.getDeclaration().getSuperclassType()!=null){
			return true;
		}
		return false;

	}
}