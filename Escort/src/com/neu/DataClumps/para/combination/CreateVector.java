package com.neu.DataClumps.para.combination;

import java.util.ArrayList;
import java.util.List;


public class CreateVector {
	class ItemVector {
		String vector;
		boolean selected;

		public ItemVector(String vector, boolean selected) {
			this.vector = vector;
			this.selected = selected;
		}

		public boolean getSelected() {
			return this.selected;
		}

		public String getVectorName() {
			return this.vector;
		}
	}

	private List<ItemVector> itemVectors;

	public CreateVector(String[] vectors) {
		itemVectors = new ArrayList<ItemVector>();
		ItemVector start = new ItemVector("Start", false);
		itemVectors.add(start);
		for (String vector : vectors) {
			ItemVector item1 = new ItemVector(vector, true);
			ItemVector item2 = new ItemVector(vector, false);
			itemVectors.add(item1);
			itemVectors.add(item2);
		}
		ItemVector end = new ItemVector("End", false);
		itemVectors.add(end);
	}

	public List<ItemVector> getVectors() {
		return this.itemVectors;
	}


}
