package com.tw;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }
    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void testMockClass() throws Exception {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        String value = "first";
        when(mockedList.get(0)).thenReturn(value);

        assertEquals(mockedList.get(0), value);

    }

    //展示主界面
    @Test
    public void testShowMainInterface() {
        String result = "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：\n";
        Library library = new Library();
        library.showMain();
        assertEquals(systemOut(),result);

    }

    //展示第一个界面
    @Test
    public void testShowFirstInterface() {
        String result = "请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n";
        Library library = new Library();
        library.showFirst();
        assertEquals(systemOut(),result);
    }

    //展示第二个界面
    @Test
    public void testShowSecondInterface() {
        String result = "请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n";
        Library library = new Library();
        library.showSecond();
        assertEquals(systemOut(),result);
    }

    //展示第一个错误界面
    @Test
    public void testShowFirstInterfaceIsFault() {
        String result = "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n";
        Library library = new Library();
        library.showFirstFault();
        assertEquals(systemOut(),result);
    }

    //展示第二个错误界面
    @Test
    public void testShowSecondInterfaceIsFault() {
        String result = "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n";
        Library library = new Library();
        library.showSecondFault();
        assertEquals(systemOut(),result);
    }

    //添加学生成功
    @Test
    public void testAddStudentSuccess() {
        String result1 = "学生小王的成绩被添加\n";
        Library library = new Library();
        library.addStudent("小王, 14, 科学: 14, 数学: 89");
        assertEquals(systemOut(),result1);
    }

    //添加学生失败
    @Test
    public void testAddStudentFail() {
        String result2 = "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n";
        Library library = new Library();
        library.addStudent("小王, 14, 科学,14");
        assertEquals(systemOut(),result2);
    }

    //生成成绩单失败
    @Test
    public void testCreateTranscriptFail() {
        Library library = initLibrary();
        String result = "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n";
        library.createTranscript("14: 25");
        assertEquals(systemOut(),result);
    }
    //生成成绩单成功
    @Test
    public void testCreateTranscriptSuccess() {
        Library library = initLibrary();
        String result = "成绩单\n" +
                "姓名|科学|数学|平均分|总分\n" +
                "========================\n" +
                "小王|14|89|51.50|103\n" +
                "小李|80|70|75.00|150\n" +
                "小白|90|70|80.00|160\n" +
                "========================\n" +
                "全班总分平均数：137.67\n" +
                "全班总分中位数：150.00\n";
        library.createTranscript("14, 25, 23, 50");
        assertEquals(systemOut(),result);
    }

    //初始化Library（给生成成绩单使用）
    private Library initLibrary() {
        Map<Integer, Student> map = new HashMap<>();
        Set<String> classes = new LinkedHashSet<>();
        classes.add("科学");
        classes.add("数学");
        //小王
        Map<String, Integer> scores1 = new HashMap<>();
        scores1.put("科学", 14);
        scores1.put("数学", 89);
        Student student1 = new Student("小王",14,scores1);
        map.put(student1.getId(), student1);
        //小李
        Map<String, Integer> scores2 = new HashMap<>();
        scores2.put("科学", 80);
        scores2.put("数学", 70);
        Student student2 = new Student("小李",25,scores2);
        map.put(student2.getId(), student2);
        //小白
        Map<String, Integer> scores3 = new HashMap<>();
        scores3.put("科学", 90);
        scores3.put("数学", 70);
        Student student3 = new Student("小白",23,scores3);
        map.put(student3.getId(), student3);
        return new Library(map,classes);
    }







    private String systemOut() {
        return outContent.toString();
    }
}
