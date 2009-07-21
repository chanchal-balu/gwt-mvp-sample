package com.enunes.bit.client;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainWidget extends Composite implements MainPresenter.Display {

	private final DockPanel panel;
	private Widget content;

	public MainWidget() {

		panel = new DockPanel();
		panel.setStyleName("main");
		initWidget(panel);

	}

	public void removeContent() {
		if (content != null) {
			panel.remove(content);
		}
	}

	public void addContent(WidgetDisplay display) {
		removeContent();
		content = display.asWidget();
		panel.add(content, DockPanel.CENTER);
	}

	public void addMenu(WidgetDisplay display) {
		panel.add(display.asWidget(), DockPanel.NORTH);
	}

	public Widget asWidget() {
		return this;
	}

	public void startProcessing() {
	}

	public void stopProcessing() {
	}

}
