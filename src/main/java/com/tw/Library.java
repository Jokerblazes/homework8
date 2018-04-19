package com.tw;

public class Library {
    public boolean someLibraryMethod() {
        return true;
    }

    public void showMain() {
        System.out.println("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：");
    }

    public void showFirst() {
        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
    }
}
