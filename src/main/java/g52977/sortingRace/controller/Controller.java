package g52977.sortingRace.controller;

import java.beans.PropertyChangeListener;
import g52977.sortingRace.model.ModelInterface;

/**
 * Application's controller.
 * 
 * @author 52977
 */
public class Controller {

	private ModelInterface model;

	/**
	 * Constructor of Controller.
	 * 
	 * @param model The model of the application.
	 */
	public Controller(ModelInterface model) {
		this.model = model;
	}
	
	/**
	 * Starts sorting according to the data sent by the view.
	 * 
	 * @param length The length of the biggest array.
	 * @param sortType The sorting type.
	 * @param nbThreads The number of threads used for sorting.
	 */
	public void startSorting(int length, String sortType, int nbThreads) {
		model.startSorting(sortType, length, nbThreads);
	}
	
	/**
	 * Add a listener to the model.
	 *
	 * @param listener The new listener.
	 */
	public void addModelListener(PropertyChangeListener listener) {
		model.addPropertyChangeListener(listener);
	}

}
