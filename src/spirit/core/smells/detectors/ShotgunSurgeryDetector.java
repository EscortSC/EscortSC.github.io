/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors;

import java.util.Iterator;
import java.util.List;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

import spirit.core.smells.CodeSmell;
import spirit.core.smells.ShotgunSurgery;
import spirit.core.smells.detectors.CodeSmellDetector;
import spirit.core.smells.detectors.configurationByProject.ShotgunSurgeryDetectionConfiguration;
import spirit.metrics.storage.MethodMetrics;
import spirit.metrics.storage.NodeMetrics;

public class ShotgunSurgeryDetector extends CodeSmellDetector {
	private ShotgunSurgeryDetectionConfiguration metricConfiguration;

	public ShotgunSurgeryDetector(
			ShotgunSurgeryDetectionConfiguration metricConfiguration) {
		this.metricConfiguration = metricConfiguration;
	}

	public boolean codeSmellVerify(NodeMetrics methodMetric) {
		return this.isNonConstructorAndNonStatic(((MethodMetrics) methodMetric)
				.getDeclaration())
				&& methodMetric.getMetric("CC") != null
				&& (double) methodMetric.getMetric("CC").floatValue() >= this.metricConfiguration
						.getCC_GreaterEqual_MANY().doubleValue()
				&& methodMetric.getMetric("CM") != null
				&& (double) methodMetric.getMetric("CM").floatValue() > this.metricConfiguration
						.getCM_Greater_SMemCap().doubleValue();
	}

	public CodeSmell codeSmellDetected(NodeMetrics methodMetric) {
		return new ShotgunSurgery((MethodMetrics) methodMetric);
	}

	private boolean isNonConstructorAndNonStatic(MethodDeclaration method) {
		List modifiers = method.modifiers();
		boolean isStatic = false;
		Iterator arg4 = modifiers.iterator();

		while (arg4.hasNext()) {
			IExtendedModifier modifier = (IExtendedModifier) arg4.next();
			if (modifier.isModifier() && ((Modifier) modifier).isStatic()) {
				isStatic = true;
			}
		}

		if (!method.isConstructor() && !isStatic) {
			return true;
		} else {
			return false;
		}
	}
}