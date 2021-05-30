package com.wxt;

/**
 * @Auther: ThomasWu
 * @Date: 2021/5/30 14:06
 * @Description:
 */
public class Student {
    private String studentName;
    private Integer sex;
    public Integer studentAge;
    public Student() {
    }

    public Student(String studentName) {
        this.studentName = studentName;
    }
    private Student(String studentName, Integer sex, Integer studentAge) {
        this.studentName = studentName;
        this.sex = sex;
        this.studentAge = studentAge;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    private String show(String message) {
        System.out.println("show: " + studentName + "," + studentAge + "," + message);
        return "testReturnValue";
    }
}
