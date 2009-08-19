package org.enunes.gwt.sample.bit.client;

import org.enunes.gwt.mvp.client.presenter.Presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public interface MenuPresenter extends Presenter<MenuPresenter.Display> {

	interface Display extends org.enunes.gwt.mvp.client.view.Display {

		HasClickHandlers getAddIssueClickHandlers();

	}

}
