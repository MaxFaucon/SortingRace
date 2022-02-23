package g52977.sortingRace.model;

import java.util.Random;

/**
 *
 * @author 52977
 */
public class JobManager {

	private final int length;
	private int current;
	private static final Random r = new Random();

	public JobManager(int length) {
		this.current = 0;
		this.length = length;
	}

	/**
	 * 
	 * @return 
	 */
	public synchronized int[] getNext() {
		int[] array = null;
		if (current <= length) {
			array = arrayGeneration(current);
			current += length / 10;
		}

		return array;
	}

	/**
	 * Generates a random array of a fixed length.
	 *
	 * @param length The length of the array.
	 * @return The generated array.
	 */
	private int[] arrayGeneration(int length) {
		int[] array = new int[length];

		for (int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(array.length);
		}

		return array;
	}

}
