package com.ly.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储图对应的领接矩阵
    private int numOfEdges;//表示边的数目
    //定义给定数组boolean[]，记录某个节点是否被访问过
    private boolean[] isVisited;
    public static void main(String[] args) {
    //测试一把图是否创建成功
        int n=5;//结点的个数
        String Vertexs[]={"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String vertex:Vertexs){
            graph.insertVertex(vertex);
        }
        //添加边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //显示一把邻接矩阵
        graph.showGraph();
    }
    //构造器
    public Graph(int n){
        //初始化矩阵和vertexList
        edges=new int[n][n];
        vertexList=new ArrayList<String>();
        numOfEdges=0;
        isVisited=new boolean[5];
    }
    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到边的数目
    public  int getNumOfEdges(){
        return numOfEdges;
    }
    //返回节点i(下标)对应的数据
    public  String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for (int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }
    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边

    /**
     *
     * @param v1 表示顶点的下标即是第几个顶点
     * @param v2 表示第二个顶点对应的下标
     * @param weight 表示权值
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }
}
