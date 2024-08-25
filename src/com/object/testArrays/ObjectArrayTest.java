package com.object.testArrays;

/**
 * @Author: yanjian
 * @CreateDate: 2022/05/01 18:22
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class ObjectArrayTest {
    public static void main(String[] args) {

        //创建对象数组对象
        com.object.testArrays.ObjectArray objArray = new com.object.testArrays.ObjectArray();

        //创建学生对象，并放到对象数组中
        com.object.myObjects.StudentObj stu1 = new com.object.myObjects.StudentObj();
        stu1.setName("张三");
        stu1.setAge(12);
        objArray.addStudent(stu1);

        com.object.myObjects.StudentObj stu2 = new com.object.myObjects.StudentObj();
        stu2.setName("李四");
        stu2.setAge(14);
        objArray.addStudent(stu2);

        com.object.myObjects.StudentObj stu3 = new com.object.myObjects.StudentObj();
        stu3.setName("王五");
        stu3.setAge(16);
        objArray.addStudent(stu3);

        objArray.showStudents();

        System.out.println(stu3.toString());
    }
}
