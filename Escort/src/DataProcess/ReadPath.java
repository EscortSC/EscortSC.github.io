package DataProcess;

import java.io.File;
import java.util.ArrayList;

public class ReadPath {
	public static ArrayList<String> paths = new ArrayList<String>();

	public ReadPath(String route) {
		getAllFileName(route);
	}

	public ArrayList<String> getPath() {
		return this.paths;
	}

	private void getAllFileName(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		for (File a : files) {
			if (a.isDirectory()) {
				getAllFileName(a.getAbsolutePath());
			} else if (!a.isDirectory() && a.getName() != null && a.getName().endsWith(".java")) {
				paths.add(a.getAbsolutePath());

			}
		}
	}

}
