/**
 * 
 */
package com.enunes.bit.client;

import com.enunes.bit.client.event.AddNewIssueEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class MenuPresenter {

	public interface View {

		void addClickHandler(ClickHandler handler);

		Widget getWidget();

	}

	private final View view;

	@Inject
	public MenuPresenter(final HandlerManager eventBus, View view) {

		this.view = view;

		view.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddNewIssueEvent());
			}
		});

	}

	public Widget go() {
		return view.getWidget();
	}

}
