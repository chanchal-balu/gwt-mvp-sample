package org.enunes.gwt.sample.bit.client.model;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class Issue {

	private final int id;
	private String taskName;
	private String taskReporter;
	private int stars;

	public Issue(int id) {
		this.id = id;
	}

	public Issue(int id, String taskName, String taskReporter, int stars) {
		this.id = id;
		this.taskName = taskName;
		this.taskReporter = taskReporter;
		this.stars = stars;
	}

	public int getId() {
		return id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskReporter() {
		return taskReporter;
	}

	public void setTaskReporter(String taskReporter) {
		this.taskReporter = taskReporter;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

}
