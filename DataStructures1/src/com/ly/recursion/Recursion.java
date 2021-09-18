package com.ly.recursion;

import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;

//递归算法
public class Recursion {
    public static void main(String[] args) {
        //通过打印问题，回顾递归调用机制
        test(4);

    }
    //打印问题
    public static  void test(int n) {
        if (n > 2) {
            test(n - 1);
        } else {
            System.out.println("n=" + n);
        }
    }
    //阶乘问题
    public  static  int factiorial(int n){
        if (n==1){
            return 1;
        } else {
            return factiorial(n-1)*n;
        }
    }
}
