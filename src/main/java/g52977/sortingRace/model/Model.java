package g52977.sortingRace.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The model of the application.
 *
 * @author 52977
 */
public class Model implements ModelInterface, PropertyChangeListener {

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Makes the sorts of the different arrays.
	 *
	 * @param sortType The type of sorting to be carried out.
	 * @param arrayLength The length of the last array to sort.
	 * @param nbThreads The number of threads to create.
	 */
	@Override
	public void startSorting(String sortType, int arrayLength, int nbThreads) {
		JobManager manager = new JobManager(arrayLength);
		for (int i = 0; i < nbThreads; i++) {
			SortThread thread = new SortThread(manager, sortType);
			thread.addPropertyChangeListener(this);
			thread.start();
		}
	}

	/**
	 * Add a listener.
	 *
	 * @param listener A listener of the model.
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Warns the view when a sorting is done. Gives him a sort object that
	 * contains all the informations about the sorting.
	 * 
	 * @param pce The object received.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent pce) {
		Sort sort = (Sort) pce.getNewValue();
		pcs.firePropertyChange("ARRAY_SORTED", null, sort);
	}

}
