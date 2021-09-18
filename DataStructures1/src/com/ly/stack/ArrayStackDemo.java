package com.ly.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
    //测试一下ArrayStack是否正确
        ArrayStack stack = new ArrayStack(4);
        String key="";
        boolean loop=true;
        Scanner scanner=new Scanner(System.in);
        while (loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:表示退出栈");
            System.out.println("push:表示压入栈");
            System.out.println("pop:表示从栈取出数据");
            System.out.println("list：表示遍历栈");
            System.out.println("请输入你的选择");
            key=scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "exit": scanner.close();
                loop=false;
                break;
                case "push":
                    System.out.println("请输入一个数");
                    int value=scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.printf("出栈的数据是%d\n",pop);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "list":
                    stack.list();
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出了");
    }
}
//表示栈
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据放在该数组中
    private  int top=-1;//top表示栈顶，初始化为-1
    public  ArrayStack(int maxSize){
        this.maxSize=maxSize;
        stack=new int[maxSize];
    }
    //栈满
    public boolean isFull(){
        return  top==maxSize-1;
    }
    //栈空
    public  boolean isEmpty(){
        return top==-1;
    }
    //入栈
    public void push(int value){
        //判断栈是否满
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }
    //出栈,将栈顶的数据返回
    public int pop(){
     if(isEmpty()){
//         System.out.println("栈空，没有数据");
         //抛出异常来处理
         throw new RuntimeException("栈空，没有数据");
     }
     int value=stack[top];
     top--;
     return  value;
    }
    //遍历栈,遍历是从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
        }
        //需要从栈顶开始显示数据
        for(int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

}
