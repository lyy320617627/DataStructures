package com.ly.linklist;

import com.sun.prism.shader.Solid_TextureFirstPassLCD_AlphaTest_Loader;
import jdk.nashorn.internal.ir.WhileNode;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
    //测试小孩出圈书否正确
        circleSingleLinkedList.countBoy(1,2,5);
    }


}
//创建一个环形的单向链表
class  CircleSingleLinkedList{
//创建一个first节点，当前没有编号
  Boy first=null;
 //添加小孩节点，构建成一个环形链表
    public  void addBoy(int nums){
        //对num进行一个数据校验
        if(nums<1){
            System.out.println("nums数据不正确");
            return;
        }
        Boy curBoy=null;//辅助指针，帮助我们构建环形链表

        //使用for循环来创建我的环形链表
        for (int i=1;i<=nums;i++){
            //根据编号，创建小孩节点
            Boy boy=new Boy(i);
            //如果是第一个小孩
            if (i==1){
                first=boy;
                first.setNext(first);//构成一个环
                curBoy=first;//让辅助节点指向第一个小孩
            }else {
             curBoy.setNext(boy);
             boy.setNext(first);
             curBoy=boy;
            }
        }
    }
    //遍历当前的环形链表
    public  void showBoy(){
        //先判断链表是否为空
        if (first==null){
            System.out.println("链表为空，没有小孩");
            return;
        }
        //因为first不能动，因此我们仍然使用辅助指针
        Boy curBoy=first;
        while (true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if (curBoy.getNext()==first){//说明已经遍历完毕
                break;
            }
            curBoy=curBoy.getNext();//curBoy后移
        }
    }
    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少个小孩在圈中
     */
    public  void countBoy(int startNo,int countNum,int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建要给辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //首先将辅助变量指向环形链表的最后一个节点
        while (true){
            if (helper.getNext()==first){//说明指向了最后一个节点
                break;
            }
            helper=helper.getNext();
        }
        for (int j=0;j<startNo-1;j++){
            first=first.getNext();
            helper=helper.getNext();
        }
        //当小孩开始报数时，让first和helper指针同时的移动m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true){
            if (helper==first){
                break;
            }
            //让first和helper指针同时的移动countNum-1,然后出圈
            for (int j=0;j<countNum-1;j++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //first表示要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //时将first指向的小孩节点出圈
            first=first.getNext();
            helper.setNext(first);//
        }
        System.out.printf("最后留在圈中的小孩%d\n",first.getNo());
    }
}

//先创建一个Boy类，表示一个节点
class Boy{
    private  int no;//编号
    private Boy next;//指向下一个节点，默认为空
    public  Boy(int no){
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}