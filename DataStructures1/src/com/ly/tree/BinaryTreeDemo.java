package com.ly.tree;

import java.sql.SQLOutput;

public class BinaryTreeDemo {
    public static void main(String[] args) {
    //需要创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        //说明，我们手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        //测试，先来一把前序遍历
        System.out.println("前序遍历：");
        binaryTree.perOrder();
        //测试
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        //测试
        System.out.println("后序遍历");
        binaryTree.postOrder();
      //前序遍历方式
        System.out.println("前序遍历方式~~~~");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode!=null){
            System.out.printf("找到了,信息为no=%d  name=%s",resNode.getNo(),resNode.getName());
        }else {
            System.out.printf("没有找到no=%d 的英雄",resNode.getNo());
        }
        //测试一下中序遍历
        System.out.println("中序遍历");
        HeroNode resNode2=binaryTree.infixOrderSearch(5);
             if (resNode2!=null){
                 System.out.printf("找到了，信息为no=%d name=%s",resNode2.getNo(),resNode2.getName());
             } else{
                 System.out.printf("抱歉，没有找到信息为no=%d的信息",resNode2.getNo());
             }
        System.out.println();
        //后序遍历查找
        HeroNode resNode3=binaryTree.postOrderSearch(5);
             if (resNode3!=null){
                 System.out.printf("找到了，信息为no=%d,name=%s",resNode3.getNo(),resNode3.getName());
             }else {
                 System.out.printf("抱歉，没有找到信息为no=%d",resNode3.getNo());
             }
             //测试一把删除的节点
        System.out.println("删除前，前序遍历");
             binaryTree.perOrder();
             binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.perOrder();




        }


    }
//定义一个BinaryTreee
class BinaryTree{
   private  HeroNode  root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    //删除节点
    public void delNode(int no){
        if (root!=null){
             //如果只有一个节点，则需要立马进行判断root是不是要删除的节点
            if(root.getNo()==no){
                root=null;
            }else {
                //进行递归删除
                root.delNode(no);
            }

        } else {
            System.out.println("空树，不能删除~~~~");
        }
    }
    //前序遍历
    public  void perOrder(){
        if (this.root!=null){
            this.root.perOrder();
        } else{
            System.out.println("当前二叉树为空,无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root!=null){
            this.root.infixOrder();
        }else {
            System.out.println("此二叉树为空");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("此二叉树为空，无法遍历");
        }
    }
    //前序遍历
    public HeroNode preOrderSearch(int no){
        if (root!=null){
          return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序遍历
    public HeroNode infixOrderSearch(int no){
        if (root!=null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序遍历
    public HeroNode postOrderSearch(int no){
        if (root!=null){
            return this.root.postOrderSearch(no);
        }else {
            return  null;
        }
    }
}
//先创建HeroNode节点
class HeroNode {
private  int no;
private  String name;
private  HeroNode left;
private  HeroNode  right;
   public HeroNode(int no,String name){
       super();
       this.no=no;
       this.name=name;
   }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    //递归删除节点
     //1.如果删除得是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void  delNode(int no){
       if(this.left!=null&&this.left.no==no){
           this.left=null;
           return;
       }
       if (this.right!=null&&this.right.no==no){
           this.right=null;
           return;
       }
       //向左子树进行递归进行判断
        if(this.left!=null){
            this.left.delNode(no);
        }
        //向右子树进行递归删除
        if (this.right!=null){
            this.right.delNode(no);
        }
    }







    //编写前序遍历的方法
    public void  perOrder(){
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left!=null){
            this.left.perOrder();
        }
        //递归向右子树遍历
        if (this.right!=null){
            this.right.perOrder();
        }

    }
    //中序遍历的方法
    public  void infixOrder(){
       //先递归向左子树中序遍历
         if (this.left!=null){
             this.left.infixOrder();
         }

         //输出父节点
        System.out.println(this);
         //递归向右子树中序遍历
        if (this.right!=null){
            this.right.infixOrder();
        }
    }
    //后序遍历的方法
    public void postOrder(){
       if(this.left!=null){
           this.left.postOrder();
       }
       if(this.right!=null){
           this.right.postOrder();
       }
        System.out.println(this);
    }
    //前序遍历查找

    /**
     *
     * @param no  查找的no
     * @return   如果找到，就返回该Node，如果没有找到，就返回null
     */
    public HeroNode preOrderSearch(int no){

        System.out.println("前序遍历调用了");
       //比较当前节点是不是
        if (this.no==no){
            return this;
        }
        //判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        HeroNode resNode=null;
        if(this.left!=null){
           resNode= this.left.preOrderSearch(no);
        }
        if (resNode!=null){
            //说明左子遍历找到了
            return resNode;
        }
        if (this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;
    }
    //中序遍历
    public HeroNode infixOrderSearch(int no){
        //先判断当前节点的左子节点是否为空，如果不为空，则进行递归中序查找
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.infixOrderSearch(no);
        }
        if (resNode!=null){
            return resNode;
        }
        if (this.no==no){
            return this;
        }

        //否则继续向右进行递归查找
        if (this.right!=null){
            resNode=this.right.infixOrderSearch(no);
        }
        return resNode;
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        //先判断当前节点的左子节点是否为空，如果不为空，则进行左递归查找，如果找到，则返回该节点，
        // 如果没有找到，则判断有边节点是否是空，如果不为空，则进行右边递归查找，找到的话返回该节点，找不到对策1话则返回空节点
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.postOrderSearch(no);
        }
        if (resNode!=null){//说明在左子树找到，
            return  resNode;
        }
        if (this.right!=null){
            resNode=this.right.postOrderSearch(no);
        }
        if (resNode!=null){

        return resNode;
        }
        System.out.println("进入后序查找");
        //如果左右子树都没有找到，就比较当前节点是不是
        if(this.no==no){
            return this;
        }
        return resNode;
    }
}
//
