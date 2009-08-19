package org.enunes.gwt.sample.bit.client;

import org.enunes.gwt.mvp.client.EventBus;
import org.enunes.gwt.mvp.client.presenter.BasePresenter;
import org.enunes.gwt.sample.bit.client.MenuPresenter.Display;
import org.enunes.gwt.sample.bit.client.event.AddNewIssueEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class MenuPresenterImpl extends BasePresenter<Display> implements
		MenuPresenter {

	@Inject
	public MenuPresenterImpl(EventBus eventBus, Display display) {
		super(eventBus, display);
	}

	@Override
	public void bind() {

		super.bind();

		registerHandler(display.getAddIssueClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						eventBus.fireEvent(new AddNewIssueEvent());
					}
				}));

	}

}
