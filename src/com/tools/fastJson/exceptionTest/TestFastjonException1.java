package com.tools.fastJson.exceptionTest;

import com.alibaba.fastjson.JSON;
import com.bean.pojo.StudentBean;
import com.bean.pojo.TeacherJob;

import java.util.List;

public class TestFastjonException1 {
    public static void main(String[] args) {
//        String json = "[{'name':'张三','age':18,'job':'老师'}]";
        String json = "[{'subject':'数学','school':'大学','teacherNo':''}]";
        List<TeacherJob> teacherJobs = JSON.parseArray(json, TeacherJob.class);
        System.out.println(teacherJobs);

        String json2 = "[{'School':'大学','classroomno':'111','studentNo':'学生'}]";
        List<StudentBean> students = JSON.parseArray(json2, StudentBean.class);
        System.out.println(students);
    }
}
