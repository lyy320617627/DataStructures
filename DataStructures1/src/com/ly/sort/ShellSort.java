package com.ly.sort;

import java.util.Arrays;

//希尔排序
public class ShellSort {
    public static void main(String[] args) {
        int [] arr=new int[]{8,9,1,7,2,3,5,4,6,0};
        shellSort(arr);
    }
    //逐步推导
    public static  void shellSort(int[] arr){
        int temp=0;
        int count=0;
        for (int gap=arr.length/2;gap>0;gap/=2){
            //希尔排序第一轮
            for (int i=gap;i<arr.length;i++){
                //遍历各组中所有的元素（共5组，每组有两个元素），步长5
                for (int j=i-gap;j>=0;j-=gap){
                    //如果当前这个元素大于加上步长后的那个元素，说明需要交换
                    if (arr[j]>arr[j+gap]){
                        temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
            System.out.println("希尔排序第"+(++count)+""+Arrays.toString(arr));
        }

        //希尔排序第一轮
        for (int i=5;i<arr.length;i++){
            //遍历各组中所有的元素（共5组，每组有两个元素），步长5
            for (int j=i-5;j>=0;j-=5){
                //如果当前这个元素大于加上步长后的那个元素，说明需要交换
                if (arr[j]>arr[j+5]){
                     temp=arr[j];
                    arr[j]=arr[j+5];
                    arr[j+5]=temp;

                }
            }
        }
        System.out.println("希尔排序1轮后");
        System.out.println(Arrays.toString(arr));
        //第二轮
        for(int i=2;i<arr.length;i++){
            for (int j=i-2;j>=0;j-=2){
                if (arr[j]>arr[j+2]){
                    temp=arr[j];
                    arr[j]=arr[j+2];
                    arr[j+2]=temp;
                }
            }
        }
        System.out.println("希尔排序第二轮之后"+Arrays.toString(arr));
        for (int i=1;i<arr.length;i++){
            for (int j=i-1;j>=0;j-=1){
                if (arr[j]>arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println("希尔第三次排序之后");
        System.out.println(Arrays.toString(arr));
    }
    //对交换式的希尔排序进行改进，变成移步式
    public  static  void shellSort2(int[] arr){
        for (int gap=arr.length/2;gap>2;gap/=2){
            //从第gap个元素，逐个对其所在的组进行直接插入
            for(int i=i=gap;i<arr.length;i++){
                int j=i;
                int temp=arr[j];
                if (arr[j]<arr[j-gap]){
                    while (j-gap>=0&&temp<arr[j-gap]){
                        //移动ar[j-gap]到arr[j]的位置
                        arr[j]=arr[j-gap];
                        j-=gap;
//                        arr[j-gap]=temp;
                    }
                    //当退出循环时，就找到了位置
                    arr[j]=temp;
                }
            }
        }
    }
}
