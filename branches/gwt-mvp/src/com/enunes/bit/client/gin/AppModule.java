package com.enunes.bit.client.gin;

import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;

import com.enunes.bit.client.IssueDisplayPresenter;
import com.enunes.bit.client.IssueDisplayWidget;
import com.enunes.bit.client.IssueEditPresenter;
import com.enunes.bit.client.IssueEditWidget;
import com.enunes.bit.client.MainPresenter;
import com.enunes.bit.client.MainWidget;
import com.enunes.bit.client.MenuPresenter;
import com.enunes.bit.client.MenuWidget;
import com.google.gwt.inject.client.AbstractGinModule;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class AppModule extends AbstractGinModule {

	@Override
	protected void configure() {

		bind(EventBus.class).to(DefaultEventBus.class);

		bind(MainPresenter.Display.class).to(MainWidget.class);

		bind(MenuPresenter.Display.class).to(MenuWidget.class);

		bind(IssueEditPresenter.Display.class).to(IssueEditWidget.class);

		bind(IssueDisplayPresenter.Display.class).to(IssueDisplayWidget.class);

	}

}
