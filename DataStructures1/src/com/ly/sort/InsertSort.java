package com.ly.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr=new int[]{101,34,119,1,-1,89};
        insertSort(arr);
    }
   public static  void insertSort(int[] arr){
        //使用逐步推导的方法，便于理解
       //第一轮
       for (int i=1;i<arr.length;i++){

       //定义待插入的数
       int insertVal=arr[i];
       //定义待插入的数的索引
       int insertIndex=i-1;
       //给insertVal找到待插入的位置
        while (insertIndex>=0&&insertVal<arr[insertIndex]){
            arr[insertIndex+1]=arr[insertIndex];
            insertIndex--;
        }
        //判断是否需要赋值
           if (insertIndex+1!=i){
        arr[insertIndex+1]=insertVal;
           }
       System.out.println("第"+i+"轮插入后");
       System.out.println(Arrays.toString(arr));

       }
      /* //第二轮
       insertVal=arr[2];
       insertIndex=1;
       while (insertIndex>=0&&insertVal<arr[insertIndex]){
           arr[insertIndex+1]=arr[insertIndex];
           insertIndex--;
       }
       arr[insertIndex+1]=insertVal;
       System.out.println("第二轮排序的结果");
       System.out.println(Arrays.toString(arr));
       //第三轮
       insertVal=arr[3];
       insertIndex=2;
       while (insertIndex>=0&&insertVal<arr[insertIndex]){
           arr[insertIndex+1]=arr[insertIndex];
           insertIndex--;
       }
       arr[insertIndex+1]=insertVal;
       System.out.println("第二轮排序的结果");
       System.out.println(Arrays.toString(arr));*/
   }

}

