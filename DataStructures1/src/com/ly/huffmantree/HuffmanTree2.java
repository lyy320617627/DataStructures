package com.ly.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree2 {
    public static void main(String[] args) {
        int arr[] ={13,7,8,3,29,6,1};
      Node2 root=createHuffmanTree(arr);
        preOrder(root);

    }
    //编写一个前序遍历的方法
    public  static  void preOrder(Node2 root){
        if (root!=null){
            root.preOrder();
        }else  {
            System.out.println("是空树，无法遍历");
        }
    }
    //创建赫夫曼树的方法

    /**
     *
     * @param arr  需要创建成赫夫曼树的数组
      * @return 创建好后的赫夫曼树的root节点
     */
    public static Node2 createHuffmanTree(int[] arr) {
        //第一步为了操作方便
        //遍历arr数组
        //将arr的每一个元素构建成一个node
        //3.将node放入到Arraylist当中
        List<Node2> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node2(value));
        }
        //我们处理的过程是一个循环的过程
        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);
            System.out.println("nodes=" + nodes);
            //取出根节点权值最小的两颗二叉树
            //（1）取出权值最小的节点
            Node2 leftNode = nodes.get(0);
            //(2)取出第二小的节点
            Node2 rightNode = nodes.get(1);
            //构建一课新的二叉树
            Node2 parent = new Node2(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent节点加入到nodes中
            nodes.add(parent);
            System.out.println("第一次处理后" + nodes);
        }
        //返回哈夫曼树的最后一个节点
        return  nodes.get(0);
    }
}
//创建节点类
//为了让Node实现支持排序
//让Node实现comparable接口
class Node2 implements  Comparable<Node2>{
    int value;//节点的权值
    char c;//字符
    Node2 left;//指向左子节点
    Node2 right;//指向右子节点
    //写一个前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    public  Node2(int value){
        this.value=value;
    }
    @Override
    public String toString(){
        return "Node [value="+value+"]";
    }
    @Override
    public int compareTo(Node2 o) {
        return this.value-o.value;
    }
}
