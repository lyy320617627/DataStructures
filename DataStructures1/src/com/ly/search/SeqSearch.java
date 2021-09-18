package com.ly.search;
//线性查找
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr=new int[]{-1,34,89,9,-11};
        int index=seqSearch(arr,-1);
        if (index==-1){
            System.out.println("没有找到需要的值");
        }else {
            System.out.println("找到了，下标为"+index);
        }
    }
    public static int seqSearch(int[] arr,int value){
        //线性查找，逐一比对
        for(int i=0;i<arr.length;i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
