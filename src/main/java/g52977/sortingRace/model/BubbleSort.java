package g52977.sortingRace.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

/**
 * Bubble sort algorithm implementation. The algorithm comes from
 * < baeldung.com/java-bubble-sort >
 *
 * @author 52977
 */
public class BubbleSort implements Sort {

	private final String name = "BUBBLE";
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
	 * Sorts the numbers in an array in ascending order with the bubble sort
	 * algorithm.
	 *
	 * @param array An array of int.
	 */
	@Override
	public void sort(int[] array) {
		operations = 0;
		duration = 0;
		begin = LocalDateTime.now();
		length = array.length;
		int n = array.length;
		operations += 3;
		IntStream.range(0, n - 1)
			.flatMap(i -> IntStream.range(1, n - i))
			.forEach(j -> {
				if (array[j - 1] > array[j]) {
					int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
					operations += 4;
				}
			});
		end = LocalDateTime.now();
		this.duration = Duration.between(begin, end).toMillis();
	}

}
