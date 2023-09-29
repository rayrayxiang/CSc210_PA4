/**
 * This is a linked list implementation of a stack. Users can access only the
 * top element.
 */
public class ListStack implements StackInterface{
	private LinkedList stack;
	private int size;
	
	/**
	 * elements are stored in a linked list, and we keep track of the size
	 */
	ListStack() {
		stack = new LinkedList();
		size = 0;
	}
	
	ListStack(ListStack original) {
		stack = new LinkedList();
		size = original.size;
		int[] values = new int[size];
		for (int i = size - 1; i >= 0; i--) {
			values[i] = original.pop();
		}
		for (int value : values) {
			stack.addLast(value);
			original.push(value);
		}
	}
	
	@Override
	/**
	 * O(1)
	 */
	public void push(int value) {
		stack.addLast(value);
		size++;
	}

	@Override
	/**
	 * O(1)
	 */
	public int pop() {
		if (size == 0)
			return -1;
		int top = stack.getLast();
		stack.removeLast();
		size--;
		return top;
	}

	@Override
	/**
	 * O(1)
	 */
	public int peek() {
		if (size == 0)
			return -1;
		return stack.getLast();
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
		stack = new LinkedList();
		size = 0;
	}
	
	@Override
	/**
	 * O(n)
	 */
	public String toString() {
		return stack.toString();
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
		if (!(obj instanceof StackInterface))
			return false;
		return checkEquality((StackInterface)obj);
	}
	
	private boolean checkEquality(StackInterface obj) {
		if (size != obj.size())
			return false;
		boolean result = true;
		ArrayStack temp1 = new ArrayStack();
		ArrayStack temp2 = new ArrayStack();
		while (!this.isEmpty()) {
			int a = this.pop();
			int b = obj.pop();
			temp1.push(a);
			temp2.push(b);
			if (a != b)
				result = false;
		}
		while (!temp1.isEmpty()) {
			this.push(temp1.pop());
			obj.push(temp2.pop());
		}
		return result;
	}
}
