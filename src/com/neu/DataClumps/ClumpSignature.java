package com.neu.DataClumps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class ClumpSignature {
	private final int signature;
	private final Set<String> names;

	private ClumpSignature(List<String> ns) {
		int s = 0;
		for (String name : ns)
			s += name.hashCode();
		this.signature = s;
		this.names = new HashSet(ns);
	}

	public static List<ClumpSignature> from(List<String> names) {
		LinkedList strings = new LinkedList();
		for (String name : names) {
			strings.add(name);
		}
		if (names.size() < 2) {
			List sigs = new ArrayList(1);
			if (names.size() == 1)
				sigs.add(new ClumpSignature(strings));
			return sigs;
		}

		return combination(strings);
	}

	public static List<ClumpSignature> combination(List<String> strings) {
		return combination(strings.subList(0, 0), strings);
	}

	private static List<ClumpSignature> combination(List<String> prefix, List<String> rest) {
		List sigs = new LinkedList();
		if (rest.size() > 0) {
			List newPrefix = new LinkedList(prefix);
			newPrefix.add((String) rest.get(0));
			if (newPrefix.size() > 1) {
				sigs.add(new ClumpSignature(newPrefix));
			}
			sigs.addAll(combination(newPrefix, rest.subList(1, rest.size())));
			sigs.addAll(combination(prefix, rest.subList(1, rest.size())));
		}
		return sigs;
	}

	public boolean equals(Object o) {
		if (!(o instanceof ClumpSignature)) {
			return false;
		}

		return (((ClumpSignature) o).signature == this.signature);
	}

	public String toString() {
		return this.names.toString();
	}

	public int hashCode() {
		return this.signature;
	}

	public boolean contains(String identifier) {
		return this.names.contains(identifier);
	}

	public int size() {
		return this.names.size();
	}
	
	public Set<String> getNames() {
		return this.names;
	}
}
