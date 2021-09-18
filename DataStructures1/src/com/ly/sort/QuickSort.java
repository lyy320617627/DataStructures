package com.ly.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{-9, 78, 0, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序后的结果"+Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int temp=0;
        int pivot=arr[(left+right)/2];
         while (l < r) {
          while (arr[l]<pivot){
              l+=1;
          }
          while (arr[r]>pivot){
              r-=1;
          }
          if (l>=r){
              break;
          }
          temp=arr[l];
          arr[l]=arr[r];
          arr[r]=temp;
          if (arr[l]==pivot){
              r-=1;
          }
          if (arr[r]==pivot){
              l+=1;
          }
         }
//        System.out.println(Arrays.toString(arr));
        //如果l==r,则要进行移位，否则会表现出栈溢出
        if(l==r){
            l+=1;
            r-=1;
        }
        if (left<r){
            quickSort(arr,left,r);
        }
        //向右递归
        if (right>l){
            quickSort(arr,l,right);
        }
    }
}