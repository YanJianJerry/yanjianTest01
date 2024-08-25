package exception;

public class PersonE {
    String name;

    int age;

    //
    public PersonE() {
    }

    public PersonE(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws IllegalAgeException {
        if (age > 200){
            throw new IllegalAgeException("年龄不能大于200");
        }else if(age < 0){
            throw new IllegalAgeException("年龄不能小于0");
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonE{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
