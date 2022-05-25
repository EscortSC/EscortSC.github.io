package spirit.metrics.calculate;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public interface IAttribute {
	void calculate(ClassMetrics node);

	void calculate(MethodMetrics node);

	String getName();


}
