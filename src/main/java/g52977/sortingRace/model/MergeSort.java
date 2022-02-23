package g52977.sortingRace.model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Merge sort algorithm implementation. The algorithm and documentation come
 * from < baeldung.com/java-merge-sort >
 *
 * @author 52977
 */
public class MergeSort implements Sort {

	private final String name = "MERGE";
	private int length;
	private long operations;
	private long duration;
	private LocalDateTime begin;
	private LocalDateTime end;

	/**
	 * Getter of nbOperations.
	 *
	 * @return The number of operations carried out by the sorting.
	 */
	@Override
	public long getOperations() {
		return this.operations;
	}

	/**
	 * Getter of time.
	 *
	 * @return The time used by the sorting.
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
	 * @return The name of the sort.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Sorts the numbers in an array in ascending order with the merge sort
	 * algorithm by calling a recursive method.
	 *
	 * @param array An array of int.
	 */
	@Override
	public void sort(int[] array) {
		begin = LocalDateTime.now();
		operations = 0;
		duration = 0;
		length = array.length;
		mergeSortRec(array, array.length);
		end = LocalDateTime.now();
		this.duration = Duration.between(begin, end).toMillis();
	}

	/**
	 * Sort the array recursively.
	 *
	 * @param array The input array.
	 * @param n The length of the input array.
	 */
	private void mergeSortRec(int[] array, int n) {
		if (n < 2) {
			operations++;
			return;
		}
		int mid = n / 2;
		int[] l = new int[mid];
		int[] r = new int[n - mid];
		operations += 3;

		for (int i = 0; i < mid; i++) {
			l[i] = array[i];
			operations++;
		}
		for (int i = mid; i < n; i++) {
			r[i - mid] = array[i];
			operations++;
		}
		mergeSortRec(l, mid);
		mergeSortRec(r, n - mid);

		merge(array, l, r, mid, n - mid);
	}

	/**
	 * Compares the elements of both sub-arrays one by one and places the
	 * smaller element into the input array.
	 *
	 * @param array The input array.
	 * @param l A first sub-array.
	 * @param r A second sub-array.
	 * @param left Starting indice.
	 * @param right End indice.
	 */
	private void merge(int[] array, int[] l, int[] r, int left, int right) {
		int i = 0, j = 0, k = 0;
		operations += 3;
		while (i < left && j < right) {
			if (l[i] <= r[j]) {
				array[k++] = l[i++];
				operations += 2;
			} else {
				array[k++] = r[j++];
				operations++;
			}
		}
		while (i < left) {
			array[k++] = l[i++];
			operations += 2;
		}
		while (j < right) {
			array[k++] = r[j++];
			operations += 2;
		}
	}

}
