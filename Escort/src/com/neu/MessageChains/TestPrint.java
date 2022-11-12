package com.neu.MessageChains;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.SQLException;

import org.apache.commons.io.input.TeeInputStreamTest;
import org.junit.Test;

import com.neu.codeSmells.LongParameterList;
import com.neu.sql.dao.NewreDao;
import com.neu.sql.dao.SysInfoDao;
import com.neu.sql.dao.VariationDao;
import com.neu.sql.model.SysInfo;
import com.neu.sql.utils.JdbcUtil;

import DataProcess.ReadPath;

/**
 * @author Fanyi Meng
 *
 */

public class TestPrint {

	/**
	 * Get the outermost path of all data objects
	 * 
	 * @param path
	 * @return
	 */

	public List<String> scanFiles(String path) {
		List<String> filePaths = new ArrayList<String>();
		LinkedList<File> list = new LinkedList<File>();
		File dir = new File(path);
		File[] file = dir.listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isDirectory()) {
				// Put all the directories of the first level into the linked list
				list.add(file[i]);
			}
			filePaths.add(file[i].getAbsolutePath());
		}
		return filePaths;

	}

	/**
	 * Parse the classes and methods of the project
	 * 
	 * @param path
	 */
	public void analyzeData(String path, String analyzepath) {
		List<String> allfilepath = new ArrayList<String>();
		TestPrint TP = new TestPrint();
		allfilepath = TP.scanFiles(path);
		for (int k = 0; k < allfilepath.size(); k++) {
			ReadPath rp = new ReadPath(allfilepath.get(k));
			ArrayList<String> paths = rp.getPath();
			LongParameterList LPL = new LongParameterList();
			ArrayList<String> methodname = new ArrayList<>();
			methodname = LPL.searchLongParamenterListAntipattern(paths);
			String[] temp = allfilepath.get(k).split("\\\\");
			String tempTXT = temp[temp.length - 1];
			try {
				/* read text */

				File writename = new File(analyzepath + "\\\\" + tempTXT + ".txt");
				writename.createNewFile();
				BufferedWriter out = new BufferedWriter(new FileWriter(writename));
				for (int i = 0; i < methodname.size(); i++) {
					out.write(methodname.get(i) + "\r\n");
					// System.out.println(i);
				}
				out.flush(); 
				out.close(); 
			} catch (Exception e) {
				e.printStackTrace();

			}
			paths.clear();
			System.out.println(k);
		}
	}

	/**
	 * create table
	 * 
	 * @param projectName
	 */

	public void createTable(String projectName) {

		String sql0 = "CREATE TABLE `" + projectName + "sysinfo` (\r\n" + "  `versionnumber` varchar(255) NOT NULL,\r\n"
				+ "  `classname` varchar(255) NOT NULL,\r\n" + "  `methodname` longtext,\r\n"
				+ "  `packagename` varchar(255) DEFAULT NULL,\r\n" + "  `evolutionpath` int(8) DEFAULT NULL,\r\n"
				+ "  `majornum` varchar(3) DEFAULT NULL,\r\n" + "  `projectname` varchar(255) DEFAULT NULL,\r\n"
				+ "  PRIMARY KEY (`versionnumber`,`classname`)\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
		String sql1 = "CREATE TABLE `" + projectName + "variation` (\r\n"
				+ "  `compareversion` varchar(255) NOT NULL,\r\n" + "  `classname` varchar(255) NOT NULL,\r\n"
				+ "  `versionnumber` varchar(255) DEFAULT NULL,\r\n" + "  `methodname` longtext,\r\n"
				+ "  `packagename` varchar(255) DEFAULT NULL,\r\n" + "  `changestate` varchar(4) DEFAULT NULL,\r\n"
				+ "  `evolutionpath` int(8) DEFAULT NULL,\r\n" + "  `majornum` varchar(3) DEFAULT NULL,\r\n"
				+ "  PRIMARY KEY (`compareversion`,`classname`)\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
		String sql2 = "CREATE TABLE `" + projectName + "newre` (\r\n" + 
				"  `projectname` varchar(255) NOT NULL,\r\n" + 
				"  `classname` varchar(255) NOT NULL,\r\n" + 
				"  `movebefore` varchar(255) DEFAULT NULL,\r\n" + 
				"  `methodname` longtext,\r\n" + 
				"  `packagename` varchar(255) DEFAULT NULL,\r\n" + 
				"  `frequency` varchar(255) DEFAULT NULL,\r\n" + 
				"  `weight` decimal(10,2) DEFAULT NULL,\r\n" + 
				"  `evolutionpath` int(8) DEFAULT NULL,\r\n" + 
				"  `majornum` varchar(3) DEFAULT NULL,\r\n" + 
				"  `changestate` varchar(4) DEFAULT NULL,\r\n" + 
				"  `changeversion` varchar(255) DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`projectname`,`classname`)\r\n" + 
				") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		Connection connection = null;
		try {
			connection = JdbcUtil.getConnection();
			connection.setAutoCommit(false);
			Statement stat = connection.createStatement();
			stat.addBatch(sql0);
			stat.addBatch(sql1);
			stat.addBatch(sql2);
			stat.executeBatch();
			System.out.println("create table");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.commit();
				JdbcUtil.close(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param strDate
	 * @return
	 */
	public java.sql.Date strToDate(String strDate) {
		String str = strDate;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(d.getTime());
		return date;
	}

	/**
	 *insert sysinfo table
	 * 
	 * @param analyzepath
	 * @param projectName
	 * 
	 */
	public void saveSysInfo(String analyzepath, String projectName) {

		ReadFiles readFiles = new ReadFiles();
		List<File> fileNameListR = new ArrayList<File>();
		fileNameListR = readFiles.readFiles1(analyzepath);

		for (File file : fileNameListR) {
			SysInfo sysInfo = new SysInfo();

			SysInfoDao sysInfoDao = new SysInfoDao();
			OToMoreMap<String, String> classToMethods = readFiles.getClassTOMethod(file);
			for (int i = 0; i < classToMethods.getSize(); i++) {
				String methodTemp = "";
				for (int j = 0; j < classToMethods.getvalueSize(classToMethods.getkey(i)); j++) {
					methodTemp = methodTemp + classToMethods.getvalue(i).get(j) + ".";
				}
				String versionnumbertemp = file.getName().substring(0, file.getName().lastIndexOf("."));
				String[] vernames = versionnumbertemp.split("\\.");
				String majornumtemp = vernames[0].substring(vernames[0].lastIndexOf("-") + 1);
				String classnametemp = classToMethods.getkey(i);
				String[] names = classToMethods.getkey(i).split("\\.");
				methodTemp = names[names.length - 1] + "." + methodTemp;
				sysInfo.setVersionnnumber(versionnumbertemp);
				sysInfo.setClassname(classnametemp);
				sysInfo.setMethodname(methodTemp);
				sysInfo.setPackagename(classnametemp.substring(0, classnametemp.lastIndexOf(".")));
				//sysInfo.setEvolutionpath(Integer.parseInt(versionnumbertemp.substring(0, versionnumbertemp.indexOf("-"))));
				sysInfo.setEvolutionpath(1);
				sysInfo.setMajornum(majornumtemp);
				sysInfo.setProjectname(projectName);
				sysInfoDao.saveSysInfo(sysInfo, projectName);

			}

			System.out.println(file.getName().substring(0, file.getName().lastIndexOf(".")));

		}

	}

	/**
	 * 
	 * insert variation
	 * 
	 * @param projectNum
	 * @param projectName
	 */
	public void savevariation(int projectNum, String projectName) {
		Connection connection = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "{call insert_var(?,?)}";
			CallableStatement stmt = connection.prepareCall(sql);
			stmt.setObject(1, projectNum);
			stmt.setObject(2, projectName);
			stmt.execute();
			stmt.close();
			System.out.println("insert variation");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(connection);
	}

	/**
	 * 
	 * update newre table
	 * 
	 * @param projectName
	 */
	public void saveNewre(String projectName) {

		NewreDao newreDao = new NewreDao();
		newreDao.saveNewre(projectName);
		VariationDao variationDao = new VariationDao();
		variationDao.selectMoveAfterPackageNum(projectName);
		variationDao.updateMovebefore(projectName);
		System.out.println("insert Newre");
	}

	public static void main(String[] args) {

		String path ="C:\\Fanyi\\dataset\\unzip\\atomix";
		String analyzepath = "C:\\Fanyi\\dataset\\file\\atomix\\atomix3";
		String projectName = "atomix3";
		TestPrint TP = new TestPrint();
		SortFilename SF = new SortFilename();		
		//TP.analyzeData(path, analyzepath);
		TP.createTable(projectName); 
		TP.saveSysInfo(analyzepath, projectName);
		SF.sortFilename(projectName);//order
		TP.savevariation(39, projectName);//Modify the number of items
		TP.saveNewre(projectName);
		 

	}

}
