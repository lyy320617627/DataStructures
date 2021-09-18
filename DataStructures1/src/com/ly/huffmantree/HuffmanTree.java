package com.ly.huffmantree;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
         int arr[]={13,7,8,3,29,6,1};
         createHuffmanTree(arr);
//        System.out.println(Arrays.toString(arr));
     //测试一下
    }
    //编写一个前序遍历的方法
    public  static  void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("树是空树，不能遍历");
        }
    }
    // 创建哈夫曼树的方法
    public  static  Node  createHuffmanTree(int[] arr){
        //为了操作方便
        //1.遍历arr数组
        //2.将arr的每个数组构建成一个Node
        //3.将Node放入到ArrayList中
        List<Node> nodes=new ArrayList<Node>();
        for (int value:arr){
            nodes.add(new Node(value));
        }
        while (nodes.size()>1) {
            //排序 从小到大
            Collections.sort(nodes);
            System.out.println("ndoes=" + nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到nodes中
            nodes.add(parent);
//        System.out.println("第一次处理后"+nodes);
            //循环处理
        }
  //返回哈夫曼树的root结点
        return nodes.get(0);
    }
}
//为了让Node对象支持排序Collections集合排序
//让Node实现Comparable接口
//创建节点类
class Node implements  Comparable<Node>{
    int value;//结点权值
    Node left;//指向左子结点
    Node right;//指向右子结点
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
//        System.out.println(this);
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    public  Node(int value){
        this.value=value;

    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排
        return this.value-o.value;
    }
}
