package com.neu.MessageChains;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.neu.sql.utils.JdbcUtil;

import sun.print.PSPrinterJob.PluginPrinter;


public class SortFilename {
	
	static List<String> filename = new ArrayList<String>();
	
	public void sortFilename(String projectName) {
		System.out.println("order");
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		String sql0 = "SELECT DISTINCT(versionnumber) FROM "+ projectName +"sysinfo";
	
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			List<Object> list = runner.query(con, sql0, new ColumnListHandler<Object>());
			for(Object obj : list){
				filename.add(String.valueOf(obj));
				//System.out.println(obj);		 
			}
			
			naturalSort(filename);
			for(int i = 0;i< filename.size();i++) {
				System.out.println(filename.get(i));
				String sql1 = "UPDATE "+ projectName +"sysinfo SET evolutionpath ="+ (i+1) +" WHERE versionnumber = ?";
				runner.update(con,sql1, filename.get(i));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				con.commit();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	public static int atoi(String str) {
       
        if (str == null || str.length() == 0) {
            return 0;
        }
        int nlen = str.length();
        double sum = 0;
        int sign = 1;
        int j = 0;
       
        while (str.charAt(j) == ' ') {
            j++;
        }
        
        for (int i = j; i < nlen; i++) {
            char current = str.charAt(i);
            if (current >= '0' && current <= '9') {
                sum = sum * 10 + (int) (current - '0');
            } else {
                break;
            }
        }
        sum = sum * sign;
   
        if (sum > Integer.MAX_VALUE) {
            sum = Integer.MAX_VALUE;
        } else if (sum < Integer.MIN_VALUE) {
            sum = Integer.MIN_VALUE;
        }
        return (int) sum;
    }


	public static void naturalSort(List<String> list) {
        Collections.sort(list, (o1, o2) -> {
            int i = 0, j = 0;
            String temp1, temp2;
            int num1, num2;
            int length = Math.min(o1.length(), o2.length());
            while (i < length && j < length) {
                temp1 = "";
                temp2 = "";
                if (o1.charAt(i) > '9' || o1.charAt(i) < '0' || o2.charAt(j) > '9' || o2.charAt(j) < '0') {
                    if (o1.charAt(i) == o2.charAt(j)) {
                        i++;
                        j++;
                        continue;
                    } else if (o1.charAt(i) > o2.charAt(j)) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                while (i < o1.length() && o1.charAt(i) <= '9' && o1.charAt(i) >= '0') {
                    temp1 += o1.charAt(i);
                    i++;
                }
                while (j < o2.length() && o2.charAt(j) <= '9' && o2.charAt(j) >= '0') {
                    temp2 += o2.charAt(j);
                    j++;
                }
                num1 = atoi(temp1);
                num2 = atoi(temp2);
                if (num1 == num2) {
                    if (temp1.length() < temp2.length()) {
                        return 1;
                    } else if (temp1.length() > temp2.length()) {
                        return -1;
                    } else {
                        continue;
                    }
                } else if (num1 > num2) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return o1.length() > o2.length() ? 1 : -1;
        });
    }
	

}
