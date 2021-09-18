package com.ly.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[]=new int[]{3,9,-1,10,20};
//        int[] arr=new int[800000];
//        for (int i=0;i<arr.length;i++){
//            arr[i]= (int) (Math.random()*80000);
//        }
        Date date1 = new Date();
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str=simpleFormatter.format(date1);
        System.out.println("排序前的时间"+date1Str);
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        //测试一下冒泡排序的速度，给出80000个数据
        //测试冒泡排序
        bubbleSort(arr);
        System.out.println("排序后");
        Date date2 = new Date();
        SimpleDateFormat simpleFormatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str2=simpleFormatter.format(date2);

//      //第二趟排序，就是把倒数第二大的数据放在倒数第二位
//        for (int i=0;i<arr.length-2;i++){
//            if (arr[i]>arr[i+1]){
//                temp= arr[i];
//                arr[i]=arr[i+1];
//                arr[i+1]=temp;
//            }
//        }
//        System.out.println(Arrays.toString(arr));
    }
    //将前面的冒泡排序封装成为一个方法
    public  static  void bubbleSort(int[] arr){
        //第一次排序就是将最大的数排在最后,冒泡排序的时间复杂度是O(n*n)
        int temp = 0;//临时变量
        boolean flag=false;//标识变量，表示是否进行过交换
        for (int j=0;j<arr.length-1;j++) {
            for (int i = 0; i < arr.length - 1-j; i++) {
                if (arr[i] > arr[i + 1]) {
                    flag=true;
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
//            System.out.println("第"+(j+1)+"次排序后的数组");
//            System.out.println(Arrays.toString(arr));
            if (flag==false){  //表明一次交换都没有发生过
                break;
            }else {
                flag=false;//重置flag，进行下次的判断
            }

        }
        System.out.println(Arrays.toString(arr));
    }
}
