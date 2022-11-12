package com.Refactor.classparser;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jeantessier.dependency.JudgeAnonymousClass;

public class SourceParser {
	/**
	 * 判断该属性是不是本系统中的
	 */
	public static boolean judgeFeatureifthisSystem(String featurename, ArrayList<String> classname) 
	{
		boolean same1 = false;
		if (featurename.contains("(") && featurename.contains(")")
				|| featurename.contains("{") && featurename.contains("}")) {
			String feature_outfeaturename = null;
			if (featurename.contains("(") && featurename.contains(")")) {
				String str0[] = {};

				int a = featurename.indexOf("(");
				String feature_outfeaturenametemp = featurename.substring(0, a); // 先去括号
				str0 = feature_outfeaturenametemp.toString().split("\\."); // 再取最后一个.之前的字符串，即类名
				// feature_outfeaturename = str0[0]+".";
				// for(int i = 1;i<str0.length-2;i++)
				// {
				// feature_outfeaturename = feature_outfeaturename +
				// str0[i]+".";
				// }
				// feature_outfeaturename = feature_outfeaturename +
				// str0[str0.length-2];

				if (str0.length > 2) {
					feature_outfeaturename = str0[0] + ".";
					for (int i = 1; i < str0.length - 2; i++) {
						feature_outfeaturename = feature_outfeaturename
								+ str0[i] + ".";
					}
					feature_outfeaturename = feature_outfeaturename
							+ str0[str0.length - 2];
				} else {
					feature_outfeaturename = str0[0];
				}
			}
			if (featurename.contains("{") && featurename.contains("}")) {
				String str0[] = {};
				featurename = featurename.replaceAll(" ", "");
				int a = featurename.indexOf("{");
				String feature_outfeaturenametemp = featurename.substring(0, a); // 先去括号
				str0 = feature_outfeaturenametemp.toString().split("\\."); // 再取最后一个.之前的字符串，即类名
				// feature_outfeaturename = str0[0]+".";
				// for(int i = 1;i<str0.length-2;i++)
				// {
				// feature_outfeaturename = feature_outfeaturename +
				// str0[i]+".";
				// }
				// feature_outfeaturename = feature_outfeaturename +
				// str0[str0.length-2];

				if (str0.length > 2) {
					feature_outfeaturename = str0[0] + ".";
					for (int i = 1; i < str0.length - 2; i++) {
						feature_outfeaturename = feature_outfeaturename
								+ str0[i] + ".";
					}
					feature_outfeaturename = feature_outfeaturename
							+ str0[str0.length - 2];
				} else {
					feature_outfeaturename = str0[0];
				}
			}

			String feature_outfeaturenamelast = null;
			JudgeAnonymousClass anonymousClass=new JudgeAnonymousClass();
			if (anonymousClass.isAnonymousClass(feature_outfeaturename)==true) {
				//判断是否为匿名类，若是，则不考虑覆盖到的此方法
//				feature_outfeaturenamelast=anonymousClass.deleteAnonymousClass(feature_outfeaturename);
//				System.out.println("-----------------"+featurename);
//				System.out.println("*****"+feature_outfeaturenamelast);
//				int nn = feature_outfeaturename.indexOf("$");
//				feature_outfeaturenamelast = feature_outfeaturename.substring(
//						0, nn);
				// System.out.println(classname);
			} else {
				feature_outfeaturenamelast = feature_outfeaturename;
			}

			/*
			 * 判断 该类名是否是本软件的类，且不等于自身类名：相当于排除jdk的类
			 */

			for (int i = 0; i < classname.size(); i++) {
				if (classname.get(i).equals(feature_outfeaturenamelast)) {
					same1 = true;
					break;
				}
			}

		} else {// 不包含括号
			String str0[] = {};
			String feature_outfeaturename = featurename;
			// System.out.println("classfeoutboundname   "+classfeoutboundname+"\n");
			str0 = feature_outfeaturename.toString().split("\\."); // 再取最后一个.之前的字符串，即类名
			// System.out.println("str0   "+str0[0]+"\n");
			if (str0.length > 2) {
				feature_outfeaturename = str0[0] + ".";
				for (int i = 1; i < str0.length - 2; i++) {
					feature_outfeaturename = feature_outfeaturename + str0[i]
							+ ".";
				}
				feature_outfeaturename = feature_outfeaturename
						+ str0[str0.length - 2];
			} else {
				feature_outfeaturename = str0[0];
			}

			String feature_outfeaturenamelast = null;
			JudgeAnonymousClass anonymousClass=new JudgeAnonymousClass();
			if (anonymousClass.isAnonymousClass(feature_outfeaturename)==true) {
//				feature_outfeaturenamelast=anonymousClass.deleteAnonymousClass(feature_outfeaturename);
//				int mm = feature_outfeaturename.indexOf("$");
//				feature_outfeaturenamelast = feature_outfeaturename.substring(
//						0, mm);
				// System.out.println(classname);
			} else {
				feature_outfeaturenamelast = feature_outfeaturename;
			}

			// boolean same1 = false;
			for (int i = 0; i < classname.size(); i++) {
				if (classname.get(i).equals(feature_outfeaturenamelast)) {
					same1 = true;
					break;
				}
			}

		}

		return same1;
	}
	
	
	public static boolean judgeIsClassorFeature(String name) {
		boolean same = false;
		if (name.contains("(") && name.contains(")")|| name.contains("{") && name.contains("}")) {
			same = true;
		}
		return same;//true is feature false is class
	}
	
	
	public static String Getonlyclassname(String featurename) {
		
		String feature_outfeaturenamelast = null;
		if (featurename.contains("(") && featurename.contains(")") || featurename.contains("{") && featurename.contains("}")) {
			String feature_outfeaturename = null;
			if (featurename.contains("(") && featurename.contains(")")) {
				String str0[] = {};
				int a = featurename.indexOf("(");
				String feature_outfeaturenametemp = featurename.substring(0, a); // 先去括号
				str0 = feature_outfeaturenametemp.toString().split("\\."); // 再取最后一个.之前的字符串，即类名

				if (str0.length > 2) {
					feature_outfeaturename = str0[0] + ".";
					for (int i = 1; i < str0.length - 2; i++) {
						feature_outfeaturename = feature_outfeaturename
								+ str0[i] + ".";
					}
					feature_outfeaturename = feature_outfeaturename
							+ str0[str0.length - 2];
				} else {
					feature_outfeaturename = str0[0];
				}
			}
			if (featurename.contains("{") && featurename.contains("}")) {
				String str0[] = {};
				featurename = featurename.replaceAll(" ", "");
				int a = featurename.indexOf("{");
				String feature_outfeaturenametemp = featurename.substring(0, a); // 先去括号
				str0 = feature_outfeaturenametemp.toString().split("\\."); // 再取最后一个.之前的字符串，即类名
				
				if (str0.length > 2) {
					feature_outfeaturename = str0[0] + ".";
					for (int i = 1; i < str0.length - 2; i++) {
						feature_outfeaturename = feature_outfeaturename
								+ str0[i] + ".";
					}
					feature_outfeaturename = feature_outfeaturename
							+ str0[str0.length - 2];
				} else {
					feature_outfeaturename = str0[0];
				}
			}
			JudgeAnonymousClass anonymousClass=new JudgeAnonymousClass();
			if (anonymousClass.isAnonymousClass(feature_outfeaturename)==true) {
//				feature_outfeaturenamelast=anonymousClass.deleteAnonymousClass(feature_outfeaturename);
//				int nn = feature_outfeaturename.indexOf("$");
//				feature_outfeaturenamelast = feature_outfeaturename.substring(
//						0, nn);
				
			} else {
				feature_outfeaturenamelast = feature_outfeaturename;
			}

		} else {// 不包含括号
			String str0[] = {};
			String feature_outfeaturename = featurename;
			str0 = feature_outfeaturename.toString().split("\\."); // 再取最后一个.之前的字符串，即类名
			if (str0.length > 2) {
				feature_outfeaturename = str0[0] + ".";
				for (int i = 1; i < str0.length - 2; i++) {
					feature_outfeaturename = feature_outfeaturename + str0[i]
							+ ".";
				}
				feature_outfeaturename = feature_outfeaturename
						+ str0[str0.length - 2];
			} else {
				feature_outfeaturename = str0[0];
			}
			JudgeAnonymousClass anonymousClass=new JudgeAnonymousClass();
			if (anonymousClass.isAnonymousClass(feature_outfeaturename)==true) {
//				int mm = feature_outfeaturename.indexOf("$");
//				feature_outfeaturenamelast = feature_outfeaturename.substring(0, mm);
//				feature_outfeaturenamelast=anonymousClass.deleteAnonymousClass(feature_outfeaturename);
			} else {
				feature_outfeaturenamelast = feature_outfeaturename;
			}

		}
		return feature_outfeaturenamelast;
	}



	
	public static String getonlyclassname(String ss) {
		// 输入的waitjudge是类名
		JudgeAnonymousClass anonymousClass=new JudgeAnonymousClass();
		if(anonymousClass.isAnonymousClass(ss)==true)
		{
			int a = ss.indexOf("$");
			ss = ss.substring(0, a);
		}
//		if (ss.contains("$0") || ss.contains("$1") || ss.contains("$2") || ss.contains("$3") || ss.contains("$4") || ss.contains("$5") || ss.contains("$6") || ss.contains("$7") || ss.contains("$8") || ss.contains("$9")) 
//		{
//			int a = ss.indexOf("$");
//			ss = ss.substring(0, a);
//		}
		return ss;

	}
	
	
	public static String methodonlyname(String filename) {// 带

		if (filename.contains(" ")) {
			filename = filename.replaceAll(" ", "");
		}
		String name = null;
		String str3[] = {};
		String str4[] = new String[2];
		if (filename.contains("(")) {
			str3 = filename.split("\\(");
			name = str3[0];

		}

		if (filename.contains("{")) {
			str3 = filename.split("\\{");
			name = str3[0];

		}
		if (!filename.contains("{") && !filename.contains("(")) {

			name = filename;
		}

		return name;
	}
	
	
	
	/*
	 * 输出log
	 */
	public static void printlogs1(List<ClassObject> classesArrList)
			throws IOException {
		System.out.println("classesArrList.size()===" + classesArrList.size());
		for (int z = 0; z < classesArrList.size(); z++) {
			ClassObject clas = classesArrList.get(z);
			if(!clas.name.contains("$"))
				continue;
			System.out.println("name = " + clas.name + "\n");
//			for (int ce = 0; ce < clas.outboundClassList.size(); ce++) {
//				System.out.println("outboundClassList" + ce + " = "
//						+ clas.outboundClassList.get(ce) + "\n");
//			}
//			for (int y = 0; y < clas.inboundClassList.size(); y++) {
//				System.out.println("inboundClassList" + y + " = "
//						+ clas.inboundClassList.get(y) + "\n");
//			}
			 for(int e =0;e<clas.outboundFeatureList.size();e++){
			 System.out.println("outboundFeatureList" +
			 e+" = "+clas.outboundFeatureList.get(e)+"\n");
			 }
			 for(int x =0;x<clas.inboundFeatureList.size();x++){
			 System.out.println("inboundFeatureList" +
			 x+" = "+clas.inboundFeatureList.get(x)+"\n");
			 }
			 for(int g =0;g<clas.FeatureList.size();g++){
			 System.out.println("feature " +
			 g+" name = "+clas.FeatureList.get(g).name+"\n");
			 // writeByFileWrite("E:\\u.txt",
		//	 clas.FeatureList.get(g).name+"\n");
//			 for(int
//			 r=0;r<clas.FeatureList.get(g).outboundClassList.size();r++)
//			 {
//			 System.out.println("feature  " + g + "outboundClassList" +
//			 r+" = "+clas.FeatureList.get(g).outboundClassList.get(r)+"\n");
//			 }
//			 for(int
//			 q=0;q<clas.FeatureList.get(g).outboundFeatureList.size();q++)
//			 {
//			 System.out.println("feature  " + g + "outboundFeatureList" +
//			 q+" = "+clas.FeatureList.get(g).outboundFeatureList.get(q)+"\n");
//			 }
			 for(int
			 p=0;p<clas.FeatureList.get(g).outboundMethodList.size();p++)
			 {
			 System.out.println("feature  " + g + "outboundMethodList" +
			 p+" = "+clas.FeatureList.get(g).outboundMethodList.get(p)+"\n");
			 }
			 for(int
			 p=0;p<clas.FeatureList.get(g).outboundAttributeList.size();p++)
			 {
			 System.out.println("feature  " + g + "outboundAttributeList" +
			 p+" = "+clas.FeatureList.get(g).outboundAttributeList.get(p)+"\n");
			 }
			 for(int
			 p=0;p<clas.FeatureList.get(g).inboundFeatureList.size();p++)
			 {
			 System.out.println("feature  " + g + "inboundFeatureList" +
			 p+" = "+clas.FeatureList.get(g).inboundFeatureList.get(p)+"\n");
			 }
			 for(int
			 p=0;p<clas.FeatureList.get(g).inboundMethodList.size();p++)
			 {
			 System.out.println("feature  " + g + "inboundMethodList" +
			 p+" = "+clas.FeatureList.get(g).inboundMethodList.get(p)+"\n");
			 }
//			 for(int
//			 p=0;p<clas.FeatureList.get(g).inboundAttributeList.size();p++)
//			 {
//			 System.out.println("feature  " + g + "inboundAttributeList" +
//			 p+" = "+clas.FeatureList.get(g).inboundAttributeList.get(p)+"\n");
//			 }
//			 for(int
//			 h=0;h<clas.FeatureList.get(g).inboundClassList.size();h++)
//			 {
//			 System.out.println("feature  " + g + "inboundClassList" +
//			 h+" = "+clas.FeatureList.get(g).inboundClassList.get(h)+"\n");
//			 }
			 }
		}
	}

}
