package com.Object.Arrays;

import com.Object.MyObjects.Student;

/**
 * @Author: yanjian
 * @CreateDate: 2022/05/01 17:49
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class ObjectArray {

    //{null,null,```,null} 创建了空间为30的对象数组
    Student[] students = new Student[30];


    //add:把对象实例放到对象数组中(赋值)，放到第一个为null的地方
    /**
     * @author: yanjian
     * @description:
     * @date: 2022/5/2 1:40
     * @param: null
     * @return:
     */
    public boolean addStudent(Student student) {
        int index = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                index = i;
                break;
            }
        }

        //使数组没满的时候才增加对象
        if (index ==-1){
            System.out.println("数组已满，增加失败！");
            return false;
        }else{
            students[index] = student;
            return true;
        }
    }

    //show:遍历显示 注意：Array.length方法，只是算数组的空间长度，并不知道里面有多少非空的值(有用的值)
    public void showStudents(){
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null)
            System.out.println(students[i].getName()+"\t"+students[i].getAge());
        }
    }
}
