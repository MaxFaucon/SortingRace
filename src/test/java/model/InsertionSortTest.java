package model;

import g52977.sortingRace.model.Sort;
import g52977.sortingRace.model.InsertionSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the insertion sort algorithm.
 * 
 * @author 52977
 */
public class InsertionSortTest {
	
	/**
	 * General test of sort method, of class InsertionSort.
	 */
	@Test
	public void testSortGeneral() {
		System.out.println("testSortGeneral");
		int[] array = { 2, 1, 4, 6, 3, 5};
		int[] sortedArray = {1, 2, 3, 4, 5, 6};
		Sort insertionSort = new InsertionSort();
		insertionSort.sort(array);
		
		assertArrayEquals(array, sortedArray);
	}
	
	/**
	 * Test of sort method, of class InsertionSort with negative values.
	 */
	@Test
	public void testSortNegative() {
		System.out.println("testSortNegative");
		int[] array = { -2, -1, -4, -6, -3, -5};
		int[] sortedArray = {-6, -5, -4, -3, -2, -1};
		Sort insertionSort = new InsertionSort();
		insertionSort.sort(array);
		
		assertArrayEquals(array, sortedArray);
	}
	
	/**
	 * Test of sort method, of class InsertionSort with same values.
	 */
	@Test
	public void testSortSameValues() {
		System.out.println("testSortSameValues");
		int[] array = {5, 5, 6, 3, 2, 4, 1, 1};
		int[] sortedArray = {1, 1, 2, 3, 4, 5, 5, 6};
		Sort insertionSort = new InsertionSort();
		insertionSort.sort(array);
		
		assertArrayEquals(array, sortedArray);
	}
}
