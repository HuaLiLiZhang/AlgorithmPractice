package leetcode.ThreadRunnable;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author: Created by zhanghl
 * @Date: 2020/4/6
 */
public class PipedInputOutputStreamTest_1 {
		public static void main(String[] args) {
				try{
						WriteReadData1 wr = new WriteReadData1();
						PipedInputStream inputStream = new PipedInputStream();
						PipedOutputStream outputStream = new PipedOutputStream();
						outputStream.connect(inputStream);
						// inputStream.connect(outputStream);
						ThreadWrite1 threadWrite = new ThreadWrite1(wr, outputStream);
						threadWrite.start();
						Thread.sleep(2000);
						ThreadRead1 threadRead = new ThreadRead1(wr, inputStream);
						threadRead.start();
						threadWrite.join();
						threadRead.join();
						System.out.println("等其他线程结束再结束");
				}catch(IOException e){
						e.printStackTrace();
				}catch (InterruptedException e){
						e.printStackTrace();
				}
		}
}

class WriteReadData1{
		synchronized public void writeMethod(PipedOutputStream out){
				try{
						System.out.println("write: ");
						for(int i = 0;i<300;i++){
								String outData = "" + (i + 1);
								out.write(outData.getBytes());
								System.out.print(outData);
						}
						System.out.println();
						System.out.println("写完数据，开始读数据，这个读数据的开始是从哪里呢？");
						out.close();
				}catch(IOException o){
						o.printStackTrace();
				}
		}

		synchronized public void readMethod(PipedInputStream input){
				try{
						System.out.println("read: ");
						byte [] byteArray = new byte[20];
						int readLength = input.read(byteArray);
//						System.out.println("由于没有数据：读数据阻塞在read这里");
						while (readLength!=-1){
								String newData = new String(byteArray, 0, readLength);
								System.out.print(newData);
								readLength = input.read(byteArray);
						}
						System.out.println();
						input.close();
				}catch(IOException o){
						o.printStackTrace();
				}
		}
}

class ThreadWrite1 extends Thread{
		private WriteReadData1 writeData;
		private PipedOutputStream out;
		public  ThreadWrite1(WriteReadData1 writeData, PipedOutputStream out){
				this.writeData = writeData;
				this.out = out;
		}

		@Override
		public void run() {
				super.run();
				writeData.writeMethod(out);
		}
}


class ThreadRead1 extends Thread{
		private WriteReadData1 readData;
		private PipedInputStream input;
		public  ThreadRead1(WriteReadData1 readData, PipedInputStream input){
				this.readData = readData;
				this.input = input;
		}

		@Override
		public void run() {
				super.run();
				readData.readMethod(input);
		}
}
