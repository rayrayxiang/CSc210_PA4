import java.util.Arrays;

/**
 * This class uses Java's built in array to represent an array of integers that 
 * can be resized.
 * 
 * @author Ray Xiang
 * @course CSc 210
 * @date 3/17/2023
 */
public class DynamicArray {
	
	private int[] array;
	private int size;
	private static final int defaultCapacity = 3;
	
	/**
	 * "array" stores the elements in order, and "size" keeps track of the 
	 * number of elements.
	 */
	DynamicArray() {
		array = new int[defaultCapacity];
		size = 0;
	}
	
	/**
	 * adds an element to the end of the dynamic array
	 * 
	 * @param value: any integer
	 */
	public void add(int value) {
		if (size >= array.length)
			resize(2*array.length);
		array[size] = value;
		size++;
	}
	
	/**
	 * modifies the element at a specific index. I only use this for circular 
	 * queue since modifying an existing element over-increments the size.
	 * 
	 * @param index: integer within the bounds for the private array
	 * @param value: any integer
	 */
	void set(int index, int value) {
		array[index] = value;
		size++;
	}
	
	/**
	 * copies the elements of the array into a bigger array
	 * 
	 * @param newLength: integer greater than or equal to the current size
	 */
	private void resize(int newLength) {
		int[] newArray = new int[newLength];
		size = newLength < size ? newLength : size;
		for (int i = 0; i < size; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	
	/**
	 * removes the element at a specific index and shifts other elements over
	 * 
	 * @param index: integer within the bounds for the dynamic array
	 */
	public void remove(int index) {
		size--;
		int[] newArray = new int[array.length];
		for (int i = 0; i < index; i++) {
			newArray[i] = array[i];
		}
		for (int i = index; i < size; i++) {
			newArray[i] = array[i + 1];
		}
		array = newArray;
	}
	
	/**
	 * @return the number of elements in the dynamic array
	 */
	public int size() {
		return size;
	}
	
	/**
	 * @return the length of the private array
	 */
	public int capacity() {
		return array.length;
	}
	
	/**
	 * @param index: integer within the bounds for the dynamic array
	 * @return element at that index
	 */
	public int get(int index) {
		return array[index];
	}
	
	@Override
	public String toString() {
		if (size == 0)
			return "{}";
		String str = "{";
		for (int i = 0; i < size - 1; i++) {
			str += array[i] + ",";
		}
		str += array[size - 1] + "}";
		return str;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DynamicArray other = (DynamicArray) obj;
		if (size != other.size)
			return false;
		if (!Arrays.equals(array, other.array))
			return false;
		return true;
	}
	
}
