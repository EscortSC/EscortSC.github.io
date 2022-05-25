package spirit.metrics.calculate;

import java.util.Iterator;
import java.util.List;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class CouplingDispersion implements IAttribute{

	@Override
	public void calculate(ClassMetrics node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void calculate(MethodMetrics node) {
		// TODO Auto-generated method stub
		List ListOfClassInvoked = (List) node
				.getAttribute("ListOfClassInvoked");
		
		List superClasses = (List) node.getClassMetrics().getAttribute("SC");
		int numberOfclasses = 0;
		Iterator arg5 = ListOfClassInvoked.iterator();

		while (arg5.hasNext()) {
			String cdisp = (String) arg5.next();
			if (!superClasses.contains(cdisp)) {
				++numberOfclasses;
			}
		}

		if (node.getMetric("CINT").floatValue() != 0.0F) {
			float arg6 = (float) numberOfclasses
					/ node.getMetric("CINT").floatValue();
			node.setMetric(this.getName(), arg6);
		} else {
			node.setMetric(this.getName(), 1.0F);
		}


	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "CDISP";
	}

}
