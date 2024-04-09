package Object.Arrays;

import com.Object.MyObjects.Student;

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
        com.Object.Arrays.ObjectArray objArray = new com.Object.Arrays.ObjectArray();

        //创建学生对象，并放到对象数组中
        Student stu1 = new Student();
        stu1.setName("张三");
        stu1.setAge(12);
        objArray.addStudent(stu1);

        Student stu2 = new Student();
        stu2.setName("李四");
        stu2.setAge(14);
        objArray.addStudent(stu2);

        Student stu3 = new Student();
        stu3.setName("王五");
        stu3.setAge(16);
        objArray.addStudent(stu3);

        objArray.showStudents();

        System.out.println(stu3.toString());
    }
}
