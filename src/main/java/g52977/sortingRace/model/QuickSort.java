package g52977.sortingRace.model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Merge sort algorithm implementation. The algorithm and documentation come
 * from < www.baeldung.com/java-quicksort >
 * 
 * @author 52977
 */
public class QuickSort implements Sort {
	
	private final String name = "QUICK";
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
	 * Sorts the numbers in an array in ascending order with the quick sort
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
		quickSort(array, 0, array.length - 1);
		end = LocalDateTime.now();
		this.duration = Duration.between(begin, end).toMillis();		
	}

	public void quickSort(int arr[], int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end);
			operations += 2;

			quickSort(arr, begin, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, end);
		}
	}

	private int partition(int arr[], int begin, int end) {
		int pivot = arr[end];
		int i = (begin - 1);
		operations += 2;

		for (int j = begin; j < end; j++) {
			if (arr[j] <= pivot) {
				i++;

				int swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
				operations += 4;
			}
			operations++;
		}

		int swapTemp = arr[i + 1];
		arr[i + 1] = arr[end];
		arr[end] = swapTemp;
		operations += 3;

		return i + 1;
	}		

}
