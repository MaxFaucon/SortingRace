package g52977.sortingRace.model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Insertion sort algorithm implementation. The algorithm comes from
 * < www.baeldung.com/java-insertion-sort >
 *
 * @author 52977
 */
public class InsertionSort implements Sort {

	private final String name = "INSERTION";
	private int length;
	private long operations;
	private long duration;
	private LocalDateTime begin;
	private LocalDateTime end;

	/**
	 * Getter of operations.
	 *
	 * @return The number of operations carried out by the sorting.
	 */
	@Override
	public long getOperations() {
		return this.operations;
	}

	/**
	 * Getter of duration.
	 *
	 * @return The duration used by the sorting.
	 */
	@Override
	public long getDuration() {
		return this.duration;
	}

	/**
	 * Getter of length.
	 *
	 * @return The length of the sorted array.
	 */
	@Override
	public int getLength() {
		return this.length;
	}

	/**
	 * Getter of name.
	 *
	 * @return The name of the name.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Sorts the numbers in an array in ascending order with the insertion sort
	 * algorithm.
	 *
	 * @param array An array of int.
	 */
	@Override
	public void sort(int[] array) {
		begin = LocalDateTime.now();
		operations = 0;
		duration = 0;
		length = array.length;
		insertionSortImperative(array);
		end = LocalDateTime.now();
		this.duration = Duration.between(begin, end).toMillis();
	}

	public void insertionSortImperative(int[] input) {
		for (int i = 1; i < input.length; i++) {
			int key = input[i];
			int j = i - 1;
			operations += 2;
			while (j >= 0 && input[j] > key) {
				input[j + 1] = input[j];
				j = j - 1;
				operations += 2;
			}
			input[j + 1] = key;
			operations += 1;
		}
	}

}
