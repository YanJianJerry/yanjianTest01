package com.bean.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudentBean extends Person{
    public String School;
    public int classroomNo;
    private String studentNo;

    public StudentBean() {
        super();
        this.classroomNo = 1;
        this.School = "University";
        this.studentNo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    @Override
    public void introduce() {
        super.introduce();
        System.out.println("I am a student in "+this.School+",and my studentNo is "+this.studentNo+".");
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "School='" + School + '\'' +
                ", classroomNo=" + classroomNo +
                ", studentNo='" + studentNo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public int getClassroomNo() {
        return classroomNo;
    }

    public void setClassroomNo(int classroomNo) {
        this.classroomNo = classroomNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
}
