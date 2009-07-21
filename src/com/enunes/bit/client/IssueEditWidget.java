/**
 * 
 */
package com.enunes.bit.client;

import com.enunes.bit.client.extra.StarsWidget;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueEditWidget extends Composite implements
		IssueEditPresenter.Display {

	private final Panel panel;
	private final TextBox taskName;
	private final TextBox taskReporter;
	private final StarsWidget stars;
	private final Button saveButton;
	private final Button cancelButton;

	public IssueEditWidget() {

		panel = new FlowPanel();
		panel.setStyleName("issue-edit");
		initWidget(panel);

		final FlowPanel infoPanel = new FlowPanel();
		infoPanel.setStyleName("issue-edit-info");
		panel.add(infoPanel);

		taskName = new TextBox();
		infoPanel.add(createLine("Task", taskName));

		taskReporter = new TextBox();
		infoPanel.add(createLine("Task reporter", taskReporter));

		stars = new StarsWidget(5);
		infoPanel.add(createLine("Rate", stars));

		final Panel buttonsPanel = new FlowPanel();
		buttonsPanel.setStyleName("issue-edit-buttons");
		panel.add(buttonsPanel);

		saveButton = new Button("save");
		buttonsPanel.add(saveButton);

		cancelButton = new Button("cancel");
		buttonsPanel.add(cancelButton);

	}

	private Widget createLine(String header, Widget value) {

		final Panel result = new FlowPanel();
		result.setStyleName("issue-edit-info-line");

		final Label label = new Label(header);
		label.setStyleName("issue-edit-info-header");
		result.add(label);

		final SimplePanel valuePanel = new SimplePanel();
		valuePanel.setStyleName("issue-edit-info-value");
		valuePanel.setWidget(value);
		result.add(valuePanel);

		return result;

	}

	@Override
	protected void onAttach() {
		super.onAttach();
		setFocus();
	}

	private void setFocus() {
		taskName.setFocus(true);
		taskName.setSelectionRange(0, taskName.getText().length());
	}

	public void addSaveClickHandler(ClickHandler handler) {
		saveButton.addClickHandler(handler);
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

	public HasClickHandlers getCancelClickHandlers() {
		return cancelButton;
	}

	public HasClickHandlers getSaveClickHandlers() {
		return saveButton;
	}

	public Widget asWidget() {
		return this;
	}

	public void startProcessing() {
	}

	public void stopProcessing() {
	}

}
