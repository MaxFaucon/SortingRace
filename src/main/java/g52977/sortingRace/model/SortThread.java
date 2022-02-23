package g52977.sortingRace.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents a thread that makes a sort.
 * 
 * @author 52977
 */
public class SortThread extends Thread {

	private Sort sort;
	private final String sortType;
	private final JobManager manager;
	private int[] array;	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Constructor of MyThread
	 * 
	 * @param manager Manages the various sorting to be performed.
	 * @param sortType The sorting type.
	 */
	public SortThread(JobManager manager, String sortType) {		
		this.manager = manager;
		this.sortType = sortType;
	}

	/**
	 * Takes an array, sort this array and warns the model when the array
	 * is sorted.
	 */
	@Override
	public void run() {
		array = manager.getNext();
		
		while (array != null) {
			sortInstanciation(sortType);
			sort.sort(array);
			pcs.firePropertyChange("ARRAY_SORTED", null, sort);
			array = manager.getNext();
		}
	}

	/**
	 * Add a listener.
	 *
	 * @param listener A listener of the model.
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Instanciates a type of sorting according to the sorting name.
	 *
	 * @param sortName The sorting name.
	 */
	private void sortInstanciation(String sortName) {
		switch (sortName) {
			case "Tri bulle" -> this.sort = new BubbleSort();
			case "Tri fusion" -> this.sort = new MergeSort();
			case "Tri rapide" -> this.sort = new QuickSort();
			case "Tri insertion" -> this.sort = new InsertionSort();
		}
	}

}
