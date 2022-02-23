package g52977.sortingRace.model;

import java.beans.PropertyChangeListener;

/**
 * The interface of the model.
 * 
 * @author 52977
 */
public interface ModelInterface {
	
	/**
	 * Makes the sorts of the different arrays.
	 * 
	 * @param arrayLength The length of the array to sort.
	 * @param sortName The type of sorting to be carried out.
	 */
	public void startSorting(String sortName, int arrayLength, int nbThreads);
	
	/**
	 * Add a listener.
	 * 
	 * @param listener A listener of the model.
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
}
