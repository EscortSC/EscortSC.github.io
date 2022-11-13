/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class NamesOfInvokedClasses implements IAttribute {
	List<String> nameOfClasses;

	public NamesOfInvokedClasses(List<String> nameOfClasses) {
		this.nameOfClasses = nameOfClasses;
	}

	public String getName() {
		return "ListOfClassInvoked";
	}

	public void calculate(ClassMetrics node) {
		ArrayList linvoked = new ArrayList();
		String nameOfClass = node.getDeclaration().resolveBinding()
				.getBinaryName();
		List lmethod = node.getMethodsMetrics();
		ArrayList lClassMethodInv = new ArrayList();
		ArrayList linvokedByMethod = new ArrayList();
		Iterator arg7 = lmethod.iterator();

		while (arg7.hasNext()) {
			MethodMetrics invoked = (MethodMetrics) arg7.next();
			linvokedByMethod.addAll((List) invoked
					.getAttribute("ListOfClassInvoked"));
			lClassMethodInv.addAll((List) invoked
					.getAttribute("ListOfMethodInvoked"));
		}

		if (linvokedByMethod != null) {
			linvokedByMethod.remove(nameOfClass);
			arg7 = linvokedByMethod.iterator();

			while (arg7.hasNext()) {
				String invoked1 = (String) arg7.next();
				if (!linvoked.contains(invoked1)
						&& this.nameOfClasses.contains(invoked1)) {
					linvoked.add(invoked1);
				}
			}
		}

		node.setAttribute(this.getName(), linvoked);
		node.setAttribute("ListOfMethodInvoked", lClassMethodInv);
	}

	public void calculate(MethodMetrics node) {
		List linvokedByMethod = (List) node.getAttribute("ListOfClassInvoked");

		for (int i = 0; i < linvokedByMethod.size(); ++i) {
			String name = (String) linvokedByMethod.get(i);
			if (!this.nameOfClasses.contains(name)) {
				linvokedByMethod.remove(name);
				--i;
			}
		}

	}
}