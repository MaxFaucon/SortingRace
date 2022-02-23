package model;

import g52977.sortingRace.model.Sort;
import g52977.sortingRace.model.MergeSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the merge sort algorithm.
 * 
 * @author 52977
 */
public class MergeSortTest {

	/**
	 * General test of sort method, of class MergeSort.
	 * From < baeldung.com/java-merge-sort >
	 */
	@Test
	public void testSortGeneral() {
		System.out.println("testSortGeneral");
		int[] array = { 2, 1, 4, 6, 3, 5};
		int[] sortedArray = {1, 2, 3, 4, 5, 6};
		Sort mergeSort = new MergeSort();
		mergeSort.sort(array);
		
		assertArrayEquals(array, sortedArray);
	}
	
	/**
	 * Test of sort method, of class MergeSort with negative values.
	 */
	@Test
	public void testSortNegative() {
		System.out.println("testSortNegative");
		int[] array = { -2, -1, -4, -6, -3, -5};
		int[] sortedArray = {-6, -5, -4, -3, -2, -1};
		Sort mergeSort = new MergeSort();
		mergeSort.sort(array);
		
		assertArrayEquals(array, sortedArray);
	}
	
	/**
	 * Test of sort method, of class MergeSort with same values.
	 */
	@Test
	public void testSortSameValues() {
		System.out.println("testSortSameValues");
		int[] array = {5, 5, 6, 3, 2, 4, 1, 1};
		int[] sortedArray = {1, 1, 2, 3, 4, 5, 5, 6};
		Sort mergeSort = new MergeSort();
		mergeSort.sort(array);
		
		assertArrayEquals(array, sortedArray);
	}
}
