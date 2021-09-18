package com.ly.tree;


public class ArrBinaryTreeDemo{
    public static void main(String[] args) {
       int[] arr={1,2,3,4,5,6,7};
        ArrBinaryTree2 arrBinaryTree2 = new ArrBinaryTree2(arr);
        arrBinaryTree2.preOrder();
    }
}
class ArrBinaryTree2{
    private int[] arr;

    public ArrBinaryTree2(int[] arr) {
        this.arr = arr;
    }
    public void preOrder(){
        this.preOrder(0);
    }
    public void preOrder(int index){
        if (arr==null||arr.length==0){
            System.out.println("数组为空，没法遍历数组");
        }
        System.out.println(arr[index]);
        if((index*2+1)<arr.length){
            preOrder(index*2+1);
        }

        if ((index*2+2)<arr.length){
            preOrder(index*2+2);
        }
    }
}