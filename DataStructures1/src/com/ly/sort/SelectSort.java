package com.ly.sort;

import java.util.Arrays;

//选择排序
public class SelectSort {
    public static void main(String[] args) {
        int[] arr=new int[]{101,34,119,1};
        selectSort(arr);
    }
    //选择排序
    public static  void selectSort(int[] arr){

        //使用逐步推导的方式，讲解选择排序
        //第一轮
        for (int i=0;i<arr.length-1;i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];

                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.println("第一轮排序后的结果" + Arrays.toString(arr));
        }
       /* //第二轮排序之后
        minIndex=1;
        min=arr[1];
        for (int i=1+1;i<arr.length;i++){
            if (arr[1]>arr[i]){
                min=arr[i];
                minIndex=i;
            }
        }
        if (minIndex!=1){
        arr[1]=min;
        arr[minIndex]=arr[1];

        }
        System.out.println("第二轮之后排序之后");
        System.out.println(Arrays.toString(arr));
        //第三轮排序
        minIndex=2;
        min=arr[2];
        for (int i=2+1;i<arr.length;i++){
            if (min>arr[i]){
                min=arr[i];
                minIndex=i;
            }
        }
        if (minIndex!=2){
            arr[minIndex]=arr[2];
            arr[2]=min;
        }
        System.out.println("第三轮排序的后果");
        System.out.println(Arrays.toString(arr));*/
    }
}
