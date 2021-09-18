package com.ly.search;

import java.lang.reflect.Array;
import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
     int[] arr=new int[100];
     for (int i=0;i<100;i++){
         arr[i]=i+1;

     }
       // System.out.println(Arrays.toString(arr));
        int insertValueSearch = insertValueSearch(arr, 0, arr.length - 1, 10);
        System.out.println("index="+insertValueSearch);
    }
    //编写插值算法
     //插值查找算法，也要求数组是有序的
    /**
     *
     * @param arr  数组
     * @param left   左边的索引
     * @param right    右边的索引
     * @param findVal  要查找的值
     * @return
     */
      public static  int insertValueSearch(int[] arr,int left,int right,int findVal){
          System.out.println("查找操作执行了了");
           if (left>right||findVal<arr[left]||findVal>arr[arr.length-1]){
               return  -1;
           }
           //求出mid，自适应的一种写法，对于
          int mid=left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
           int midVal=arr[mid];
           if (findVal>midVal){//说明应该向右递归
               return insertValueSearch(arr,mid+1,right,findVal);
           } else if (findVal<midVal){
               return insertValueSearch(arr,left,mid-1,findVal);
           } else {
               return mid;
           }
      }
}
