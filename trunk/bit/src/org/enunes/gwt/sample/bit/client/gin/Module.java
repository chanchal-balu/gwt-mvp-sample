package org.enunes.gwt.sample.bit.client.gin;

import org.enunes.gwt.sample.bit.client.IssueDisplayPresenter;
import org.enunes.gwt.sample.bit.client.IssueDisplayPresenterImpl;
import org.enunes.gwt.sample.bit.client.IssueDisplayWidget;
import org.enunes.gwt.sample.bit.client.IssueEditPresenter;
import org.enunes.gwt.sample.bit.client.IssueEditPresenterImpl;
import org.enunes.gwt.sample.bit.client.IssueEditWidget;
import org.enunes.gwt.sample.bit.client.MainPresenter;
import org.enunes.gwt.sample.bit.client.MainPresenterImpl;
import org.enunes.gwt.sample.bit.client.MainWidget;
import org.enunes.gwt.sample.bit.client.MenuPresenter;
import org.enunes.gwt.sample.bit.client.MenuPresenterImpl;
import org.enunes.gwt.sample.bit.client.MenuWidget;

import com.google.gwt.inject.client.AbstractGinModule;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class Module extends AbstractGinModule {

	@Override
	protected void configure() {

		install(new org.enunes.gwt.mvp.client.gin.Module());

		bind(IssueDisplayPresenter.class).to(IssueDisplayPresenterImpl.class);
		bind(IssueDisplayPresenter.Display.class).to(IssueDisplayWidget.class);

		bind(IssueEditPresenter.class).to(IssueEditPresenterImpl.class);
		bind(IssueEditPresenter.Display.class).to(IssueEditWidget.class);

		bind(MenuPresenter.class).to(MenuPresenterImpl.class);
		bind(MenuPresenter.Display.class).to(MenuWidget.class);

		bind(MainPresenter.class).to(MainPresenterImpl.class);
		bind(MainPresenter.Display.class).to(MainWidget.class);

	}

}
