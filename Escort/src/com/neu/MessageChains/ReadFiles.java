package com.neu.MessageChains;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ReadFiles {
	/**
	 * Recursively reads all files under the file path
	 * 
	 * @param path
	 * @return
	 */

	public List<File> readFiles1(String path) {
		File file = new File(path);
		File[] fileList = file.listFiles();
		List<File> wjList = new ArrayList<File>();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				wjList.add(fileList[i]);
			}
		}
		//wjList = sortFileByName(wjList, "asc");
		return (wjList);
	}

	/**
	 *
	 * @param files
	 * @param orderStr order:asc,des,Case insensitive
	 * @return
	 */
	public List<File> sortFileByName(List<File> files, final String orderStr) {
		if (!orderStr.equalsIgnoreCase("asc") && orderStr.equalsIgnoreCase("desc")) {
			return files;
		}
		File[] files1 = files.toArray(new File[0]);
		Arrays.sort(files1, new Comparator<File>() {
			public int compare(File o1, File o2) {
				int n1 = extractNumber(o1.getName());
				int n2 = extractNumber(o2.getName());
				if (orderStr == null || orderStr.length() < 1 || orderStr.equalsIgnoreCase("asc")) {
					return n1 - n2;
				} else {
					// descending order 
					return n2 - n1;
				}
			}
		});
		return new ArrayList<File>(Arrays.asList(files1));
	}

	private static int extractNumber(String name) {
		int i;
		try {
			String[] number = name.split("-");
			i = Integer.parseInt(number[0]);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	/**
	 * Gets the class names of all files
	 * 
	 * @param filename
	 * @return
	 */

	public List<LinkedHashSet<String>> getALLClassSet(List<File> filename) {
		List<LinkedHashSet<String>> getAllClassList = new ArrayList<LinkedHashSet<String>>();
		LinkedHashSet<String> classSet1 = new LinkedHashSet<String>();
		for (File file : filename) {
			classSet1 = getSigleClassSet(file);
			getAllClassList.add(classSet1);
		}
		return getAllClassList;
	}

	/**
	 * Gets the class names of all files
	 * 
	 * @param file
	 * @return
	 */

	public LinkedHashSet<String> getSigleClassSet(File file) {
		LinkedHashSet<String> classSet = new LinkedHashSet<String>();
		Boolean boo = file.exists() && file.isFile();
		// System.out.println(boo);
		if (boo) {
			BufferedReader bufferedReader = null;
			try {
				
				bufferedReader = new BufferedReader(new FileReader(file));
				String linetxt = null;
				
				while ((linetxt = bufferedReader.readLine()) != null) {
					String[] names = linetxt.split("\\.");
					String temp = "";
					for (int i = 0; i < names.length - 1; i++) {
						temp = temp + names[i] + ".";
					}
					temp = temp.substring(0, temp.length() - 1);
					classSet.add(temp);
				}
			} catch (Exception e) {
				System.out.println("Error reading file content");
				e.printStackTrace();
			} finally {
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			System.out.println("The specified file could not be found");
		}
		return classSet;
	}

	/**
	 * 
	 * 
	 * @param file
	 * @return
	 */
	public OToMoreMap<String, String> getClassTOMethod(File file) {
		OToMoreMap<String, String> moreMap = new OToMoreMap<>();
		Boolean boo = file.exists() && file.isFile();
		// System.out.println(boo);
		if (boo) {
			BufferedReader bufferedReader = null;
			try {
				
				bufferedReader = new BufferedReader(new FileReader(file));
				String linetxt = null;
				
				while ((linetxt = bufferedReader.readLine()) != null) {
					String[] names = linetxt.split("\\.");
					String temp = "";
					for (int i = 0; i < names.length - 1; i++) {
						temp = temp + names[i] + ".";
					}
					temp = temp.substring(0, temp.length() - 1);
					moreMap.put(temp, names[names.length - 1]);
				}
			} catch (Exception e) {
				System.out.println("Error reading file content");
				e.printStackTrace();
			} finally {
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			System.out.println("The specified file could not be found");
		}
		return moreMap;
	}

	/**
	 * Get the class and method names of all files
	 * 
	 * @param filename
	 * @return
	 */

	public List<OToMoreMap<String, String>> getALLClassTOMethod(List<File> filename) {
		List<OToMoreMap<String, String>> getallclasstomethodList = new ArrayList<OToMoreMap<String, String>>();
		OToMoreMap<String, String> classtomethod = new OToMoreMap<>();
		for (File file : filename) {
			classtomethod = getClassTOMethod(file);
			getallclasstomethodList.add(classtomethod);
		}
		return getallclasstomethodList;
	}

	/**
	 * read file
	 * 
	 * @param filename
	 * @return
	 */
	public Set<String> getContect(File file) {
		Set<String> classnameSet = new HashSet<String>();
	
			Boolean boo = file.exists() && file.isFile();
			if (boo) {
				BufferedReader bufferedReader = null;
				try {
					
					bufferedReader = new BufferedReader(new FileReader(file));
					String linetxt = null;
					
					while ((linetxt = bufferedReader.readLine()) != null) {
						classnameSet.add(linetxt);
					}

				} catch (Exception e) {
					System.out.println("Error reading file content");
					e.printStackTrace();
				} finally {
					try {
						bufferedReader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				System.out.println("The specified file could not be found");
			}
		return classnameSet;

	}

}
