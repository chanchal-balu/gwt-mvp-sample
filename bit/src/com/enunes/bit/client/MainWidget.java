package com.enunes.bit.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainWidget extends Composite implements MainPresenter.View {

	private final DockPanel panel;
	private Widget content;

	public MainWidget() {

		panel = new DockPanel();
		initWidget(panel);

	}

	public Widget getWidget() {
		return this;
	}

	public void addMenu(BaseView view) {
		panel.add(view.getWidget(), DockPanel.NORTH);
	}

	public void addContent(BaseView view) {
		removeContent();
		content = view.getWidget();
		panel.add(view.getWidget(), DockPanel.CENTER);
	}

	public void removeContent() {
		if (content != null) {
			panel.remove(content);
		}
	}

}
