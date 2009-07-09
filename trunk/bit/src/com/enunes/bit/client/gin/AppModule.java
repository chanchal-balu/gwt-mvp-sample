package com.enunes.bit.client.gin;

import com.enunes.bit.client.IssueDisplayPresenter;
import com.enunes.bit.client.IssueDisplayPresenterImpl;
import com.enunes.bit.client.IssueDisplayWidget;
import com.enunes.bit.client.IssueEditPresenter;
import com.enunes.bit.client.IssueEditPresenterImpl;
import com.enunes.bit.client.IssueEditWidget;
import com.enunes.bit.client.MainPresenter;
import com.enunes.bit.client.MainPresenterImpl;
import com.enunes.bit.client.MenuPresenter;
import com.enunes.bit.client.MenuPresenterImpl;
import com.enunes.bit.client.MenuWidget;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class AppModule extends AbstractGinModule {

	@Override
	protected void configure() {

		bind(HandlerManager.class).toProvider(HandlerManagerProvider.class).in(
				Singleton.class);

		bind(IssueDisplayPresenter.class).to(IssueDisplayPresenterImpl.class);
		bind(IssueDisplayPresenter.View.class).to(IssueDisplayWidget.class);

		bind(IssueEditPresenter.class).to(IssueEditPresenterImpl.class);
		bind(IssueEditPresenter.View.class).to(IssueEditWidget.class);

		bind(MenuPresenter.class).to(MenuPresenterImpl.class);
		bind(MenuPresenter.View.class).to(MenuWidget.class);

		bind(MainPresenter.class).to(MainPresenterImpl.class);

	}

}
