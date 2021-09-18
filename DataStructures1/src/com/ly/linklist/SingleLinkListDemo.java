package com.ly.linklist;


import java.util.Stack;

public class SingleLinkListDemo {
    public static void main(String[] args) {
//        先创建几个节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
//        按照编号添加
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();
//        修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "lyyy", "李大帅");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        singleLinkedList.list();
        System.out.println("删除后的节点链表");
        singleLinkedList.del(1);
        singleLinkedList.del(2);
        singleLinkedList.del(3);
        singleLinkedList.del(4);
        singleLinkedList.list();
//   测试一下
        System.out.println("有效的节点个数："+getLength(singleLinkedList.getHead()));
//        测试一下看看是否能够得到倒数第k个节点的个数
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        HeroNode res=findLastIndexNode(singleLinkedList.getHead(), 1);
        System.out.println("res="+res);
//        原来链表的情况
        System.out.println("原来链表的情况");
         singleLinkedList.list();
        System.out.println("翻转之后的链表情况");
        reversizeLinkedList(singleLinkedList.getHead());
        singleLinkedList.list();
        System.out.println("测试逆序打印链表");
        reversePrint(singleLinkedList.getHead());


    }
//    使用方式二：即将链表的节点压入栈内，然后再取出，就可以实现链表的倒序输出
       public static  void reversePrint(HeroNode head){
        if (head.next==null){
            return;
        }
        //先创建一个栈，将各个节点压入栈中
           Stack<HeroNode> stack=new Stack<HeroNode>();
            HeroNode cur=head.next;
            //将链表的节点全部压入栈中
           while (cur!=null){
               stack.push(cur);
               cur=cur.next;//cur后移，这样就可以压入下一个节点
           }
           //将栈中的节点全部取出
           while (stack.size()>0){
               System.out.println(stack.pop());
           }
       }

//    将单链表进行翻转
    public static  void  reversizeLinkedList(HeroNode head){
        if (head.next==null||head.next.next==null){
            return;
        }
//        定义一个辅助的指针变量（帮助我们遍历原来的链表）
        HeroNode cur=head.next;
        HeroNode next=null;
        HeroNode reverseHead=new HeroNode(0,"","");
        while (cur!=null){
            next=cur.next;//先暂时保存当前节点的下一个节点，因为后面需要用
              cur.next=reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next=cur;
            cur=next;//让cur后移
        }
//        将head.next指向reverseHead.next,实现单链表的翻转
        head.next=reverseHead.next;
    }



//查找单链表中的倒数第k个节点
//    思路：编写一个方法，接收head节点，同时接收一个index
//    index表示倒数第index个节点
//    先把链表从头到尾遍历一下，得到链表的长度，然后从头遍历length-index个
    public static   HeroNode findLastIndexNode(HeroNode head,int index){
//        判断链表是否为空
        HeroNode cur=head.next;
        if (head.next==null){
            return null;
        }
        int size=getLength(head);
        if (index<=0||index>size){
            return null;
        }
        for (int i=0;i<size-index;i++){
            cur=cur.next;
        }
        return  cur;
    }



//    方法，获取单链表的有效节点个数（带头结点的建表，需要不统计头节点）

    /**
     * @param head 表示链表头节点
     * @return 返回链表的有效节点个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length=0;
//        定义一个辅助的变量
        HeroNode cur=head.next;
        while (cur!=null){
            length++;
            cur=cur.next;
        }
        return length;
    }
}
class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");
//    返回头节点
    public HeroNode getHead(){
        return head;
    }
    public void add(HeroNode heroNode) {
        HeroNode temp=head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp=temp.next;
        }
        temp.next=heroNode;
    }
//    第二种添加英雄的方式，按照排名添加
    public void addByOrder(HeroNode heroNode){
//        因为是单链表，我们找的temp是位于添加位置的前一个节点
         HeroNode temp=head;
         boolean flag=false;
         while (true){
             if (temp.next==null){//说明temp已经在链表的最后
                 break;
             }
             if (temp.next.no>heroNode.no){  //位置找到，就在temp的后面插入
                 break;
             }else if (temp.next.no==heroNode.no){//说明希望添加的heroNode的编号已然存在
                         flag=true;
                         break;

             }
             temp=temp.next; //后移，遍历当前链表
         }
//         判断flag的值
        if(flag){//说明变好已经存在
            System.out.printf("待插入的值%d已经存在\n",heroNode.no);
        }else {
//            插入到链表中，temp的后面
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }
//    修改节点的name和nickname属性，根据编号来修改，即no不能修改
//    根据newHeroNode的no来修改
    public void update(HeroNode newHeroNode){
        HeroNode temp=head.next;
        boolean flag=false;
        if (head.next==null){
            System.out.println("链表为空，没有数据");
            return;
        }
//        找到需要修改的节点，根据no编号来修改
//        定义一个辅助变量
        while(true){
            if (temp==null){
                break;//表示已经到链表的最后
            }
           if (temp.no==newHeroNode.no){
//               找到
               flag=true;
               break;
           }
           temp=temp.next;
        }
//        根据flag判断是否找到要修改的节点
        if(flag==true){
            temp.name=newHeroNode.name;
            temp.nickname= newHeroNode.nickname;
        }else {
//            没有找到
            System.out.printf("没有找到%d节点",newHeroNode.no);
        }
    }
//  删除节点
//    head节点不能动
//    需要设置一个辅助节点，找到要删除节点的前一个节点
    public void del(int no){
        HeroNode temp=head;
        boolean flag=true;//表示是否找到待删除的节点
        while (true){
            if (temp.next==null){
                break;//表示已经到链表的最后
            }
            if(temp.next.no==no){
//                表示已经找到要删除的节点，是temp的下一个节点
                flag=true;
                break;
            }
            temp=temp.next;//temp后移，遍历数组
        }
        if (flag){
            temp.next=temp.next.next;
        }else {
//            表示没有找到要删除的节点
            System.out.printf("%d节点不存在\n",no);
        }

    }
    //遍历链表
        public void list () {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            HeroNode temp=head.next;
            while (true){
                if (temp==null){
                    break;
                }
                System.out.println(temp);
                temp=temp.next;
            }
        }

}
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    public HeroNode(int no,String name,String nickname){
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