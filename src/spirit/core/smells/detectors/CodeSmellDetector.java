/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors;

import spirit.core.smells.CodeSmell;
import spirit.metrics.storage.NodeMetrics;


public abstract class CodeSmellDetector {
	public abstract boolean codeSmellVerify(NodeMetrics arg0);

	public abstract CodeSmell codeSmellDetected(NodeMetrics arg0);
}