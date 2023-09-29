/**
 * This is a dynamic array implementation of a stack. Users can access only the
 * top element.
 */
public class ArrayStack implements StackInterface {
	
	private DynamicArray stack;
	private int size;
	
	/**
	 * elements are stored in a dynamic array, and we keep track of the size
	 */
	ArrayStack() {
		stack = new DynamicArray();
		size = 0;
	}
	
	ArrayStack(ArrayStack original) {
		stack = new DynamicArray();
		size = original.size;
		int[] values = new int[size];
		for (int i = size - 1; i >= 0; i--) {
			values[i] = original.pop();
		}
		for (int value : values) {
			stack.add(value);
			original.push(value);
		}
	}

	@Override
	/**
	 * O(n)
	 */
	public void push(int value) {
		stack.add(value);
		size++;
	}

	@Override
	/**
	 * O(n)
	 */
	public int pop() {
		if (size == 0)
			return -1;
		int top = stack.get(size - 1);
		stack.remove(size - 1);
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
		return stack.get(size - 1);
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
		stack = new DynamicArray();
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
