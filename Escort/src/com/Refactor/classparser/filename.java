package com.Refactor.classparser;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class filename {

	// д�ļ�����
	public static void writeByFileWrite(String _sDestFile, String _sContent)

	throws IOException {

		FileWriter fw = null;

		try {

			fw = new FileWriter(_sDestFile, true);

			fw.write(_sContent);

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fw != null) {

				fw.close();

				fw = null;

			}

		}

	}

	// ��ȡ�ļ�

	public static ArrayList<String> readfilelist(ArrayList<String> array) {
		/**
		 * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
		 */

		// System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
		String tempString = null;
		ArrayList<String> arraya = new ArrayList();

		for (int i = 0; i < array.size(); i++) {
			// ��ʾ�к�
			tempString = array.get(i);
			String javaclassname = null;

			String str0[] = {};
			String str1[] = new String[2];

			if (tempString.endsWith(".java")) {

				str0 = tempString.split("\\" + "\\");
				javaclassname = str0[str0.length - 1]; // �õ��������?

				str1 = javaclassname.split(".java");
				String sContent = null;
				String classname = str1[0];

				sContent = str0[0] + ".";
				for (int wq = 1; wq < str0.length - 1; wq++) {
					sContent = sContent + str0[wq] + ".";

				}
				sContent = sContent + classname;
				// System.out.println("wangaguo " + tempString);
				arraya.add(sContent);
				//
			}
		}
		// System.out.println("arraya.size=="+arraya.size());
		// for(int j = 0; j < arraya.size(); j++){
		// System.out.println("wangaguo " + arraya.get(j));
		// }
		return arraya;
	}

	// }

	/**
	 * 
	 * ��ȡĳ���ļ����µ������ļ��к��ļ�, ���������ļ���
	 * 
	 * @param filepath
	 *            String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return Map<Integer, String> pathMap
	 * 
	 */
	public static Map<Integer, String> readfile(String filepath,
			Map<Integer, String> pathMap) throws Exception {
		if (pathMap == null) {
			pathMap = new HashMap<Integer, String>();
		}

		File file = new File(filepath);
		// �ļ�
		if (!file.isDirectory()) {
			pathMap.put(pathMap.size(), file.getPath());

		} else if (file.isDirectory()) { // �����Ŀ¼��? ����������Ŀ¼ȡ�������ļ���
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				String classname=filelist[i];
//				if(classname.contains("Test"))
////				{
////					//System.out.println(classname.contains("Test"));
////					System.out.println("*********"+filelist[i]);
////				}
//					continue;
				
				File readfile = new File(filepath + "\\" + filelist[i]);
				if (!readfile.isDirectory()) {
					 //System.out.println("wangaguoreadfile.getPath() ==" +
					// readfile.getPath());
					pathMap.put(pathMap.size(), readfile.getPath());

				} else if (readfile.isDirectory()) { // ��Ŀ¼��Ŀ¼
					readfile(filepath + "\\" + filelist[i], pathMap);
				}
			}
		}
		return pathMap;
	}
	
	public static ArrayList<String> getallclassnames(String sourcepath) {
		ArrayList<String> arraya = new ArrayList();
		try {
			File file=new File(sourcepath);
			sourcepath=file.getAbsolutePath()+'\\';
			int index = sourcepath.length();
			ArrayList<String> array = new ArrayList<String>();
			Map<Integer, String> map = readfile(sourcepath, null);
			for (int i = 0; i < map.size(); i++) {
				// System.out.println(map.get(i));

				String path = map.get(i);
				// System.out.println("path="+path);
				String parkegename = path.substring(index);

				String sContent=parkegename;
				// System.out.println("sContent  wag=="+parkegename);
				array.add(parkegename);

			}
			arraya = readfilelist(array);
		} catch (Exception ex) {
		}

		System.out.println("arraya.size==" + arraya.size());
		/* for(int j = 0; j < arraya.size(); j++){
		 System.out.println("allclass "+j +" "+ arraya.get(j));
		 }*/
		return arraya;
	}
	
}
