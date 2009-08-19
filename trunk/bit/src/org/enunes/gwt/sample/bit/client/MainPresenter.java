package org.enunes.gwt.sample.bit.client;

import org.enunes.gwt.mvp.client.presenter.Presenter;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public interface MainPresenter extends Presenter<MainPresenter.Display> {

	interface Display extends org.enunes.gwt.mvp.client.view.Display {

		void addMenu(org.enunes.gwt.mvp.client.view.Display display);

		void addContent(org.enunes.gwt.mvp.client.view.Display display);

		void removeContent();

	}

}
