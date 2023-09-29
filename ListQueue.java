/**
 * This is a linked list implementation of a queue. Users can remove from the 
 * front and add to the back.
 */
public class ListQueue implements QueueInterface {
	
	private LinkedList queue;
	private int size;
	
	/**
	 * A linked list stores the elements in order the number of elements in the 
	 * array.
	 */
	ListQueue() {
		queue = new LinkedList();
		size = 0;
	}
	
	ListQueue(ListQueue original) {
		queue = new LinkedList();
		size = original.size;
		int[] values = new int[size];
		for (int i = 0; i < size; i++) {
			values[i] = original.dequeue();
		}
		for (int value : values) {
			queue.addLast(value);
			original.enqueue(value);
		}
	}
	
	@Override
	/**
	 * O(1)
	 */
	public void enqueue(int value) {
		queue.addLast(value);
		size++;
	}

	@Override
	/**
	 * O(1)
	 */
	public int dequeue() {
		if (size == 0)
			return -1;
		int frontVal = queue.get(0);
		queue.removeFirst();
		size--;
		return frontVal;
	}

	@Override
	/**
	 * O(1)
	 */
	public int peek() {
		return queue.get(0);
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
		queue = new LinkedList();
		size = 0;
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
