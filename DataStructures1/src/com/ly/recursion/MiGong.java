package com.ly.recursion;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //先把上下置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }
        //使用递归回溯给小球找路
        setWay2(map, 1, 1);
        //输出新的地图，小球走过，并标识过的递归
        System.out.println("小球走过，并标识过的地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    //使用递归回溯来给小球找路
    //约定：当map[i][j]为0，表示该点没有走过 当为1时表示墙，当为2时表示通路可以走，3表示该点已经走过，但是走不通
    //在走迷宫时，需要制定一个策略（方法） 下->右->上->左，如果该点走不通，再回溯

    /**
     * @param map 表示地图
     * @param i   从哪个位置开始
     * @param j
     * @return 如果找到通路，返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已经找到了
            return true;
        } else {
            if (map[i][j] == 0) {   //表示当前点没有走过
                map[i][j] = 2;//假设该点可以通过
                if (setWay(map, i + 1, j)) {
                    //按照策略项下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
    //修改找路的策略，改成上->右->下->左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已经找到了
            return true;
        } else {
            if (map[i][j] == 0) {   //表示当前点没有走过
                map[i][j] = 2;//假设该点可以通过
                if (setWay(map, i - 1, j)) {
                    //按照策略项下走
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    return true;
                } else if (setWay2(map, i + 1, j)) {
                    return true;
                } else if (setWay2(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
