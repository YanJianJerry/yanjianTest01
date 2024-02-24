/**
 *
 */
package com.javaTest;

/**
 * @author Sunny
 *
 */
public class TestMemory {

	/**
	 *
	 */
	public TestMemory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System. out .println( " 内存信息 :" + toMemoryInfo());
	}

	 /**
     * 获取当前 jvm 的内存信息
     *
     * @return
     */
    public static String toMemoryInfo() {

       Runtime currRuntime = Runtime.getRuntime ();
       int nFreeMemory = ( int ) (currRuntime.freeMemory() / 1024 / 1024);
       int nTotalMemory = ( int ) (currRuntime.totalMemory() / 1024 / 1024);
       return nFreeMemory + "M/" + nTotalMemory +"M(free/total)" ;
    }

}
