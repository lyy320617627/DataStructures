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
        //测试一把，我们的dfs遍历是否ok
        System.out.println("深度遍历");
        graph.dfs();
    }
    //构造器
    public Graph(int n){
        //初始化矩阵和vertexList
        edges=new int[n][n];
        vertexList=new ArrayList<String>();
        numOfEdges=0;
        isVisited=new boolean[5];
    }
    //得到第一个邻接结点的下标w

    /**
     *
     * @param index
     * @return 如果存在就返回对应的下标，如果不存在就返回-1
     */
    public int getFirstNeighbor(int index){
        for (int j=0;j<vertexList.size();j++){
            if (edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1,int v2){
        for (int j=v2+1;j<vertexList.size();j++){
            if (edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }
    //深度优先遍历方法
    //i 第一次就是0
   private   void dfs(boolean[] isVisited,int i){
        //首先，我们访问该结点
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i]=true;
        int w=getFirstNeighbor(i);
        while(w!=-1){
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果w结点已经被访过了
           w=getNextNeighbor(i,w);
        }
    }
    //对dfs进行一个重载，遍历所有的节点，并进行dfs
    public void dfs(){
        //遍历所有的节点，进行dfs[回溯]
        for(int i=0;i<getNumOfVertex();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
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
