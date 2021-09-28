package com.ly.tree;

import java.util.Scanner;
import java.util.zip.CheckedOutputStream;

public class Guess {
    public static void main(String[] args) {
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        String z = "";
        int x = (int) (Math.random() * 100);
        while (true) {
            System.out.println("continue:继续游戏");
            System.out.println("break:退出游戏");
            z = scanner.next();
            switch (z) {
                case "continue":

                    System.out.println("请输入您的数字");
                    int y = scanner.nextInt();
                    if (y > x) {
                        System.out.println("您输入的数字较大");
                    } else if (y < x) {
                        System.out.println("您输入的数字较小");
                    } else {
                        System.out.println("恭喜您，猜对了");
                    }
                    count++;
                    continue;
                case "break":
                    System.out.print("游戏结束,您进行了"+count+"次游戏");
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }

    }
}

