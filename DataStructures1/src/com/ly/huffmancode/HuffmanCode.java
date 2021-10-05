package com.ly.huffmancode;

import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;

import javax.lang.model.util.ElementScanner6;
import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
    String content="i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);
        //分步过程
        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes="+nodes);
        //测试一把，创建的二叉树
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
       huffmanTreeRoot.preOrder();
       //测试一把是否生成了对用哈夫曼编码
        getCodes(huffmanTreeRoot,"",stringBuilder);
        System.out.println("生成的哈夫曼编码表");
        System.out.println(huffmanCodes);
        //测试
        byte[] huffmanCodeBytes=zip(contentBytes,huffmanCodes);
        System.out.println("huffmanCodeBytes="+Arrays.toString(huffmanCodeBytes));
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的数组是"+new String(sourceBytes));

//        byte[] huffmanCodesBytes=huffmanZip(contentBytes);
//        System.out.println("压缩后的结果："+Arrays.toString(huffmanCodesBytes));
    }

    //编写一个方法，完成对压缩文件的解压

    /**
     *
     * @param zipFile  准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile,String dstFile){
        //定义文件的输入流
        InputStream is=null;
        //定义一个对象输入流
        ObjectInputStream ois=null;
        //定义一个文件的输出流
        OutputStream os =null;
        try {
            //创建文件输入流
          is = new  FileInputStream(zipFile);
          //我们创建一个和is关联的对象输入流
            ois=new ObjectInputStream(is);
            //读取byte数组huffmanBytes
          byte[] huffmanBytes= (byte[]) ois.readObject();
          //读取赫夫曼编码表
          Map<Byte,String> huffmanCodes=(Map<Byte, String>) ois.readObject();
          //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes数组写入到目标文件
          os=  new  FileOutputStream(dstFile);
          //写数据到文件中
            os.write(bytes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
            os.close();
            ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //编写一个方法，将一个文件进行压缩

    /**
     *
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放入到那个目录下
     */
    public static void zipFile(String srcFile,String dstFile){
        //创建输出流
        //创建文件的输入流
        FileInputStream is=null;
        OutputStream os=null;
        ObjectOutputStream oos=null;
        try {
        is = new FileInputStream(srcFile);
            //创建一个和源文件代销一样的byte[]
            byte[] b=new byte[is.available()];
            //读取文件
            is.read(b);
           //直接对源文件进行压缩
            byte[] huffmanBytes=huffmanZip(b);
            //创建文件的输出流
            os=new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
           oos= new  ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);//我们先把
            //这里我们以对象流的方式写入赫夫曼编码，是为了以后我们恢复源文件时使用
             oos.writeObject(huffmanCodes);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }


    }
    //完成数据的解压
    //思路1.将huffmanCodeBytes重写转换成赫夫曼编码对应的二进制编码
    //2.赫夫曼编码对应的二进制的字符集再转换成为响应的字符集
       //编写一个方法，完成对压缩数据的解码

    /**
     *
     * @param huffmanCodes 赫夫曼编码表map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes) {
        //1.先得到huffmanBytes对应的二进制的字符串，形式如1010100010111...
        StringBuilder stringBuilder1 = new StringBuilder();
        //将byte数组转换成二进制的字符串
        for (int i=0;i<huffmanBytes.length;i++){
            byte b=huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag=(i==huffmanBytes.length-1);
            stringBuilder1.append(byteToBitString(!flag,b));
        }
//        System.out.println("赫夫曼字节数组对应的二进制字符串="+stringBuilder1.toString());
//        return null;
        //把字符串 按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调转因为反向查询a->100 100->?
        Map<String,Byte> map =new HashMap<>();
        for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }
        //创建一个集合，存放byte
        List<Byte> list=new ArrayList<>();
        //i可以看成索引，扫描stringbuilder
        for (int i=0;i<stringBuilder1.length();){
            int count=1;//小的计数器
            boolean flag=true;
            Byte b=null;
            while (flag){
                //递增的取出key
                String key=stringBuilder1.substring(i,i+count);//i不动，让count移动，直到匹配到一个字符
                b=map.get(key);
                if(b==null){
                    count++;
                }else{
                    flag=false;
                }
            }
           list.add(b);
            i+=count-1;//直接让i移动到count的位置
        }
      //当for循环结束后，我们list中就存放了所有的字符
        //把list中的数据放入到byte[]并返回
        byte[] b=new byte[list.size()];
        for (int i=0;i<b.length;i++){
            b[i]=list.get(i);
        }
        return b;
    }
    /**
     * 将一个byte转化成一个二进制的字符串
     * @param b
     * @param flag 表示是否需要补高位，如果是true，表示需要补高位，如果是false表示不补
     * @return  是该b对应的二进制的字符串（注意是按补码返回）
     */
    private  static String byteToBitString(boolean flag,byte b) {
        int temp = b;//将b转换成一个int
        //如果是整数我们还存在补高位
        if (flag) {
            temp |= 256;//按位与
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else{
            return  str;
        }
    }

  /* // 使用一个方法，将前面的方法封装起来，便于我们的调用

    *//**
     *
     * @param bytes  原始的字符串对应的字节数组
     * @return 返回的是经过赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes=getNodes(bytes);
        //根据nodes创建赫夫曼树
        Node huffmanTreeRoot=createHuffmanTree(nodes);
        //生成对应的赫夫曼编码（根据赫夫曼树）
               preOrder(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组

        byte[] huffmanCodeBytes=zip(bytes,huffmanCodes);
        return huffmanCodeBytes;

    }


    /**
     *
     * @param bytes 这时原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte数组
     */
    //编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼树编码表，返回一个赫夫曼编码压缩后的byte[]数组
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
     //1.先利用赫夫曼编码表，将bytes数组转换成赫夫曼编对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组，
        for (byte b:bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("测试stringBuilder="+stringBuilder);
        //将对应的字符串转换成byte[]
        //统计返回byte[]HuffmanCodeBytes长度
        int len;
        //一句话搞定  int len=(stringBuilder.length()+7)/8;
        if (stringBuilder.length()%8==0){
            len=stringBuilder.length()/8;
        }else {
            len=stringBuilder.length()/8+1;
        }
        //创建存储压缩后的byte数组
        byte[] HuffmanCodeBytes=new byte[len];
        int index=0;//记录是第几个byte
        for (int i=0;i<stringBuilder.length();i+=8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {//不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
                //将strByte转成一个Byte，放入到HuffmanCodeBytes中
                HuffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
                index++;
        }
        return HuffmanCodeBytes;
    }
    //生成赫夫曼树对应的赫夫曼编码表
    //思路：
    //1.将赫夫曼编码表存放在map中比较合适Map<Byte,String>
    //形式如响应的ascii表转换成二进制
    static  Map<Byte,String> huffmanCodes=new HashMap<Byte,String>();
    //2.在生成赫夫曼编码表时，需要拼接路径，定义一个Stringbuilder存储某个叶子节点的路径
    static  StringBuilder stringBuilder=new StringBuilder();

    /**
     * 功能：将转入的node结点的所有叶子节点的赫夫曼编码存放到huffmanCodes中
     * @param node 转入结点
     * @param code 路径：左边的左子节点是0，右子结点是1
     * @param stringBuilder 拼接路径
     */
    private  static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将code传入到stringbuilder1中
        stringBuilder1.append(code);
        if (node!=null){//如果node==null不处理
            //判断当前node是叶子节点还是非叶子节点
            if (node.data==null){//非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left,"0",stringBuilder1);
                //向右递归
                getCodes(node.right,"1",stringBuilder1);
            }else { //说明是一个叶子节点
                //就表示找到了某个叶子节点的最后
                huffmanCodes.put(node.data,stringBuilder1.toString());
            }

        }
    }

    //前序遍历的方法
    private static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("赫夫曼树为空");
        }
    }
    /**
     *
     * @param bytes  接收一个字节数组，
     * @return  返回的就是list
     */
    private  static List<Node> getNodes(byte[] bytes){
        List<Node> nodes=new ArrayList<>();
            Map<Byte,Integer> counts=new HashMap<>();
        for (byte b:bytes){
            Integer count=counts.get(b);
            counts.put(b,count);
            if (count==null){
                counts.put(b,1);
            } else  {
                counts.put(b,count+1);
            }
        }
        //把每个键值对转成一个Node对象，并加入到nodes集合
        for (Map.Entry<Byte,Integer> entry:counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }
    //可以通过一个List创建对应的哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size()>1){
            //排序,从小到大排
            Collections.sort(nodes);
            //取出第一课最小的二叉树
            Node leftNode=nodes.get(0);
            //取出第二课最小的二叉树
            Node rightNode=nodes.get(1);
            //创建一颗新的二叉树，它的根节点  没有data，只有权值
            Node parent =new Node(null, leftNode.weight+ rightNode.weight);
            parent.left=leftNode;
            parent.right=rightNode;
            //将已经处理的两棵二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到nodes中
            nodes.add(parent);
        }
        //返回的节点，最后的节点就是huffmanTree的节点
        return  nodes.get(0);
    }
}
//创建一个Node,带权值和数据
class Node implements  Comparable<Node>{
    Byte data;//存放数据局本身
    int weight;//权值,表示字符数据出现的次数
    Node left;
    Node right;
    public Node(Byte data,int weight){

        this.data=data;
        this.weight=weight;
    }

    @Override
    public int compareTo(Node o) {
        ///此时表示从小到大排序
        return this.weight-o.weight;
    }
    //重写一下toString
    @Override
    public  String toString(){
        return "Node [data="+data+" weight="+weight+"]";
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
}