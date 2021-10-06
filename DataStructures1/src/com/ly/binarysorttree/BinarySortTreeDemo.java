package com.ly.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
   int[] arr={7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for (int i=0;i<arr.length;i++){
            binarySortTree.add(new Node(arr[i]));
        }
        //中序遍历二叉树
        //中序遍历二叉树
        binarySortTree.infixOrder();

        //测试一下要删除的叶子节点
        binarySortTree.delNode(7);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(9);
        System.out.println("删除节点后");
        binarySortTree.infixOrder();
    }
}
class BinarySortTree{
    private  Node root;
    //查找要删除的节点
    public Node search(int value){
        if (root==null){
            return null;
        }else {
            return root.search(value);
        }
    }
    //查找父节点
    public  Node searchParent(int value){
        if (root==null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }
    //编写方法
    //1.返回的以node为根节点的二叉排序树的最小根节点的值
    //2.删除node为根节点的二叉排序树的最小节点


    /**
     *
     * @param node 就是你传入的节点(当做二叉排序树的根节点)
     * @return 返回的是以node为根节点的二叉排序树的最小根节点的值
     */
    public int delRightTreeMin(Node node){
        Node target=node;
        //循环的查找左节点，就会找到最小值
        while (target.left!=null){
            target=target.left;
        }
        //这时target就指向了最小的节点
        //删除最小的节点
        delNode(target.value);
        return  target.value;
    }
    //删除节点
    public void delNode(int value){
        if (root==null){
            return;
        }else {
            //1.需求先去找到要删除的节点 targetNode
            Node targetNode = search(value);
            if (targetNode==null){
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个节点
            if (root.left==null && root.right==null){
                root=null;
                return;
            }
            //去找到targetNode的父节点
            Node parent=searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left==null && targetNode.right==null){
                 //判断targetNode是父节点的左子节点还是右子节点
                if (parent.left!=null && parent.left.value==value){//是左子节点
                    parent.left=null;
                }else if (parent.right!=null && parent.right.value==value){ //是右子节点
                    parent.right=null;
                }
            }   else if (targetNode.left!=null && targetNode.right!=null){
                //删除有两颗子树的情况
                int minvalue = delRightTreeMin(targetNode.right);
                targetNode.value=minvalue;

            }else {
                //删除只有一颗子树的节点
                //如果要删除的节点有左子节点
                if (targetNode.left!=null){
                    if (parent!=null) {
                        //如果targetNode是parent的左子节电
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else { //如果targetNode是parent的右子节电
                            parent.right = targetNode.left;
                        }
                    }else {
                        root=targetNode.left;
                    }
                } else {//要删除的节点有右子节点
                    if (parent!=null) {

                        //targetNode是parent的左子节电
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    }else {
                        root=targetNode.right;
                    }
                }

            }
        }
    }
    //添加节点的方法
    public  void add(Node node){
        if (root==null){//如果root为空，直接让root指向node
            root=node;
        }else{
            root.add(node);
        }
    }
    //中序遍历的方法
    public void infixOrder(){
        if (root!=null){
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}
//创键Node节点
class Node{
      int value;
      Node left;
      Node right;
      public Node(int value){
          this.value=value;
      }
      //查找要删除的节点

    /**
     *
     * @param value 你希望删除的节点的值
     * @return  如果找到返回该节点 ，否则返回null
     */
     public Node search(int value){
          if (value==this.value){//找到的就是该节点
              return this;
          }else if (value<this.value){//如果查找的值小于当前节点，就应该向左子树递归查找
              //如果左子节点为空，就不能继续查找
              if (this.left==null){
                  return null;
              }
             return  this.left.search(value);
          } else {//查找的值不小于当前结点
              //判断右子树是否为空
              if (this.right==null){
                  return null;
              }
              return this.right.search(value);
          }
     }
     //查找要删除节点的父节点

    /**
     *
     * @param value 要找的节点的值
     * @return 返回的是要删除的节点的父节点，如果没有就返回null
     */
    public Node searchParent(int value){
        //如果当前节点就是要删除的的节点的父节点，就返回
         if ((this.left!=null && this.left.value==value) || (this.right!=null && this.right.value==value)){
             return this;
         }else {
             //如果查找的值小于当前节点的这个值，并且当前节点的左子节点
             if (value<this.value && this.left!=null){
                 return this.left.searchParent(value);//向左子节点递归的查询父节点
             }else if (value>=this.value && this.right != null){
                 return this.right.searchParent(value);//向右子树递归查找
             } else{
                 return null;//没有找到父节点
             }
         }
    }
      @Override
      public String toString(){
          return  "Node [value="+value+"]";
      }
      //添加节点的方法
    //递归的形式，添加节点，注意需要满足二叉排序树的要求
    public void add(Node node){
          if(node==null){
              return;
          }
          //判断传入的节点的值，和当前节点的根节点的值作比较
        if (node.value<this.value){
            //如果当前节点的左子树为空
            if (this.left==null){
                this.left=node;
            }else {
                //递归的向左子树进行添加
                this.left.add(node);
            }
        }else{//添加的节点的值大于当前节点的值
            if (this.right==null){
                this.right=node;
            }else {
                //递归的向右子树进行添加
                this.right.add(node);
            }
        }
    }
    //中序遍历
    public  void infixOrder(){
          if (this.left!=null){
              this.left.infixOrder();
          }
        System.out.println(this);
          if (this.right!=null){
              this.right.infixOrder();
          }
    }
}
