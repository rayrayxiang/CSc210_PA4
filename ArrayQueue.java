/**
 * This is a dynamic array implementation of a queue. Users can remove from the 
 * front and add to the back.
 */
public class ArrayQueue implements QueueInterface {
	
	private DynamicArray queue;
	private int size;
	private int front;
	private int back;
	private boolean hi;
	
	/**
	 * A dynamic array stores the elements in order. We keep track of the 
	 * locations of the front and back, the number of elements in the array, 
	 * and whether or not elements have queued into this array.
	 */
	ArrayQueue() {
		queue = new DynamicArray();
		size = 0;
		front = 0;
		back = 0;
		hi = true;
	}
	
	ArrayQueue(ArrayQueue original) {
		queue = new DynamicArray();
		size = original.size;
		int[] values = new int[size];
		for (int i = 0; i < size; i++) {
			values[i] = original.dequeue();
		}
		for (int value : values) {
			queue.add(value);
			original.enqueue(value);
		}
		hi = original.state();
	}
	
	@Override
	/**
	 * O(n)
	 */
	public void enqueue(int value) {
		if (hi == true) {
			queue.add(value);
			back = 1;
			hi = false;
		}
		else if (front != back) {
			queue.set(back, value);
			back = (back + 1)%queue.capacity();
		}
		// expand capacity
		else {
			DynamicArray newQueue = new DynamicArray();
			for (int i = front; i < queue.capacity(); i++) {
				newQueue.add(queue.get(i));
			}
			for (int i = 0; i < back; i++) {
				newQueue.add(queue.get(i));
			}
			newQueue.add(value);
			queue = newQueue;
			front = 0;
			back = queue.size()%queue.capacity();
		}
		size++;
	}

	@Override
	/**
	 * O(1)
	 */
	public int dequeue() {
		if (size == 0)
			return -1;
		int frontVal = queue.get(front);
		if (back == 0) {
			back = queue.capacity() - 1;
		}
		else
			back--;
		queue.remove(front);
		size--;
		return frontVal;
	}

	@Override
	/**
	 * O(1)
	 */
	public int peek() {
		if (size == 0)
			return -1;
		return queue.get(front);
	}

	@Override
	/**
	 * O(1)
	 */
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	@Override
	/**
	 * O(1)
	 */
	public int size() {
		return size;
	}

	@Override
	/**
	 * O(1)
	 */
	public void clear() {
		queue = new DynamicArray();
		size = 0;
		front = 0;
		back = 0;
	}
	
	@Override
	/**
	 * O(n)
	 */
	public String toString() {
		return queue.toString();
	}
	
	@Override
	/**
	 * O(n)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof QueueInterface))
			return false;
		return checkEquality((QueueInterface)obj);
	}
	
	private boolean state() {
		return hi;
	}
	
	private boolean checkEquality(QueueInterface obj) {
		if (size != obj.size())
			return false;
		boolean result = true;
		ArrayQueue temp1 = new ArrayQueue();
		ArrayQueue temp2 = new ArrayQueue();
		while (!this.isEmpty()) {
			int a = this.dequeue();
			int b = obj.dequeue();
			temp1.enqueue(a);
			temp2.enqueue(b);
			if (a != b)
				result = false;
		}
		while (!temp1.isEmpty()) {
			this.enqueue(temp1.dequeue());
			obj.enqueue(temp2.dequeue());
		}
		return result;
	}
	
}
