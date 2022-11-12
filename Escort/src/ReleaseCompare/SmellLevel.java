package ReleaseCompare;

import java.util.ArrayList;

public class SmellLevel {
  static ArrayList<String> levelClass;
  static ArrayList<String> levelMethod;
  public SmellLevel(){
    levelClass=new ArrayList<String>();
    levelMethod=new ArrayList<String>();
    levelClass.add("ClassDataShouldBePrivates");
    levelClass.add("ComplexClass");
    levelClass.add("SpaghettiCode");
    levelClass.add("SpeculativeGeneralitie");
    levelClass.add("MiddleMan");
    levelClass.add("LazyClass");
    levelClass.add("Shotgun Surgery");
    levelClass.add("God Class");
    levelClass.add("Data Class");
    levelClass.add("RefusedBequest");
    levelClass.add("Tradition Breaker");

    levelMethod.add("Intensive Coupling");
    levelMethod.add("Dispersed Coupling");
    levelMethod.add("Brain Method");
    levelMethod.add("LongParamenterList");
    levelMethod.add("MessageChains");
    levelMethod.add("Feature Envy");
  }
  public ArrayList<String>getLevelClass(){
    return this.levelClass;
  }
  public ArrayList<String>getLevelMethod(){
    return this.levelMethod;
  }
  public int checkSmellLevel(String smellName){
    int type=0;
    for(String smell:this.levelClass)
      if(smellName.equals(smell)){
        type=1;
        break;
      }
    if(type==1) return type;
    for(String smell:this.levelMethod)
      if(smellName.equals(smell)){
        type=2;
        break;
      }
    return type;
  }



}

