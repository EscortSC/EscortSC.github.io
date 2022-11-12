package spirit.metrics.storage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public abstract class NodeMetrics {
	private HashMap<String, Float> metrics = new HashMap();
	private HashMap<String, Object> attributes = new HashMap();

	public HashMap<String, Float> getMetrics() {
		return this.metrics;
	}

	public void setMetrics(HashMap<String, Float> metrics) {
		this.metrics = metrics;
	}

	public Float getMetric(String metric) {
		return (Float) this.metrics.get(metric);
	}

	public void setMetric(String metric, float valor) {
		this.metrics.put(metric, Float.valueOf(valor));
	}

	public Object getAttribute(String name) {
		return this.attributes.get(name);
	}

	public void setAttribute(String name, Object attribute) {
		this.attributes.put(name, attribute);
	}

	public void printDebug() {
		Set keys = this.metrics.keySet();
		System.out.println("----- NodeMetrics -----");
		Iterator arg2 = keys.iterator();

		while (arg2.hasNext()) {
			String key = (String) arg2.next();
			System.out.println(key + " : " + this.metrics.get(key));
		}

		System.out.println("----- ----------- -----");
	}

	public void printDebugAttr() {
		Set keys = this.metrics.keySet();
		System.out.println("----- NodeAttributes -----");
		Iterator arg2 = keys.iterator();

		while (arg2.hasNext()) {
			String key = (String) arg2.next();
			System.out.println(key + " : " + this.attributes.get(key));
		}

		System.out.println("----- ----------- -----");
	}

}
