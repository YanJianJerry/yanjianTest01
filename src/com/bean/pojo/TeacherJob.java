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
public class TeacherJob {
    public String subject;
    public String school;

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

    @Override
    public String toString() {
        return "TeacherJob{" +
                "subject='" + subject + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
