package exception;

public class tesAgeException {
    public static void main(String[] args) throws IllegalAgeException {
//        test1();
        test2();
    }

    public static void test1() throws IllegalAgeException {
        PersonE personE = new PersonE();
        personE.setAge(250);
    }

    public static void test2() {
        try{
            PersonE personE = new PersonE();
            personE.setAge(-1);
        }catch (IllegalAgeException e){
            System.out.println(e.getMessage());
            System.out.println(e);
            e.printStackTrace();
        }

    }
}
