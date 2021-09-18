package com.ly.recursion;

public class Queue8 {
    int max=8;
    //定义数组array，保存皇后放置位置的结果
    int[] array=new int[max];
    static int count=0;
    public static void main(String[] args) {
    //测试一把，8皇后是否正确
        Queue8 queue8=new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法",count);
    }
    //编写一个方法，放置第n个皇后
    private  void check(int n){
        if (n==max){// n==8，其实8个皇后就已经放好了
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i=0;i<max;i++){
            //先把当前这个皇后n，放在该行的第1列
            array[n]=i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){
                //如果不冲突，接着放第n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行arrar[n]=i;即将第n个皇后，放置在本行的后移 的后一个位置
        }
    }
    //查看当我们放置第n个皇后，就去检测该皇后是否和以前已经摆放的皇后是否冲突
    private boolean judge(int n){  //n表示放置第n个皇后
        for (int i=0;i<n;i++) {
            if (array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){//判断是否在同一列或者在同一斜线上
                return false;
            }
        }
        return true;
    }

    //首先写一个方法，可以将皇后摆放的位置输出
    private void print(){
        count++;
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
