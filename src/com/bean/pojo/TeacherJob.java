package com.bean.pojo;

/**
 * @Author: yanjian
 * @CreateDate: 2023/11/15 16:03
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class TeacherJob extends Person{
    public String subject;
    public String school;
    private String teacherNo;

    // 构造器
    public TeacherJob(){
        super();
        this.school = "University";
        this.teacherNo = String.valueOf(System.currentTimeMillis());
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    @Override
    public void introduce(){
        System.out.println("My name is "+this.name+",a teacher in "+this.school+".");
    }

    @Override
    public String toString() {
        return "TeacherJob{" +
                "subject='" + subject + '\'' +
                ", school='" + school + '\'' +
                ", teacherNo='" + teacherNo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
