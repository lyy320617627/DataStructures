package com.ly.hashtab;

import com.sun.glass.ui.Size;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.naming.InsufficientResourcesException;
import java.beans.beancontext.BeanContext;
import java.util.Scanner;

//哈希表的代码实现
public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key="";
            Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出系统");
              key=scanner.next();
              switch (key){
                  case "add":
                      System.out.println("输入id");
                      int id=scanner.nextInt();
                      System.out.println("输入名字");
                      String name=scanner.next();
                      //创建一个雇员
                      Emp emp = new Emp(id, name);
                     hashTab.add(emp);
                     break;
                  case "list":
                      hashTab.list();
                      break;
                  case "find":
                      System.out.println("请输入要查找的id");
                     id = scanner.nextInt();
                     hashTab.findEmpById(id
                     );
                      break;
                  case "exit":
                      scanner.close();
                      System.exit(0);
                  default:
                      break;
              }
        }
    }
}
///编写哈希表,用来管理多条链表
class  HashTab{
   private EmpLinkedList[] empLinkedListArray;
    public int size;//表示共有多少条链表
   //构造器
    public  HashTab(int size){
        this.size=size;
        //初始化我们的EmpLinkedList链表
        empLinkedListArray=new EmpLinkedList[size];
        for (int i=0;i<size;i++){
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }
    //添加雇员
    public void add(Emp emp){
        //根据员工的id得到该员工应当添加到那条链表
        int empLinkEdListNO=hashFun(emp.id);
        //将emp加入到对应的链表中
        empLinkedListArray[empLinkEdListNO].add(emp);
    }
    //根据输入的id，查找雇员
    public void findEmpById(int id){
        //使用散列函数确定到那条链表去查找
        int empLinkedListNO=hashFun(id);
        Emp emp=empLinkedListArray[empLinkedListNO].findEmpById(id);
        if(emp!=null){
            System.out.printf("在第%d条链表中找到了该雇员 id=%d\n",(empLinkedListNO+1),id);
        }else {
            System.out.println("在哈希表中，没有找到该雇员");
        }
    }

    //编写一个散列函数，使用一个简单的取模法
    public int hashFun(int id){
        return id % size;
    }
    //遍历所有的链表,遍历哈希表
    public  void list(){
        for (int i=0;i<size;i++){
            empLinkedListArray[i].list(i);
        }
    }
}
    //表示一个雇员
    class  Emp{
        public int id;
        public String name;
        public Emp next;//next默认为空
        public Emp(int id,String name){
            super();
            this.id=id;
            this.name=name;
        }
    }
    //创建EmpLinkedList,表示链表
class EmpLinkedList{
    //头指针指向第一个雇元，因此我们这个链表，是直接指向第一个雇员的
        private Emp  head;//默认为空
        //添加雇员到链表中
        //说明：添加雇员的时候，就是添加到链表的最后
        public void add(Emp emp){
            //如果添加的是第一个雇员
            if (head==null){
                head=emp;
                return;
            }
            //如果不是第一个雇员，则使用一个辅助的指针，帮我们定位到最后
            Emp curEmp=head;
            while (true){
                if (curEmp.next==null){//说明到链表的最后
                    break;
                }
                curEmp=curEmp.next;//后移
            }
            //退出时，直接将emp加入到链表的最后
            curEmp.next=emp;
        }
        //遍历链表的雇员信息
        public  void list(int no){
            if (head==null){
                System.out.println("第"+(no)+"链表为空");
                return;
            }
            System.out.print("第"+(no+1)+"链表的信息为为");
            Emp curEmp=head;//辅助指针
            while (true){
                System.out.printf("=>id=%d name=%s\t",curEmp.id,curEmp.name);
                if (curEmp.next==null){//说明curEmp已经是最后一个节点
                    break;
                }
                curEmp=curEmp.next;//后移，遍历
            }
            System.out.println();
        }
        //根据id查找雇员
        //如果查找到，就返回Emp。如果没有找到，就返回null
        public Emp findEmpById(int id){
              //判断链表是否为空
            if (head==null){
                System.out.println("链表为空，没有数据");
                return null;
            }
            Emp curEmp=head;
            while (true){
                if (curEmp.id==id){//说明找到
                    break;//这时curEmp就指向要查找的雇员
                }
                // 退出条件
                if (curEmp.next==null){//说明遍历完，还是没有找到c
                    curEmp=null;
                    break;
                }
                curEmp=curEmp.next;
            }
            return curEmp;
        }
    }

