package com.neu.DataClumps.para.combination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.neu.DataClumps.para.combination.Graph.EdgeNode;

public class FindAllPath {
	//����ĳ�ڵ��Ƿ���stack��,���������·  
		public Map<Integer,Boolean> states=new HashMap<Integer, Boolean>();
		private List<String> paths=new ArrayList<String>();
		  
		//��ŷ���stack�еĽڵ�  
		public Stack<Integer> stack=new Stack<Integer>();  
		
		//��ӡstack����Ϣ,��·����Ϣ  
		 public void printPath(){  
		    StringBuilder sb=new StringBuilder();  
		    for(Integer i :stack){  
		        sb.append(i+",");  
		    }  
		    sb.delete(sb.length()-1,sb.length());  
		   // System.out.println(sb.toString()); 
		    paths.add(sb.toString());
		}  
		 
		//�õ�x���ڽӵ�Ϊy�ĺ�һ���ڽӵ�λ��,Ϊ-1˵��û���ҵ�  
		 public int getNextNode(Graph graph,int x,int y){  
		     int next_node=-1;  
		     EdgeNode edge=graph.vexsarray[x].firstEdge;  
		     if(null!=edge&&y==-1){  
		         int n=edge.adjvex;  
		         //Ԫ�ػ�����stack��  
		         if(!states.get(n))  
		             return n;  
		         return -1;  
		     }  
		           
		     while(null!=edge){  
		         //�ڵ�δ����  
		         if(edge.adjvex==y){  
		             if(null!=edge.nextEdge){  
		             next_node=edge.nextEdge.adjvex;  
		             
		             if(!states.get(next_node))  
		                 return next_node;  
		             }  
		             else  
		                 return -1;  
		         }  
		         edge=edge.nextEdge;  
		     }  
		     return -1;  
		 }
		 
		 
		 
		 public void visit(Graph graph,int x,int y){  
		       //��ʼ�����нڵ���stack�е����  
		        for(int i=0;i<graph.vexsarray.length;i++){  
		        states.put(i,false);  
		    }  
		        //stack topԪ��  
		        int top_node;  
		    //��ŵ�ǰtopԪ���Ѿ����ʹ����ڽӵ�,������������-1,��ʱ������ʸ�topԪ�صĵ�һ���ڽӵ�  
		        int adjvex_node=-1;  
		    int next_node;  
		    stack.add(x);  
		    states.put(x,true);  
		    
		    while(!stack.isEmpty()){  
		        top_node=stack.peek();  
		        //�ҵ���Ҫ���ʵĽڵ�  
		               if(top_node==y){  
		            //��ӡ��·��  
		            printPath();  
		            adjvex_node=stack.pop();  
		            states.put(adjvex_node,false);  
		        }  
		        else{  
		            //����top_node�ĵ�advex_node���ڽӵ�  
		                        next_node=getNextNode(graph,top_node,adjvex_node);  
		            if(next_node!=-1){  
		                stack.push(next_node);  
		                //�õ�ǰ�ڵ����״̬Ϊ����stack��  
		                                states.put(next_node,true);  
		                //�ٽӵ�����  
		                                adjvex_node=-1;  
		            }  
		                       //�������ٽӵ㣬��stack topԪ���˳�   
		                        else{  
		                //��ǰ�Ѿ����ʹ���top_node�ĵ�adjvex_node�ڽӵ�  
		                                adjvex_node=stack.pop();  
		                //����stack��  
		                states.put(adjvex_node,false);  
		            }  
		        }  
		    }  
		}  
		 
		 public List<String> getPaths(){
			 return this.paths;
		 }

}
