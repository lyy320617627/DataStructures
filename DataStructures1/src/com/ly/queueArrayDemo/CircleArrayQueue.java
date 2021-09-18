package com.ly.queueArrayDemo;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        System.out.println("测试数组模拟唤醒列表");

        CicleArray CicleArray=new CicleArray(4);//此时环形队列的有效数据为3
        char key=' ';
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        while (loop) {
            System.out.println("s(show),显示队列");
            System.out.println("e(exit),退出队列");
            System.out.println("g(get),从队列中去除数据");
            System.out.println("a(add),向队列中添加数据");
            System.out.println("h(head),显示队列的头部数据");
            key=scanner.next().charAt(0);
            switch (key){
                case's':
                    CicleArray.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value=scanner.nextInt();
                    CicleArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res=CicleArray.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.printf("队列的头数据是%d\n",CicleArray.headQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
class  CicleArray{
    private int maxSize;
    private  int front;
    private int rear;
    private int[] arr;
    public CicleArray(int arrMaxSize){
        maxSize=arrMaxSize;

        arr=new int[arrMaxSize];
    }
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }
    public boolean isEmpty(){
        return rear==front;
    }
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满，没法添加数据");
            return;
        }
//        rear指向最后一个元素的后一个位置，所以直接添加即可
        arr[rear]=n;
//        将rear后移，这里必须考虑取模
        rear=(rear+1)%maxSize;
    }
    public int  getQueue(){
        if (isEmpty()){

            throw new RuntimeException("队列为空，没有数据");
        }
//        这里需要知道front是直接指向队列第一个元素的
//        首先把front对应的值保存在一个临时变量中
//        将front后移
//        然后返回临时保存的变量值
        int value=arr[front];
        front=(front+1)%maxSize;
        return  value;

    }
    public int  headQueue(){
        if (isEmpty()){
            throw  new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据");
        }
//        思路：从front开始遍历，遍历多少个是数据就好
        for (int i=front;i<front+size();i++){
            System.out.printf("arr[%d]=%d",i%maxSize,arr[i%maxSize]);
        }
    }
//    求出当前队列的有效的数据个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

}