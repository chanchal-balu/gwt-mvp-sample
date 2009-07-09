/**
 * 
 */
package com.enunes.bit.client;

import com.enunes.bit.client.extra.StarsWidget;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueDisplayWidget extends Composite implements
		IssueDisplayPresenter.View {

	private final Panel panel;
	private final Label taskName;
	private final Label taskReporter;
	private final StarsWidget stars;
	private final Button editButton;
	private final Button removeButton;

	public IssueDisplayWidget() {

		panel = new FlowPanel();
		panel.setStyleName("issue-display");
		initWidget(panel);

		final Panel buttonsPanel = new FlowPanel();
		buttonsPanel.setStyleName("issue-display-buttons");
		panel.add(buttonsPanel);

		editButton = new Button("E");
		editButton.setTitle("edit");
		buttonsPanel.add(editButton);

		removeButton = new Button("R");
		removeButton.setTitle("remove");
		buttonsPanel.add(removeButton);

		final Panel infoPanel = new FlowPanel();
		infoPanel.setStyleName("issue-display-info");
		panel.add(infoPanel);

		taskName = new Label();
		infoPanel.add(createLine("Task", taskName));

		taskReporter = new Label();
		infoPanel.add(createLine("Task reporter", taskReporter));

		stars = new StarsWidget(5);
		stars.setReadOnly(true);
		infoPanel.add(createLine("Rate", stars));

	}

	private Widget createLine(String header, Widget value) {

		final Panel result = new FlowPanel();
		result.setStyleName("issue-display-info-line");

		final Label label = new Label(header);
		label.setStyleName("issue-display-info-header");
		result.add(label);

		final SimplePanel valuePanel = new SimplePanel();
		valuePanel.setStyleName("issue-display-info-value");
		valuePanel.setWidget(value);
		result.add(valuePanel);

		return result;

	}

	public void addEditClickHandler(ClickHandler handler) {
		editButton.addClickHandler(handler);
	}

	public void addRemoveClickHandler(ClickHandler handler) {
		removeButton.addClickHandler(handler);
	}

	public Widget getWidget() {
		return this;
	}

	public HasValue<Integer> getStars() {
		return stars;
	}

	public HasText getTaskName() {
		return taskName;
	}

	public HasText getTaskReporter() {
		return taskReporter;
	}

}
