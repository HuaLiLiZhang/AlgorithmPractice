package leetcode.ThreadRunnable;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Date;

/**
 * @author: Created by zhanghl
 * @Date: 2020/4/6
 */
public class PipedInputOutputStreamTest {
		public static void main(String[] args) {
				try{
						WriteData writeData = new WriteData();
						ReadData readData = new ReadData();
						PipedInputStream inputStream = new PipedInputStream();
						PipedOutputStream outputStream = new PipedOutputStream();
						outputStream.connect(inputStream);
						// inputStream.connect(outputStream);
						ThreadRead threadRead = new ThreadRead(readData, inputStream);
						threadRead.start();
						Thread.sleep(4000);
						ThreadWrite threadWrite = new ThreadWrite(writeData, outputStream);
						threadWrite.start();
						System.out.println("等其他线程结束再结束");
				}catch(IOException e){
						e.printStackTrace();
				}catch (InterruptedException e){
						e.printStackTrace();
				}
		}
}
class WriteData{
		public void writeMethod(PipedOutputStream out){
				try{
						System.out.println("write: ");
						for(int i = 0;i<500;i++){
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
}

class ReadData{
		public void readMethod(PipedInputStream input){
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

class ThreadWrite extends Thread{
		private WriteData writeData;
		private PipedOutputStream out;
		public  ThreadWrite(WriteData writeData, PipedOutputStream out){
				this.writeData = writeData;
				this.out = out;
		}

		@Override
		public void run() {
				super.run();
				writeData.writeMethod(out);
		}
}


class ThreadRead extends Thread{
		private ReadData readData;
		private PipedInputStream input;
		public  ThreadRead(ReadData readData, PipedInputStream input){
				this.readData = readData;
				this.input = input;
		}

		@Override
		public void run() {
				super.run();
				readData.readMethod(input);
		}
}
