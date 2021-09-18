package com.ly.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int temp=0;
     int arr[]={4,6,8,5,9-1,90,89,56,-999};
     //分步完成
        addjustHeap(arr,1,arr.length);
        System.out.println("第一次"+ Arrays.toString(arr));
        addjustHeap(arr,0,arr.length);
        System.out.println("第二次"+ Arrays.toString(arr));
        //完成最终的代码
        for (int i=arr.length/2-1;i>=0;i--){
            addjustHeap(arr,i,arr.length);
        }
        for (int j=arr.length-1;j>0;j--){
            //交换
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            addjustHeap(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));

    }
    //编写一个堆排序的方法
    public static  void heapSort(int arr[]){
        System.out.println("堆排序!!");
    }
    //将一个数组（二叉树），调整成一个大顶堆

    /**
     * 功能：完成将以i指向的非叶子的树，调整成大顶堆
     * @param arr 待调整的数组
     * @param i    i表示非叶子节点的在数组的索引
     * @param length 表示对多少个元素进行调整，length是在逐渐的减少
     */
    public static void addjustHeap(int arr[],int i,int length){
        int temp=arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        for (int k=i*2+1;k<length;k=k*2+1){
            if (k+1<length&&arr[k]<arr[k+1]){
                k++;//k指向右子节点
            }

            if (arr[k]>temp){
                arr[i]=arr[k];
                i=k;
            }else{
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值放在了i原先的位置
        arr[i]=temp;//将temp值放到调整后的位置

    }
}
