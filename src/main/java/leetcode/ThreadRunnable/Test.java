package leetcode.ThreadRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Created by zhanghl
 * @Date: 2020/4/5
 */
public class Test {
		public static void main(String[] args) throws InterruptedException {
				Mylist mylist = new Mylist();
				Myservice t1 = new Myservice(mylist);
				Myservice t2 = new Myservice(mylist);
				t1.start();
				t2.start();
				Thread.sleep(6000);
				System.out.println("listSize: "+ mylist.getSize());
		}

}
class Myservice extends Thread{

		private Mylist mylist;
		public Myservice (Mylist mylist){
				super();
				this.mylist = mylist;
		}
		@Override
		public void run() {
				Myservice myservice = new Myservice(mylist);
				myservice.addServiceMethod(mylist, Thread.currentThread().getName());
		}

		public Mylist addServiceMethod(Mylist list, String data){
				try{
					synchronized (list){
							System.out.println("进入同步：" + Thread.currentThread().getName());
							if(list.getSize()<1){
									Thread.sleep(2000);
									System.out.println("添加数据前： " + Thread.currentThread().getName());
									list.add(data);
							}
					}
				}catch (Exception e){
						e.printStackTrace();
				}
				return list;
		}

}

class Mylist implements Runnable{
		private List list = new ArrayList();
		synchronized public void add(String data){
				list.add(data);
		}
		synchronized public int getSize(){
				return list.size();
		}

		@Override
		public void run() {
		}
}