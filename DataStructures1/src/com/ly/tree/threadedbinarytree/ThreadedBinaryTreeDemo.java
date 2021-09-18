package com.ly.tree.threadedbinarytree;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
   //测试线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        //二叉树要递归创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
       //测试中序线索化二叉树
        HeroNode leftNode = node5.getLeft();
        System.out.println("10号节点的前驱节点是="+leftNode);
        System.out.println("10号节点的后继节点是="+node5.getRight());
        System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.threadedList();//8,3,10,1,14,6
    }
}
//定义ThreadedBinaryTree  实现线索化二叉树的功能
class ThreadedBinaryTree{
    private HeroNode root;
    //为了实现线索化，需要创建要给指向当前节点的前驱结点的一个变量
    //在递归进行线索化时，pre总是保留前一个节点

    private   HeroNode pre=null;
    public void setRoot(HeroNode root) {
        this.root = root;
    }
    //对线索化方法进行重载
    public void  threadedNodes(){
        this.threadedNodes(root);
    }
    //遍历线索化二叉树的方法
    public  void threadedList(){
        //定义一个变量，存储当前遍历当前的节点，从root开始
        HeroNode node=root;
        while (node!=null){
            //循环的找到leftType==1的节点，第一个找到的是8节点
            //后面随着遍历而变化，因为leftType==1，说明该节点是按照线索化
            //处理后的有效节点
            while(node.getLeftType()==0){
                node=node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType()==1){
                //获取到当前节点的后继节点
                node=node.getRight();
                System.out.println(node);
            }
            //替换遍历的节点
            node=node.getRight();
        }
    }
    /**
     *
     * @param node  就是当前需要线索化的节点
     */
    //编写对线索化二叉树进行中序线索化的方法
    public void threadedNodes(HeroNode node){
        //如果node==null,不能线索化
        if (node==null){
            return;
        }
        //线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft()==null){
            //让当前节点的左子针 指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型,指向前驱节点
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre!=null&&pre.getRight()==null){
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改当前节点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个节点，让当前节点是下一个节点的下一个前驱节点
        pre=node;
        //线索化右子树
        threadedNodes(node.getRight());
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
//创建HeroNode
//先创建HeroNode节点
class HeroNode {
    private  int no;
    private  String name;
    private HeroNode left;
    private HeroNode right;
    //定义两个新的属性
    //说明1.如果leftType==0表示指向的是左子树，如果1则表示指向前驱节点
    //2.如果rightType==0表示指向的是右子树，如果1表示指向后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name){
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
