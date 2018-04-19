package com.tw;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Map<Integer, Student> studentMap;
    private Set<String> classes;

    public Library() {
        studentMap = new HashMap<>();
        classes = new LinkedHashSet<>();
    }

    public Library(Map<Integer, Student> studentMap, Set<String> classes) {
        this.studentMap = studentMap;
        this.classes = classes;
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

    public void createTranscript(String content) {
        List<Student> students;
        try {
            students = checkSecondInput(content);
        } catch (NumberFormatException e) {
            showSecondFault();
            return;
        }
        if (students != null) {
            showCreateTranscriptSuccess(students);
        } else {
            showSecondFault();
        }

    }

    public void showCreateTranscriptSuccess(List<Student> students) {
        //内容打印
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("成绩单\n")
                .append("姓名|");
        classes.forEach(value -> stringBuilder.append(value).append("|"));
        stringBuilder.append("平均分|总分\n")
                .append("========================\n");
        int everySum = 0;
        int classSum = 0;
        int classSize = classes.size();
        DecimalFormat format = new DecimalFormat(".00");
        List<Integer> evertTotal = new ArrayList<>();
        for (Student student : students) {
            stringBuilder.append(student.getName())
                    .append("|");
            Map<String, Integer> scoreMap = student.getScoreMap();
            for (String klass : classes) {
                Integer score = scoreMap.get(klass);
                if (score != null) {
                    stringBuilder.append(score);
                    everySum += score;
                } else {
                    stringBuilder.append(0);
                }
                stringBuilder.append("|");
            }
            evertTotal.add(everySum);
            classSum += everySum;
            stringBuilder.append(format.format((float) everySum / classSize))
                    .append("|").append(everySum).append("\n");
            everySum = 0;
        }
        List<Integer> collect = evertTotal.stream().sorted().collect(Collectors.toList());
        stringBuilder.append("========================\n")
                .append("全班总分平均数：").append(format.format((float)classSum / students.size()))
                .append("\n全班总分中位数：").append(format.format(Utils.getMidNumberFromList(collect)));
        System.out.println(stringBuilder.toString());
    }

    public List<Student> checkSecondInput(String input) {
        String[] ids = input.split(", ");
        List<Student> students = new ArrayList<>();
        for (String id : ids) {
            Student student = studentMap.get(Integer.parseInt(id));
            if (student != null)
                students.add(student);
        }
        return students;
    }
}
