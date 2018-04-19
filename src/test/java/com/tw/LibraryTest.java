package com.tw;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
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

    @Test
    public void testShowFirstInterface() {
        String result = "请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n";
        Library library = new Library();
        library.showFirst();
        assertEquals(systemOut(),result);
    }

    @Test
    public void testShowSecondInterface() {
        String result = "请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n";
        Library library = new Library();
        library.showSecond();
        assertEquals(systemOut(),result);
    }

    @Test
    public void testShowFirstInterfaceIsFault() {
        String result = "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n";
        Library library = new Library();
        library.showFirstFault();
        assertEquals(systemOut(),result);
    }

    @Test
    public void testShowSecondInterfaceIsFault() {
        String result = "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n";
        Library library = new Library();
        library.showSecondFault();
        assertEquals(systemOut(),result);
    }





    private String systemOut() {
        return outContent.toString();
    }
}
