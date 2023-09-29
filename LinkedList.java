/**
 * This class is a singly linked list, where each node points to a single next
 * node (or null for the last node).
 */
public class LinkedList {
	
	/**
	 * This is a node.
	 */
	private class Node {
		
		private int value;
		private Node next;
		
		/**
		 * Nodes store an integer value and a pointer to the next node.
		 * 
		 * @param value: any integer
		 */
		Node(int value) {
			this.value = value;
			this.next = null;
		}	
		
	}
	
	private Node head;
	private Node tail;
	private int size;
	
	/**
	 * For the list, we store pointers to the head and tail and keep track of
	 * the number of nodes.
	 */
	LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * Adds a node to the end of the list.
	 * 
	 * @param value: any integer
	 */
	public void addLast(int value) {
		Node newNode = new Node(value);
		if (head == null) {
			head = newNode;
			tail = newNode;
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	
	/**
	 * @return the value of the tail node
	 */
	public int getLast() {
		return tail.value;
	}
	
	/**
	 * @param index: integer >= 0 and less than the size
	 * @return value at the index
	 */
	public int get(int index) {
		Node cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.value;
	}
	
	/**
	 * removes the tail of the list
	 */
	public void removeLast() {
		if (head != null) {
			Node prev = head;
			Node cur = head;
			while (cur.next != null) {
				prev = cur;
				cur = cur.next;
			}
			tail = prev;
			prev.next = null;
			size--;
		}
	}
	
	/**
	 * removes the head of the list
	 */
	public void removeFirst() {
		head = head.next;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkedList other = (LinkedList)obj;
		if (size != other.size)
			return false;
		Node cur = head;
		Node otherCur = other.head;
		for (int i = 0; i < size; i++) {
			if (cur.value != otherCur.value)
				return false;
			cur = cur.next;
			otherCur = otherCur.next;
		}
		return true;
	}
	
	@Override
	public String toString() {
		if (size == 0)
			return "{}";
		String str = "{";
		Node cur = head;
		while (cur.next != null) {
			str += cur.value + ",";
			cur = cur.next;
		}
		str += tail.value + "}";
		return str;
	}
	
	/**
	 * @return the number of nodes in the list
	 */
	public int size() {
		return size;
	}
}
