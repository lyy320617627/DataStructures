package com.ly.search;


import java.time.temporal.Temporal;
import java.util.Arrays;

public class FibonacciSearch{
    public static int maxSize=20;
    public static void main(String[] args) {
      int[] arr=new int[]{1,8,10,89,1000,1234};
        System.out.println(fibSearch(arr,1234));
    }
    //非递归的方法构造斐波那契函数
    public static int[] fib(){
        int[] f=new int[maxSize];
        f[0]=1;
        f[1]=1;
        for (int i=2;i<maxSize;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }
    //编写斐波那契查找算法的函数，mid=low+f[k-1]-1;

    /**
     *
     * @param a  数组
      * @param key   表示我们要查找的数据
     * @return  如果查找到了，则返回的是数据的下标，如果没有查找到，则返回-1
     */
    public  static int fibSearch(int[] a,int key){
           int low=0;
           int high=a.length-1;
           int k=0;//表示斐波那契数列分割数值的下标
           int mid=0;//存放mid值
           int f[]=fib();
           //获取到斐波那契分割数值的下标
        while (high>f[k]-1){
            k++;
        }
        //因为f[k]可能大于数组a的长度，因此我们需要使用一个Arrays类，构造一个新的数组，并且指向a
        //不足的部分会使用0填充
        int[] temp= Arrays.copyOf(a, f[k]);
        //  实际上需求使用a数组最后的数填充temp
        for(int i=high+1;i<temp.length;i++){
            temp[i]=a[high];
        }
        //使用while来循环处理，来找到我们的数key
        while (low<=high){//只要这个条件满足，就
        mid=low+f[k-1]-1;
        if (key<temp[mid]){  //我们应该继续向数组的左边查找
            high=mid-1;
            k--;
        }  else if (key>temp[mid]){   //我们应该向数组的右边查找
            low=mid+1;

            k-=2;
        }  else {
            //需要确定返回的是哪个下标
            if (mid<=high){
                return mid;
            } else {
                return high;
            }
        }
        }
        return -1;
    }
}
