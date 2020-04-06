package leetcode.middleProblem;

/**
 * @author: Created by zhanghl
 * @Date: 2020/3/21
 */
public class _622ConstructCircularQueue {
		public static void main(String[] args) {
				_622ConstructCircularQueue circularQueue = new _622ConstructCircularQueue(3); // 设置长度为 3

				System.out.println(circularQueue.enQueue(1));  // 返回 true

				System.out.println(circularQueue.enQueue(2));  // 返回 true

				System.out.println(circularQueue.enQueue(3));  // 返回 true

				System.out.println(circularQueue.enQueue(4));  // 返回 false，队列已满

				System.out.println(circularQueue.Rear());  // 返回 3

				System.out.println(circularQueue.isFull());  // 返回 true

				System.out.println(circularQueue.deQueue());  // 返回 true

				System.out.println(circularQueue.enQueue(4));  // 返回 true

				System.out.println(circularQueue.Rear());  // 返回 4
		}


		/**
		 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。
		 * 它也被称为“环形缓冲器”。
		 *
		 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，
		 * 即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
		 *
		 * 你的实现应该支持如下操作：
		 *
		 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
		 * Front: 从队首获取元素。如果队列为空，返回 -1 。
		 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
		 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
		 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
		 * isEmpty(): 检查循环队列是否为空。
		 * isFull(): 检查循环队列是否已满。
		 */

		private int[] circleQueue;
		private int count ;
		private int capacity;
		private int frontIndex;
		/** Initialize your data structure here. Set the size of the queue to be k. */
		public _622ConstructCircularQueue(int k) {
				this.circleQueue = new int[k];
				this.count = 0;
				this.capacity = k;
				this.frontIndex = 0;
		}

		/** Insert an element into the circular queue. Return true if the operation is successful. */
		public boolean enQueue(int value) {
				if(this.capacity == this.count){
						return false;
				}
				this.circleQueue[(this.frontIndex + this.count) % this.capacity] = value;
				this.count += 1;
				return true;
		}

		/** Delete an element from the circular queue. Return true if the operation is successful. */
		public boolean deQueue() {
				if(this.count == 0){
						return false;
				}
				this.frontIndex = (this.frontIndex + 1) % this.capacity;
				this.count -=1;
				return true;
		}

		/** Get the front item from the queue. */
		public int Front() {
				if(this.count == 0){
						return -1;
				}
				return this.circleQueue[this.frontIndex];
		}

		/** Get the last item from the queue. */
		public int Rear() {
				if(this.count == 0){
						return -1;
				}
				return this.circleQueue[(this.frontIndex + this.count - 1) % this.capacity] ;
		}

		/** Checks whether the circular queue is empty or not. */
		public boolean isEmpty() {
				return this.count == 0;
		}

		/** Checks whether the circular queue is full or not. */
		public boolean isFull() {
				return this.count == this.capacity;
		}
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
