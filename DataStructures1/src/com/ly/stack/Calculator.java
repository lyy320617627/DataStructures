package com.ly.stack;

import sun.security.krb5.internal.ccache.CCacheInputStream;

import java.lang.management.OperatingSystemMXBean;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
      //根据老师的思路，完成表达式的运算
        Scanner scanner = new Scanner(System.in);
        String expression=scanner.next();
        ArrayStack2 numberStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int num1=0;
        int num2=0;
        int res=0;
        int oper=0;
        char ch=' ';//将每次得到的char保存到ch
        int index=0;//用于扫描
        String keepNum="";//用于拼接多位数
        while (true){
            //依次得到expression的每一个字符
            ch=expression.substring(index,index+1).charAt(0);
            if (operStack.isOper(ch)){//如果是一个运算符
           if (!operStack.isEmpty()){  //如果运算符不为空
               if (operStack.priority(ch)<=operStack.priority(operStack.peek())){//如果运算符小于当前栈顶的运算符的等级
                    num1=numberStack.pop();
                    num2=numberStack.pop();
                    oper=operStack.pop();
                    res= numberStack.cal(num1,num2,oper);
                    numberStack.push(res);
                    operStack.push(ch);
               } else {
                   //如果运算符大于当前运算符的登记
                   operStack.push(ch);
               }

           }else{
               //如果为空，则直接入栈
               operStack.push(ch);
            }
            } else {//如果是数，则直接入数栈
                //当处理多位数时，不能发现是一位数就入栈，应该判断是否是多位数

//                numberStack.push(ch-48);
                //处理多位数
                keepNum+=ch;
                // 如果ch已经是expressionn的最后一位，就直接入栈
                if(index==expression.length()-1){
                    numberStack.push(Integer.parseInt(keepNum));
                }else{

                //判断下一个字符是不是数字，如果是数字，则进行急需扫描，如果是运算符，则压入栈
                if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                    //是操作符
                    numberStack.push(Integer.parseInt(keepNum));
                    //清空，
                    keepNum="";
                }

                }
            }
            //让index+1，并判断是否扫描到expression的最后
            index++;
            if (index>=expression.length()){
                break;
            }
        }
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1=numberStack.pop();
            num2=numberStack.pop();
            oper=operStack.pop();
            res=numberStack.cal(num1,num2,oper);
            numberStack.push(res);
        }
        //将数栈的最后数，pop出，就是结果
        System.out.printf("表达式%s=%d",expression,numberStack.pop());
    }
}
//先创建一个栈，直接使用前面创建好的，
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据放在该数组中
    private  int top=-1;//top表示栈顶，初始化为-1
    public  ArrayStack2(int maxSize){
        this.maxSize=maxSize;
        stack=new int[maxSize];
    }
    //栈满
    public boolean isFull(){
        return  top==maxSize-1;
    }
    //增加一个方法，可以返回当前栈顶的值，但是不是真正的出栈
    public int peek(){
        return stack[top];
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
    //返回运算符的优先级，优先级是由程序员来定义的，优先级使用数字表示，数字越大，则优先级越高
    public  int priority(int oper){
        if (oper=='*' ||  oper=='/'){
            return 1;
        }else if (oper=='+'||oper=='-'){
            return 0;
        } else {
            return -1;//假定目前的表达式只有四则运算
        }
    }
    //判断是不是一个操作符，
    public  boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }
//    计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;//用于存放计算的结果
        switch (oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;//注意顺序
                break;
            case '*':
                res= num1*num2;
                break;
            case '/':
                res= num2/num1;
                break;
        }
        return  res;
    }
}
