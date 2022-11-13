package com.jeantessier.dependency;

public class JudgeAnonymousClass {

	/**判断类name是否为匿名内部类 或方法的参数含有$
     * @param name
     * @return $+number是:返回ture
     */
	public boolean isAnonymousClass(String name) {
		// TODO Auto-generated method stub
    	while(name.contains("$")&&!name.contains("("))
    	{//分析的是类
        	String innerClassName=name.substring(name.indexOf("$")+1);
//        	System.out.println("======"+name);
//        	System.out.println(innerClassName);
        	if(innerClassName.length()==0)
        	{//特殊情况：org.apache.jmeter.JMeter.class$
        		return true;
        	}
        	if(Character.isDigit(innerClassName.substring(0, 1).toCharArray()[0]))
        		return true;
        	else
        	{
        		innerClassName=name.substring(name.indexOf("$")+1);
        		if(innerClassName.contains("$"))
        		{
        			name=innerClassName.substring(innerClassName.indexOf("$"));
        		}
        		else
        			return false;
        	}
    	}
    	if(name.contains("("))
    	{//分析的是方法，判断参数是否含有$
    		String paraName=name.substring(name.indexOf("("));
    		if(paraName.contains("$"))
    			return true;
    		else 
    			return false;
    	}
   		return false;
	}
	
	/**删去匿名的$1信息，保留匿名$前的信息
	 * @param name
	 * @return
	 */
	public String deleteAnonymousClass(String name) {
		// TODO Auto-generated method stub
		String newName="";
    	while(name.contains("$"))
    	{
        	String innerClassName=name.substring(name.indexOf("$")+1);
        	newName+=name.substring(0,name.indexOf("$"));
        	if(Character.isDigit(innerClassName.substring(0, 1).toCharArray()[0]))
        	{
        		break;
        	}
        	else
        	{
        		newName+=name.substring(0,name.indexOf("$"));
        		innerClassName=name.substring(name.indexOf("$")+1);
        		if(innerClassName.contains("$"))
        		{
        			name=innerClassName.substring(innerClassName.indexOf("$"));
        		}
        	}
    	}
   		return newName;
	}
}
