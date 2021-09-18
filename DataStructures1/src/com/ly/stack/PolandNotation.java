package com.ly.stack;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转换成为后缀表达式
        //将中缀表达式转换成为后缀表达式，并且去除掉小括号
        String expression="1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        System.out.println(list);
        List<String> SuffixExpressionList = parseSuffixExpressionList(list);
        System.out.println(SuffixExpressionList);
        System.out.printf("expression%s==%d",SuffixExpressionList,calculate(SuffixExpressionList));



       /* //先定义一个逆波兰表达式
        String suffixExpression="3 4 + 5 * 6 -";
        //1.先将3 4 + 5 * 6 -放在一个ArrayList中
        //2.将ArrayList传递给一个方法，配合栈完成计算
        List<String> list=getListString(suffixExpression);
        System.out.println("rpnList="+list);
        int res=calculate(list);
        System.out.println("逆波兰表达式的值是"+res);*/
    }
    //将后缀逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression进行分隔
        String[] split = suffixExpression.split(" ");
        List<String> list=new ArrayList<>();
        for (String ele:split){
            list.add(ele);
        }
        return list;
    }
    //完成对逆波兰表达式的运算
    public  static  int calculate(List<String> ls){
        //创建栈，只需一个栈
        Stack<String> stack = new Stack<>();
//        遍历list
          for (String item:ls)  {
              //这里使用正则表达式来取出数据
              if (item.matches("\\d+")){//匹配的是多位数
                  //入栈

                  stack.push(item);
              }else {
                  //pop出两个数并运算，再入栈
                  int num2=Integer.parseInt(stack.pop());
                  int  num1=Integer.parseInt(stack.pop());
                  int res=0;
                  if (item.equals("+")){
                      res=num1+num2;
                  }else if(item.equals("-")) {
                      res=num1-num2;
                  }else if (item.equals("*")){
                      res=num1*num2;
                  }else if (item.equals("/")){
                      res=num1/num2;
                  } else{

//                      System.out.println("输入结果有误");
                      throw new RuntimeException("运算符有误");
                  }
                  //把res入栈
                  stack.push(res+"");
              }

          }
          //最后留在stack中的2就是运算结果
        return  Integer.parseInt(stack.pop());
    }
    //方法：将中缀表达式转换成对应的list
    public static  List<String> toInfixExpressionList(String s){
        //定义一个List，存放对应的数据
        List<String> ls=new ArrayList<String>();
        int i=0;//这时是一个指针，用于遍历中缀表达式字符串
        String str;//用来对多位数的拼接操作
        char c;//每遍历到一个字符串，就放到c
        do {
            //如果c是一个非数字，我们就需要加入到ls中
            if ((c=s.charAt(i))<48||(c=s.charAt(i))>57) {
                ls.add("" + c);
                i++;
            } else { //如果是一个数
                //需要考虑多位数的问题
                str="";//先将str制成空串
                while(i<s.length()&&(c=s.charAt(i))>=48){
                    str+=c;//拼接操作
                    i++;
                }
                ls.add(str);
            }
        }while (i<s.length());
        return ls;
    }
    //方法：将中缀表达式对应list转换成为后缀表达式的值
    public static List<String> parseSuffixExpressionList(List<String> ls){
        Stack<String> s1 = new Stack<>();//符号栈
        List<String> s2 = new ArrayList<>();//存储中间结果的栈
//        遍历ls
        for(String item:ls){
            //如果是一个数，就直接入s2栈
            if (item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());//把s1栈顶的内容加入到s2，直到取到左括号“（”；
                }
                s1.pop();//将小括号弹出s1，消除小括号
            }   else{
                //当item的运算优先级小于或者等于栈顶运算符的优先级
                while (s1.size()!=0 && Operation.getValue(s1.peek())>=Operation.getValue(item) ){
                    s2.add(s1.pop());
                }
                //还需要item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入到s2中
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//因为是存放到List中，因此按顺序输出就是对应的逆波兰表达式
    }
}
//编写一个类Operation,可以返回一个运算符对应的优先级
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;
    //写一个方法，返回对应的优先级数字
    public  static int getValue(String operation){
        int result=0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-": result=SUB;
            break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
