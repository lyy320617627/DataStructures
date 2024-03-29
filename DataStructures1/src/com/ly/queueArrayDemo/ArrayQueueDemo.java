package com.ly.queueArrayDemo;

import java.util.IdentityHashMap;
import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue=new ArrayQueue(3);
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
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value=scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res=queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                }
                    break;
                case 'h':
                    try {
                        System.out.printf("队列的头数据是%d\n",queue.headQueue());
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
class ArrayQueue{
    private int maxSize;
    private  int front;
    private int rear;
    private int[] arr;
    public ArrayQueue(int arrmaxSize){
        maxSize=arrmaxSize;
        front=-1;
        rear=-1;
         arr=new int[arrmaxSize];
    }
    public boolean isFull(){
        return rear==maxSize-1;
    }
    public boolean isEmpty(){
        return rear==front;
    }
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满，没法添加数据");
            return;
        }
        rear++;
        arr[rear]=n;
    }
    public int  getQueue(){
        if (isEmpty()){

            throw new RuntimeException("队列为空，没有数据");
        }
        front++;
        return arr[front];
    }
    public int  headQueue(){
        if (isEmpty()){
            throw  new RuntimeException("队列为空，没有数据");
        }
        return arr[front+1];
    }
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据");
        }
        for (int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

}
