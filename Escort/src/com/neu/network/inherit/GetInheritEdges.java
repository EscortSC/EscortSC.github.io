package com.neu.network.inherit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.Refactor.classparser.ClassObject;
import com.jeantessier.escort.gui.SaveFileAction;

public class GetInheritEdges {
	private List<String> inheritEdges;
	private List<String> newAddedVectors = new ArrayList<String>();

	public GetInheritEdges(String proName) {
		this.inheritEdges = setEdges();
		this.printDetails("InheritEdges", proName, inheritEdges);
	}

	private void printDetails(String fileName, String proName, List<String> details) {
		try {
			File f = new File("D:\\smell\\output\\" + proName + "\\" + fileName + ".txt");
			f.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(f));
			for (String item : details) {
				out.write(item + '\n');
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<String> setEdges() {
		List<String> bounds = new ArrayList<String>();
		for (ClassObject co : SaveFileAction.classesArrList) {
			bounds.add(co.getName()+","+co.getInboundClassList().size() + "," + co.getOutboundClassList().size());
		}
		return bounds;
	}

	private List<String> reSetVectors(List<String> vectors) {
		List<String> reSetVectors = new ArrayList<String>();
		for (String vector : vectors) {
			reSetVectors.add(vector + ",0");
		}
		reSetVectors.addAll(newAddedVectors);
		return reSetVectors;
	}

}
