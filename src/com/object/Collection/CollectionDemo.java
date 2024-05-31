package object.Collection;

import com.Object.MyObjects.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Author: yanjian
 * @CreateDate: 2022/04/17 14:48
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class CollectionDemo {
    public static void main(String[] args) {

        //1. boolean collection.add(E e);
        // 将指定的元素追加到此列表的末尾（可选操作）
        //确保此集合包含指定的元素（可选操作）。 如果此集合由于调用而更改，则返回true 。 （如果此集合不允许重复，并且已包含指定的元素，则返回false。 ）
        //支持此操作的集合可能会限制可能添加到此集合的元素。 特别是一些集合拒绝添加null种元素，和其他人将强加可添加元素的类型限制。 收集类应在其文档中明确说明可能添加哪些元素的限制。
        //如果一个集合拒绝添加一个特定的元素，除了它已经包含该元素之外，它必须抛出异常（而不是返回false ）。 这保留了一个集合在此调用返回后始终包含指定元素的不变量。
        Collection col1 = new ArrayList();//Collection是接口，不能被实例化(new)，需要new一个它的子实现类
        boolean flag1 = col1.add('a');
        col1.add("b");
        col1.add('c');
        System.out.println("col1为 "+col1);//[a, b, c] 打印的不是地址，而是字符串，说明重写了toString()方法

        //2. boolean collection.add(E e); 把对象放入集合中
        Student stu1 = new Student("张三",18);
        Collection col2 = new ArrayList();
        col2.add(stu1);
        System.out.println("col2为 "+col2);//[张三 -- 18]

        //3.boolean collection.addAll(Collection<? extends E> c);
        // 将指定集合中的所有元素添加到此集合（可选操作）。
        Collection col3 = new ArrayList();
        col3.addAll(col1);
        System.out.println("col3为 "+col3);//[a, b, c]

        //4. void collection.clear();
        // 从此集合中删除所有元素（可选操作）。 此方法返回后，集合将为空。
        Collection col4 = new ArrayList();
        col4.add('a');
        col4.clear();
        System.out.println("col4为 "+col4);//[]

        //5. int collection.size();
        // 返回此集合中的元素数（真实有效的数据个数）。
        // 如果此收藏包含超过Integer.MAX_VALUE个元素，则返回Integer.MAX_VALUE 。
        int size1 = col1.size();
        int size2 = col2.size();
        int size3 = col4.size();
        System.out.println(size1+","+size2+","+size3);//3,1,0

        //6. boolean collection.contains(Object o);
        // 如果此集合包含指定的元素，则返回 true 。
        Collection col6 = new ArrayList();
        col6.addAll(col1);
        boolean isContains1 = col6.contains("a");
        boolean isContains2 = col6.contains("b");
        System.out.println(isContains1);//注意区分单个字符的字符串String和字符char
        System.out.println(isContains2);

        //7. boolean collection.containsAll(Collection<?> c);
        // 如果此集合包含指定 集合中的所有元素，则返回true。
        Collection col7 = new ArrayList(col1);
        col7.add("good");
        System.out.println("col7为 "+col7);
        boolean isContainsAll = col7.containsAll(col1);
        System.out.println(isContainsAll);

        //8. boolean collection.isEmpty();
        // 如果此集合不包含元素，则返回 true 。
        Collection col8 = new ArrayList();
        col8.addAll(col1);
        col8.clear();
        boolean isEmpty =  col8.isEmpty();
        System.out.println("isEmpty :"+isEmpty);

        //9. Iterator<E> collection.iterator();
        // 返回此集合中的元素的迭代器。没有关于元素返回顺序的保证（除非这个集合是提供保证的某个类的实例）。
        Iterator iter = col1.iterator();
        //iter.hasNext(); 判断集合是否有下一个元素，有则返回true。
        while(iter.hasNext()){
            Object result = iter.next();//取出下一个元素
            System.out.println(result);
        }

        //10. boolean remove(Object o)


    }
}
