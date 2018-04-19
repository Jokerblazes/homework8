package com.tw;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Library {
    private Map<Integer, Student> studentMap;
    private Set<String> classes;

    public Library() {
        studentMap = new HashMap<>();
        classes = new LinkedHashSet<>();
    }

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

    public void showSecond() {
        System.out.println("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
    }

    public void showFirstFault() {
        System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
    }

    public void showSecondFault() {
        System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
    }


    public void addStudent(String content) {
        Student student;
        try {
            student = checkInput(content);
        } catch (NumberFormatException e) {
            showFirstFault();
            return;
        }
        if (student != null) {
            studentMap.put(student.getId(), student);
            addClass(student);
            showAddStudentSuccess(student.getName());
        } else
            showFirstFault();
    }

    public void showAddStudentSuccess(String name) {
        System.out.println("学生"+ name + "的成绩被添加");
    }

    public Student checkInput(String input) throws NumberFormatException {
        String[] split = input.split(", ");
        //包括姓名、学号、成绩，默认长度大于3
        if (split.length < 3) {
            return null;
        } else {
            int id = Integer.parseInt(split[1]);
            Map<String, Integer> scoreMap = new HashMap<>();
            for (int i = 2; i < split.length; i++) {
                String[] scores = split[i].split(": ");
                if (scores.length != 2) {
                    return null;
                } else {
                    scoreMap.put(scores[0], Integer.parseInt(scores[1]));
                }
            }
            return new Student(split[0],id,scoreMap);
        }
    }

    public void addClass(Student student) {
        student.getScoreMap().forEach((key,value) -> classes.add(key));
    }
}
