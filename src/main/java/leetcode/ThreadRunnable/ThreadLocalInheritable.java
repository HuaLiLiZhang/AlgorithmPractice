package leetcode.ThreadRunnable;

import java.util.Date;

/**
 * @author: Created by zhanghl
 * @Date: 2020/4/12
 */
public class ThreadLocalInheritable {
		public static void main(String[] args) throws InterruptedException {
				ThreadTest t1 = new ThreadTest();
				t1.setName("				子线程1中取值: ");
				t1.start();
				for(int i = 0;i < 20;i++){
						if(ToolTest.threadLocal.get()==null){
								ToolTest.threadLocal.set(new Date());
						}
						System.out.println("				Main线程中取值: " + ToolTest.threadLocal.get());
				}
				Thread.sleep(5000);
				ThreadTest t2 = new ThreadTest();
				t2.setName("				子线程2中取值: ");
				t2.start();


		}
}
class ToolTest{
		public static ThreadLocalexe threadLocal = new ThreadLocalexe();
}
/**
* @Description: 子线程和父线程，使用InheritableThreadLocal可以使得子线程取得父线程继承下来的值。
* @Param: 当然子线程也能对值进行修改
* @return:
* @Auther: zhanghl
* @Date: 2020/4/12
*/
class ThreadLocalexe extends InheritableThreadLocal {
		@Override
		protected Object initialValue() {
				return System.currentTimeMillis();
		}

		@Override
		protected Object childValue(Object parentValue) {
				return parentValue + "   我在子线程加的----   ";
		}
}



class ThreadTest extends Thread{
		@Override
		public void run() {
				try{
						for(int i = 0;i < 20;i++){
								System.out.println(Thread.currentThread().getName()  + ToolTest.threadLocal.get());
								Thread.sleep(100);
						}
				}catch (InterruptedException e){
						e.printStackTrace();
				}
		}
}
