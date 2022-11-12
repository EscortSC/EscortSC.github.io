package ReleaseCompare;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GetDifference {
	private ArrayList<String> newCodeSmells=new ArrayList<String>();
	private ArrayList<String> diffuseness=new ArrayList<String>();

	public GetDifference(){
		ArrayList<String> release1=this.getRelease("D:\\smell\\TestSTRUTS\\STRUTS_2_0_8.txt");
		ArrayList<String> release2=this.getRelease("D:\\smell\\TestSTRUTS\\STRUTS_2_1_6.txt");
		this.getDifference(release1, release2);
		this.print("D:\\smell\\TestSTRUTS\\STRUTS_2_1_6_NewCM.txt", newCodeSmells);
		this.print("D:\\smell\\TestSTRUTS\\STRUTS_2_1_6_ChangeDiff.txt", diffuseness);
	}

	public void  getDifference(ArrayList<String> release1,ArrayList<String> release2){
		for(String r2:release2){
			boolean found=false;
			for(String r1:release1){
				if(r2.equals(r1)){
					found=true;
					break;
				}
				else{
					String []str1=r1.split(",");
					String []str2=r2.split(",");
					if(str1[0].equals(str2[0])&&str1[1].equals(str2[1])){
						found=true;
						String diff=str1[0]+","+str1[1]+",";
						for(int i=3;i<str1.length;i+=2){
							if(!str1[i].equals(str2[i])){
								diff+=str2[i-1]+","+str2[i];
							}
						}
						diffuseness.add(diff);
						break;
					}
				}
			}
			if(!found) newCodeSmells.add(r2);
		}
	}

	public ArrayList<String> getRelease(String path){
		ArrayList<String> release=new ArrayList<String>();
		 try {
	            String pathname =path;
	            File filename = new File(pathname);
	            InputStreamReader reader = new InputStreamReader(
	                    new FileInputStream(filename));
	            BufferedReader br = new BufferedReader(reader);
	            String line=br.readLine();
	            while(!line.equals(null)){
	            	release.add(line);
	            	line=br.readLine();
	            }
	            }
		 catch(Exception e){
		 }

		return release;
	}

	public void print(String path,ArrayList<String> detail){
		try{
			 File writename = new File(path);
	           writename.createNewFile();
	           BufferedWriter out = new BufferedWriter(new FileWriter(writename));
	           for(String str: detail){
	        	   out.write(str+'\n');
	           }
	           out.flush();
	           out.close();
		}
		catch(Exception e){

		}
	}



}
