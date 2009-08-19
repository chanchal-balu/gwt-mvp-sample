package org.enunes.gwt.sample.bit.client;

import org.enunes.gwt.sample.bit.client.MainPresenter.Display;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class MainWidget extends Composite implements Display {

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

	public void addContent(org.enunes.gwt.mvp.client.view.Display display) {
		removeContent();
		content = display.asWidget();
		panel.add(display.asWidget(), DockPanel.CENTER);
	}

	public void addMenu(org.enunes.gwt.mvp.client.view.Display display) {
		panel.add(display.asWidget(), DockPanel.NORTH);
	}

	public Widget asWidget() {
		return this;
	}

}
