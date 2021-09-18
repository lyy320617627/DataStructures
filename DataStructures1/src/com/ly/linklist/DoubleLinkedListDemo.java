package com.ly.linklist;

import java.beans.beancontext.BeanContext;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList=new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        //修改
        HeroNode2 newHeroNode=new HeroNode2(4,"lyyy","李大帅");
        doubleLinkedList.update(newHeroNode);
        System.out.println();
        doubleLinkedList.list();
        //删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();

        System.out.println("按编号添加后的链表");
        doubleLinkedList.list();
    }
}
//创建一个双向链表的类
class DoubleLinkedList{
    private HeroNode2 head=new HeroNode2(0,"","");
    public HeroNode2 getHead(){
        return head;
    }
    //遍历双向链表的方法
    public  void list(){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp=head.next;
        while (true){
            if (temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }
    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode2){
        HeroNode2 temp=head;
        while (true){
            if (temp.next==null){
                break;
            }
            temp=temp.next;
        }
        temp.next=heroNode2;
        heroNode2.pre=temp;
    }
    //第二种添加方式，即按编号添加

    //修改一个节点的内容，可以看到双向链表的修改和单向链表的修改差不多
    //知识节点的类型改成了HeroNode2
    public void update(HeroNode2 heroNode2){
        if (head.next==null){
            System.out.println("链表为空，没有数据");
            return;
        }
        HeroNode2 temp=head.next;
        boolean flag=false;
        while (true){
            if (temp==null){
                break;
            }
            if (temp.no== heroNode2.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.name= heroNode2.name;
            temp.nickname= heroNode2.nickname;
        }else {
            System.out.println("没有此节点");
        }
    }
//    从双向连链表中，删除一个节点
    //对于双向链表而言，我们可以直接找到要删除的节点
    //找到后，我们直接删除就可以
    public void del(int no){
        //判断当前链表是否为空
        if(head.next==null){
            System.out.println("链表为空，不能删除");
        }
        HeroNode2 temp=head.next;
        boolean flag=false;
        while (true){
            if (temp==null){//已经到链表的最一个节点的后一个
                break;
            }
            if (temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.pre.next=temp.next;
            //如果是最后一个节点，则不需要执行temp.next.pre=temp.pre;
            //否则会出现空指针异常
            if(temp.next!=null){
            temp.next.pre=temp.pre;
            }
        }else {
            System.out.printf("编号为%d的英雄不存在",no);
        }
    }
}
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public  HeroNode2 pre;//指向前一个节点
    public HeroNode2(int no,String name,String nickname){
        this.name=name;
        this.nickname=nickname;
        this.no=no;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}