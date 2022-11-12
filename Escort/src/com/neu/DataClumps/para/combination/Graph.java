package com.neu.DataClumps.para.combination;

public class Graph {
	class EdgeNode {
		int adjvex;
		EdgeNode nextEdge;
	}

	class VexNode {
		int data;
		EdgeNode firstEdge;
		boolean isVisted;

		public boolean isVisted() {
			return isVisted;
		}

		public void setVisted(boolean isVisted) {
			this.isVisted = isVisted;
		}

	}

	VexNode[] vexsarray;
	int[] visited = new int[100];
	boolean[] isVisited = new boolean[100];

	public void linkLast(EdgeNode target, EdgeNode node) {
		while (target.nextEdge != null) {
			target = target.nextEdge;
		}
		target.nextEdge = node;
	}

	public int getPosition(int data) {
		for (int i = 0; i < vexsarray.length; i++) {
			if (data == vexsarray[i].data) {
				return i;
			}
		}
		return -1;
	}

	public void buildGraph(int[] vexs, int[][] edges) {
		int vLen = vexs.length;
		int eLen = edges.length;
		vexsarray = new VexNode[vLen];

		for (int i = 0; i < vLen; i++) {
			vexsarray[i] = new VexNode();
			vexsarray[i].data = vexs[i];
			vexsarray[i].firstEdge = null;
		}

		for (int i = 0; i < eLen; i++) {

			int a = edges[i][0];
			int b = edges[i][1];

			int start = getPosition(a);
			int end = getPosition(b);

			EdgeNode edgeNode = new EdgeNode();
			edgeNode.adjvex = end;

			if (vexsarray[start].firstEdge == null) {
				vexsarray[start].firstEdge = edgeNode;
			} else {
				linkLast(vexsarray[start].firstEdge, edgeNode);
			}
		}
	}

	public void printGraph() {
		/*
		 * for(int i=0;i<vexsarray.length;i++) {
		 * System.out.printf("%d--",vexsarray[i].data); EdgeNode node =
		 * vexsarray[i].firstEdge; while (node!=null) {
		 * System.out.printf("%d(%d)--",node.adjvex,vexsarray[node.adjvex].data); node =
		 * node.nextEdge; } System.out.println("\n"); }
		 */
	}
}
