package com.ly.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
//     int[] arr={4,3,6,5,7,8};
     //创建一个AVLTree
        int[] arr={10,12,8,9,7,6};
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i=0;i< arr.length;i++){
           avlTree.add(new Node(arr[i]));
        }
        //\中序遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("在没有平衡处理前");
        System.out.println("树的高度"+avlTree.getRoot().height());//4
        System.out.println("树的左子树的高度="+avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度="+avlTree.getRoot().rightHeight());
    }
}
//创建AVLTree
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }
    //编写方法
    //1.返回的以node为根节点的二叉排序树的最小根节点的值
    //2.删除node为根节点的二叉排序树的最小节点


    /**
     * @param node 就是你传入的节点(当做二叉排序树的根节点)
     * @return 返回的是以node为根节点的二叉排序树的最小根节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小的节点
        //删除最小的节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.需求先去找到要删除的节点 targetNode
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //去找到targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {//是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) { //是右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //删除有两颗子树的情况
                int minvalue = delRightTreeMin(targetNode.right);
                targetNode.value = minvalue;

            } else {
                //删除只有一颗子树的节点
                //如果要删除的节点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果targetNode是parent的左子节电
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else { //如果targetNode是parent的右子节电
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//要删除的节点有右子节点
                    if (parent != null) {

                        //targetNode是parent的左子节电
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
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

    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        //返回左子树的高度
        public int leftHeight() {
            if (left == null) {
                return 0;
            }
            return left.height();
        }

        //返回右子树的高度
        public int rightHeight() {
            if (right == null) {
                return 0;
            }
            return right.height();
        }

        //返回当前节点的高度，以该节点为根节点的树的高度
        public int height() {
            return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
        }
   //左旋转的方法
        private  void  leftRotate(){
            //创建新的节点，以当前根节点的值
            Node newNode = new Node(value);
            //把新的结点的左子树设置成当前节点的左子树
            newNode.left=left;
            //把新的结点的右子树设置成当前节点的右子树的左子树
            newNode.right=right.left;
            //把当前节点的值替换成右子树的值
            value=right.value;
            //把当前节点的右子树设置成右子树的右子树
            right=right.right;
            //把当前节点的左子树（左子节点）设置成新的节点
            left=newNode;

        }
        //右旋转
        private  void  rightRotate(){
            //创建一个新的节点，值等于当前节点的值
           Node newNode=new Node(value);
            //新的节点的右节点指向当前节点的右节点
           newNode.right=right;
           //新的节点的左子节点指向当前节点的左子节点的右子节点
            newNode.left=left.right;
            //设置当前节点的左子节点为当前节点
            value=left.value;
            //设置当前节点的左子节点为左子节点的左子节点
            left=left.left;
            //当前节点的右子节点为设置成新的·节点
            right=newNode;
        }

        //查找要删除的节点

        /**
         * @param value 你希望删除的节点的值
         * @return 如果找到返回该节点 ，否则返回null
         */
        public Node search(int value) {
            if (value == this.value) {//找到的就是该节点
                return this;
            } else if (value < this.value) {//如果查找的值小于当前节点，就应该向左子树递归查找
                //如果左子节点为空，就不能继续查找
                if (this.left == null) {
                    return null;
                }
                return this.left.search(value);
            } else {//查找的值不小于当前结点
                //判断右子树是否为空
                if (this.right == null) {
                    return null;
                }
                return this.right.search(value);
            }
        }
        //查找要删除节点的父节点

        /**
         * @param value 要找的节点的值
         * @return 返回的是要删除的节点的父节点，如果没有就返回null
         */
        public Node searchParent(int value) {
            //如果当前节点就是要删除的的节点的父节点，就返回
            if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
                return this;
            } else {
                //如果查找的值小于当前节点的这个值，并且当前节点的左子节点
                if (value < this.value && this.left != null) {
                    return this.left.searchParent(value);//向左子节点递归的查询父节点
                } else if (value >= this.value && this.right != null) {
                    return this.right.searchParent(value);//向右子树递归查找
                } else {
                    return null;//没有找到父节点
                }
            }
        }

        @Override
        public String toString() {
            return "Node [value=" + value + "]";
        }

        //添加节点的方法
        //递归的形式，添加节点，注意需要满足二叉排序树的要求
        public void add(Node node) {
            if (node == null) {
                return;
            }
            //判断传入的节点的值，和当前节点的根节点的值作比较
            if (node.value < this.value) {
                //如果当前节点的左子树为空
                if (this.left == null) {
                    this.left = node;
                } else {
                    //递归的向左子树进行添加
                    this.left.add(node);
                }
            } else {//添加的节点的值大于当前节点的值
                if (this.right == null) {
                    this.right = node;
                } else {
                    //递归的向右子树进行添加
                    this.right.add(node);
                }
            }
            //判断当添加完一个节点后，如果右子树的高度-左子树的高度>1，则发生左旋转
            if (rightHeight()-leftHeight()>1){
                //如果他的右子树的左子树的高度大于它的右子树的右子树的高度，则先对右子树进行右旋转
                if(right!=null &&right.leftHeight()>right.rightHeight()){
                    right.rightRotate();
                leftRotate();//左旋转
                }else {
                    leftRotate();
                }
                return;
            }
            //当添加完一个节点后，如果（左子树的高度-右子树的高度）>1,则进行左旋转
            if (leftHeight()-rightHeight()>1){
                //如果左子树的右子树的高度大于它的左子树的高度

                if (left!=null &&left.rightHeight()>left.leftHeight()){
                    //先对当前节点的左节点进行左旋转
                    left.rightRotate();
                    //再对当前节点进行右旋转
                rightRotate();
                }else{
                    //直接进行右旋转
                    rightRotate();
                }
            }
        }

        //中序遍历
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrder();
            }
        }
    }

