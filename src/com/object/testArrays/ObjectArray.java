package com.object.testArrays;

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
    com.object.myObjects.StudentObj[] studentObjs = new com.object.myObjects.StudentObj[30];


    //add:把对象实例放到对象数组中(赋值)，放到第一个为null的地方
    /**
     * @author: yanjian
     * @description:
     * @date: 2022/5/2 1:40
     * @param: null
     * @return:
     */
    public boolean addStudent(com.object.myObjects.StudentObj studentObj) {
        int index = -1;
        for (int i = 0; i < studentObjs.length; i++) {
            if (studentObjs[i] == null) {
                index = i;
                break;
            }
        }

        //使数组没满的时候才增加对象
        if (index ==-1){
            System.out.println("数组已满，增加失败！");
            return false;
        }else{
            studentObjs[index] = studentObj;
            return true;
        }
    }

    //show:遍历显示 注意：Array.length方法，只是算数组的空间长度，并不知道里面有多少非空的值(有用的值)
    public void showStudents(){
        for (int i = 0; i < studentObjs.length; i++) {
            if (studentObjs[i] != null)
            System.out.println(studentObjs[i].getName()+"\t"+ studentObjs[i].getAge());
        }
    }
}
