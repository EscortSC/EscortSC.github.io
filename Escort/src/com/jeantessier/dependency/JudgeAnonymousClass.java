package com.jeantessier.dependency;

public class JudgeAnonymousClass {

	/**�ж���name�Ƿ�Ϊ�����ڲ��� �򷽷��Ĳ�������$
     * @param name
     * @return $+number��:����ture
     */
	public boolean isAnonymousClass(String name) {
		// TODO Auto-generated method stub
    	while(name.contains("$")&&!name.contains("("))
    	{//����������
        	String innerClassName=name.substring(name.indexOf("$")+1);
//        	System.out.println("======"+name);
//        	System.out.println(innerClassName);
        	if(innerClassName.length()==0)
        	{//���������org.apache.jmeter.JMeter.class$
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
    	{//�������Ƿ������жϲ����Ƿ���$
    		String paraName=name.substring(name.indexOf("("));
    		if(paraName.contains("$"))
    			return true;
    		else 
    			return false;
    	}
   		return false;
	}
	
	/**ɾȥ������$1��Ϣ����������$ǰ����Ϣ
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
