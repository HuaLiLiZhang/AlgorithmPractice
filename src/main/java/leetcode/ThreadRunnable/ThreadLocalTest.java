package leetcode.ThreadRunnable;

import java.util.Date;

/**
 * @author: Created by zhanghl
 * @Date: 2020/4/12
 */
public class ThreadLocalTest {
		public static void main(String[] args) throws InterruptedException {
				for(int i = 0;i < 20 ;i++){
						System.out.println("  在main线程中取值 = " + Tool.threadLocal.get());
						Thread.sleep(100);
				}
				Thread.sleep(5000);
				ThreadA threadA = new ThreadA();
				Thread t1 = new Thread(threadA);
				t1.setName("A");
				t1.start();

				System.out.println(Tool.threadLocal.get());
				System.out.println(Tool.threadLocal.get());

		}
}

/**
* @Description: ThreadLocal 每个线程独享一份变量
* @Param:
* @return:
* @Auther: zhanghl
* @Date: 2020/4/12
*/
class Tool {
		public static ThreadLocalex1 threadLocal = new ThreadLocalex1();
}
class ThreadLocalex1 extends ThreadLocal {
		@Override
		protected Object initialValue() {
				return System.currentTimeMillis();
		}
}



class ThreadA implements Runnable{
		@Override
		public void run() {
				try{
						for(int i = 0;i < 20;i++){
								if(Tool.threadLocal.get()==null){
										Tool.threadLocal.set(new Date());
								}
								System.out.println(Thread.currentThread().getName() + ": " + Tool.threadLocal.get());
								Thread.sleep(100);
						}
				}catch (InterruptedException e){
						e.printStackTrace();
				}
		}
}

