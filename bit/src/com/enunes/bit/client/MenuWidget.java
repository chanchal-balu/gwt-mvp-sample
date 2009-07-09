/**
 * 
 */
package com.enunes.bit.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class MenuWidget extends Composite implements MenuPresenter.View {

	private final Button addIssueButton;

	public MenuWidget() {

		final FlowPanel panel = new FlowPanel();
		panel.setStyleName("menu");
		initWidget(panel);

		addIssueButton = new Button("add issue");
		panel.add(addIssueButton);

	}

	@Override
	public Widget getWidget() {
		return this;
	}

	public void addClickHandler(ClickHandler handler) {
		addIssueButton.addClickHandler(handler);
	}

}
