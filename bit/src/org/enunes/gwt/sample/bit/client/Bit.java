package org.enunes.gwt.sample.bit.client;

import org.enunes.gwt.mvp.client.EventBus;
import org.enunes.gwt.sample.bit.client.event.AddNewIssueEvent;
import org.enunes.gwt.sample.bit.client.event.AddNewIssueHandler;
import org.enunes.gwt.sample.bit.client.event.IssueEditCanceledEvent;
import org.enunes.gwt.sample.bit.client.event.IssueEditCanceledHandler;
import org.enunes.gwt.sample.bit.client.event.IssueEditEvent;
import org.enunes.gwt.sample.bit.client.event.IssueEditHandler;
import org.enunes.gwt.sample.bit.client.event.IssueRemovedEvent;
import org.enunes.gwt.sample.bit.client.event.IssueRemovedHandler;
import org.enunes.gwt.sample.bit.client.event.IssueUpdatedEvent;
import org.enunes.gwt.sample.bit.client.event.IssueUpdatedHandler;
import org.enunes.gwt.sample.bit.client.gin.Injector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class Bit implements EntryPoint {

	public void onModuleLoad() {

		final Injector ginjector = GWT.create(Injector.class);

		final MainPresenter mainPresenter = ginjector.getMainPresenter();

		mainPresenter.bind();

		RootPanel.get().add(mainPresenter.getDisplay().asWidget());

		logEvent(ginjector.getEventBus());

	}

	private void logEvent(EventBus eventBus) {
		eventBus.addHandler(IssueEditEvent.getType(), new IssueEditHandler() {
			public void onIssueEdit(IssueEditEvent event) {
				GWT.log(event.toDebugString(), null);
			}
		});
		eventBus.addHandler(IssueRemovedEvent.getType(),
				new IssueRemovedHandler() {
					public void onIssueRemoved(IssueRemovedEvent event) {
						GWT.log(event.toDebugString(), null);
					}
				});
		eventBus.addHandler(AddNewIssueEvent.getType(),
				new AddNewIssueHandler() {
					public void onAddNewIssue(AddNewIssueEvent event) {
						GWT.log(event.toDebugString(), null);
					}
				});
		eventBus.addHandler(IssueEditCanceledEvent.getType(),
				new IssueEditCanceledHandler() {
					public void onIssueEditCanceled(IssueEditCanceledEvent event) {
						GWT.log(event.toDebugString(), null);
					}
				});
		eventBus.addHandler(IssueUpdatedEvent.getType(),
				new IssueUpdatedHandler() {
					public void onIssueUpdated(IssueUpdatedEvent event) {
						GWT.log(event.toDebugString(), null);
					}
				});
	}

}
