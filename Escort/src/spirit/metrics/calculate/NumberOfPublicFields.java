/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.Iterator;
import java.util.List;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class NumberOfPublicFields implements IAttribute {
	public void calculate(ClassMetrics node) {
		TypeDeclaration declaration = node.getDeclaration();
		FieldDeclaration[] fields = declaration.getFields();
		int publicAtt = 0;
		if (fields != null) {
			FieldDeclaration[] arg7 = fields;
			int arg6 = fields.length;

			for (int arg5 = 0; arg5 < arg6; ++arg5) {
				FieldDeclaration field = arg7[arg5];
				List modifiers = field.modifiers();
				Iterator arg10 = modifiers.iterator();

				while (arg10.hasNext()) {
					IExtendedModifier modifier = (IExtendedModifier) arg10
							.next();
					if (modifier.isModifier()
							&& ((Modifier) modifier).isPublic()) {
						++publicAtt;
					}
				}
			}
		}

		node.setMetric(this.getName(), (float) publicAtt);
	}

	public String getName() {
		return "NOPA";
	}

	public void calculate(MethodMetrics node) {
	}
}