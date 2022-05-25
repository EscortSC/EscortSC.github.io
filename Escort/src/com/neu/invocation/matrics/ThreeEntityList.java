package com.neu.invocation.matrics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.jeantessier.escort.gui.SaveFileAction;

public abstract class ThreeEntityList {
	abstract List<List<String>> setInvokeList();
	abstract List<List<String>> setInherianceList();
	abstract List<List<String>> setAllList();

	void printThreeEntity(String pathName, List<List<String>> allEntityAndBounds) {
		try {
			String relativePath = SaveFileAction.relativePath;
			File writename = new File("D:\\smell\\output\\" + relativePath + "\\" + pathName + ".txt");
			if(writename.exists()) {
				writename.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(writename,true));
			for (List<String> entityAndBounds : allEntityAndBounds) {
				for(String value:entityAndBounds) {
					out.write(value+";");
				}
				out.write('\n');
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
