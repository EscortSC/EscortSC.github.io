package ReleaseCompare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GetClassLevel {

  ArrayList<String> CMInClass=new ArrayList<String>();
  ArrayList<String> classNames=new ArrayList<String>();
  public GetClassLevel(){
    ArrayList<String> lines=this.getLines();
    this.getClassNames();
    this.TestExist(lines);
    for(String str:CMInClass){
      System.out.println(str);
    }
  }
  public ArrayList<String> getLines(){
    ArrayList<String> lines=new ArrayList<String>();
    try{
      String pathname ="D:\\smell\\TestHSQLDB\\HSQLDB_1.7.0_NewCM.txt";
      File filename = new File(pathname);
      InputStreamReader reader = new InputStreamReader(
              new FileInputStream(filename));
      BufferedReader br = new BufferedReader(reader);
      String line=br.readLine();
      while(!line.equals(null)){
        String [] str=line.split("\\,");
        SmellLevel sm=new SmellLevel();
        if(sm.checkSmellLevel(str[0])==1){
          lines.add(line);
        }
        line=br.readLine();
      }
    }
    catch(Exception e){

    }
    return lines;
  }
  public void getClassNames(){
    try{
      String pathname ="D:\\smell\\output\\Matrix1.6.1\\classMatrix.txt";
      File filename = new File(pathname);
      InputStreamReader reader = new InputStreamReader(
              new FileInputStream(filename));
      BufferedReader br = new BufferedReader(reader);
      String line=br.readLine();
      String [] str=line.split("\\;");
       for(int i=1;i<str.length;i++){
         classNames.add(str[i]);
       }
    }
    catch(Exception e){

    }
  }
  public void TestExist(ArrayList<String> lines){
    for(String line:lines){
      String[] str=line.split("\\,");
      for(String className:classNames){
        if(str[1].equals(className)){
          CMInClass.add(line);
        }
      }
    }

  }



}

