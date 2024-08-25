package yan.oop;

import java.util.Objects;

/**
 * @author yanjian
 */
public class StudentOop extends PersonOop {
    public double assets;
    private String studentId;
    private int GPA;
    public String grade;

    //无参构造器，实例化初始值，new这个对象时会进这个方法
    /*
     * 1、和类名相同
     * 2、没有返回值
     * new本质在于调用构造方法
     * 初始化对象的值
     */
    public StudentOop() {
        super();//构造器的第一句都是调用父类的构造器，如果没有则默认调用父类的无参构造器
        this.studentId = "2018001";
        this.grade = "Freshman";

        this.identityId = "1001";
        this.name = "鄢健";
        this.height = 180;
        this.weight = 160;
        this.age = 18;
    }

    //如果定义了有参构造，无参构造必须显示定义
    public StudentOop(String name) {
        this.name = name;
    }

    public StudentOop(String identityId, String studentId) {
        super(identityId);
        this.studentId = studentId;
    }

    public StudentOop(String humanity, String identityId, String name, int age, int height, int weight, String studentId) {
        super(humanity, identityId, name, age, height, weight);
        this.studentId = studentId;
    }

    public StudentOop(String humanity, String identityId, String name, int age, int height, int weight, String studentId, int GPA, String grade) {
        super(humanity, identityId, name, age, height, weight);
        this.studentId = studentId;
        this.GPA = GPA;
        this.grade = grade;
    }

    @Override
    public String toString() {
        String humanity = this.getHumanity();
        return "Student{" +
                "humanity=" + humanity +
                ", identityId='" + identityId + '\'' +
                ", name='" + name + '\'' +
                ", assets=" + assets +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", studentId='" + studentId + '\'' +
                ", GPA=" + GPA +
                ", grade='" + grade + '\'' +
                '}';
    }

    public boolean equalsStudent(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentOop studentOop = (StudentOop) o;
        return studentId.equals(studentOop.studentId) && Objects.equals(this.identityId, studentOop.identityId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentOop studentOop = (StudentOop) o;
        return Objects.equals(studentId, studentOop.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    /*
                 重写setAge方法
                 */
    @Override
    public void setAge(int age) {
        if (age > 150 || age < 0) {
            this.age = 0;
        } else {
            this.age = age;
        }
    }

    /*
     get and set
     */
    //提供public的对私有属性的get和set
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getGPA() {
        return GPA;
    }

    public void setGPA(int GPA) {
        this.GPA = GPA;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public double getAssets() {
        return super.getAssets();
    }

    @Override
    public void setAssets(double assets) {
        super.setAssets(assets);
    }

    @Override
    public String getHumanity() {
        return super.getHumanity();
    }

    @Override
    public void setHumanity(String humanity) {
        super.setHumanity(humanity);
    }
}
