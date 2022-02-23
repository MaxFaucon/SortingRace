package g52977.sortingRace.model;

/**
 * Interface for all the sort algorithms.
 * Contains all the informations about the sortings.
 *
 * @author 52977
 */
public interface Sort {

	/**
	 * Sorts the numbers in an array in ascending order.
	 *
	 * @param array An array of int.
	 */
	public void sort(int[] array);

	/**
	 * Getter of nbOperations.
	 *
	 * @return The number of operations carried out by the sorting.
	 */
	public long getOperations();

	/**
	 * Getter of time.
	 *
	 * @return The time used by the sorting.
	 */
	public long getDuration();

	/**
	 * Getter of length.
	 *
	 * @return The length of the sorted array.
	 */
	public int getLength();

	/**
	 * Getter of name.
	 *
	 * @return The name of the sort.
	 */
	public String getName();

}
