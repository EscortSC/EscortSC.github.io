package com.neu.MessageChains;

import java.util.*;

public class OToMoreMap<K, V> {

	/**
	 * one to more
	 */

	private List<K> mkey;
	private List<List<V>> mvlaue;

	public OToMoreMap() {
		mkey = new ArrayList<K>();
		mvlaue = new ArrayList<List<V>>();

	}

	/*
	 ** add element
	 */
	public void put(K key, V value) {
		List list = new ArrayList<V>();
		list.add(value);
		if (containsKey(key)) {
			mvlaue.get(mkey.indexOf(key)).add(value);
		} else {
			mkey.add(key);
			mvlaue.add(list);
		}
	}


	public K getkey(int i) {
		return mkey.get(i);
	}

	
	public List<V> getvalue(int i) {
		return mvlaue.get(i);
	}

	
	public Map<K, List<V>> get(int i) {
		Map<K, List<V>> map = new HashMap<>();
		map.put(mkey.get(i), mvlaue.get(i));
		return map;
	}

	
	public Map<K, List<V>> getAll() {
		Map<K, List<V>> map = new HashMap<>();
		for (int i = 0; i < mkey.size(); i++) {
			map.put(mkey.get(i), mvlaue.get(i));
		}
		return map;
	}


	public boolean containsKey(K key) {
		if (mkey.contains(key)) {
			return true;
		} else {
			return false;
		}
	}

	
	public long getSize() {
		return mkey.size();
	}

	
	public long getvalueSize(K key) {

		// Map<K, List<V>> map = new HashMap<>();
		return getAll().get(key).size();
	}

	/*
	 * remove
	 */
	public boolean removeAll() {
		mkey.clear();
		mvlaue.clear();
		if (mkey.isEmpty() && mvlaue.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

//	public static void main(String[] agrs) {
//		OToMoreMap<String, String> moreMap = new OToMoreMap<>();
//		// Set<String> tSet=new HashSet<String>();
//		moreMap.put("ss", "1");
//		moreMap.put("ss", "2");
//		moreMap.put("ss", "3");
//		moreMap.put("zz", "2");
////        for (int i = 0; i < moreMap.getSize(); i++) {
////        	tSet.add(moreMap.getkey(i));
////        }
//		// System.out.println(moreMap.getAll());
////        for(String t:tSet) {
//		System.out.print(moreMap.getvalueSize("ss"));
////        }
//
//	}

}
