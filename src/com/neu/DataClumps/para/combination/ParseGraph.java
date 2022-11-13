package com.neu.DataClumps.para.combination;

import java.util.ArrayList;
import java.util.List;

import com.neu.DataClumps.para.combination.CreateVector.ItemVector;

public class ParseGraph {
	List<List<String>> allPaths=new ArrayList<List<String>>();

	public ParseGraph(List<String> types) {
		String []vectors=new String[types.size()];
		types.toArray(vectors);
		CreateVector prossion = new CreateVector(vectors);
		int vectorSize = prossion.getVectors().size();
		int[] vexs = new int[vectorSize];
		for (int i = 0; i < vectorSize; i++) {
			vexs[i] = i;
		}
		int[][]edges=this.getEdges(vectors.length);			
		Graph graph = new Graph();
		graph.buildGraph(vexs, edges);

		FindAllPath findAllPath = new FindAllPath();
		findAllPath.visit(graph, 0,vectorSize-1);
		List<ItemVector> Vectors = prossion.getVectors();
		List<String> paths = findAllPath.getPaths();
		this.setAllPaths(Vectors, paths);
		
	}
	
	public ParseGraph(String[] vectors) {
		CreateVector prossion = new CreateVector(vectors);
		int vectorSize = prossion.getVectors().size();
		int[] vexs = new int[vectorSize];
		for (int i = 0; i < vectorSize; i++) {
			vexs[i] = i;
		}
		int[][]edges=this.getEdges(vectors.length);			
		Graph graph = new Graph();
		graph.buildGraph(vexs, edges);

		FindAllPath findAllPath = new FindAllPath();
		findAllPath.visit(graph, 0,vectorSize-1);
		List<ItemVector> Vectors = prossion.getVectors();
		List<String> paths = findAllPath.getPaths();
		this.setAllPaths(Vectors, paths);
		
	}
	
	public List<List<String>> getAllPaths(){
		return this.allPaths;
	}
	
	private void setAllPaths(List<ItemVector> Vectors,List<String> paths){
		for (String path : paths) {
			String[] items = path.split(",");
			List<String> realPath=new ArrayList<String>();
			for (String item : items) {
				int index = Integer.parseInt(item);
				ItemVector vector = Vectors.get(index);
				if (vector.getSelected())
					realPath.add(vector.getVectorName());
			}
			if(realPath.size()>0) {
			  allPaths.add(realPath);
			}
			
		}
	}
	
	private int[][]getEdges(int vectorsLength){
		List<int[]> Edges = new ArrayList<int[]>();
		int vectorSize=vectorsLength*2+2;
		int[] edge1 = { 0, 1 };
		int[] edge2 = { 0, 2 };
		int[] edge3 = { vectorSize - 2, vectorSize - 1 };
		int[] edge4 = { vectorSize - 3, vectorSize - 1 };
		Edges.add(edge1);
		Edges.add(edge2);
		Edges.add(edge3);
		Edges.add(edge4);

		for (int i = 1; i < (vectorsLength * 2 - 1); i += 2) {
			int j = i + 1;
			int m = i + 2;
			int n = i + 3;
			int[] edge5 = { i, m };
			int[] edge6 = { i, n };
			int[] edge7 = { j, m };
			int[] edge8 = { j, n };
			Edges.add(edge5);
			Edges.add(edge6);
			Edges.add(edge7);
			Edges.add(edge8);

		}
		int[][] edges = new int[Edges.size()][2];
		for (int i = 0; i < Edges.size(); i++) {
			edges[i] = Edges.get(i);
		}
		return edges;
	}

}
