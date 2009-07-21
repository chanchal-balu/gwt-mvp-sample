/**
 * 
 */
package com.enunes.bit.client;

import com.enunes.bit.client.event.AddNewIssueEvent;
import com.enunes.bit.client.event.AddNewIssueHandler;
import com.enunes.bit.client.event.IssueEditCanceledEvent;
import com.enunes.bit.client.event.IssueEditCanceledHandler;
import com.enunes.bit.client.event.IssueEditEvent;
import com.enunes.bit.client.event.IssueEditHandler;
import com.enunes.bit.client.event.IssueRemovedEvent;
import com.enunes.bit.client.event.IssueRemovedHandler;
import com.enunes.bit.client.event.IssueUpdatedEvent;
import com.enunes.bit.client.event.IssueUpdatedHandler;
import com.enunes.bit.client.model.Issue;
import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class MainPresenterImpl implements MainPresenter {

	private final View view;
	private final MenuPresenter menuPresenter;
	private final Provider<IssueDisplayPresenter> displayPresenter;
	private final Provider<IssueEditPresenter> editPresenter;

	@Inject
	public MainPresenterImpl(HandlerManager eventBus, View view,
			MenuPresenter menuPresenter,
			Provider<IssueDisplayPresenter> displayPresenter,
			Provider<IssueEditPresenter> editPresenter) {

		this.view = view;
		this.menuPresenter = menuPresenter;
		this.displayPresenter = displayPresenter;
		this.editPresenter = editPresenter;

		bindEvents(eventBus);

	}

	private void bindEvents(HandlerManager eventBus) {

		eventBus.addHandler(AddNewIssueEvent.getType(),
				new AddNewIssueHandler() {
					public void onAddNewIssue(AddNewIssueEvent event) {
						doAddNewIssue();
					}
				});

		eventBus.addHandler(IssueEditEvent.getType(), new IssueEditHandler() {
			public void onIssueEdit(IssueEditEvent event) {
				doIssueEdit(event.getIssue());
			}
		});

		eventBus.addHandler(IssueRemovedEvent.getType(),
				new IssueRemovedHandler() {
					public void onIssueRemoved(IssueRemovedEvent event) {
						doIssueRemoved();
					}
				});

		eventBus.addHandler(IssueEditCanceledEvent.getType(),
				new IssueEditCanceledHandler() {
					public void onIssueEditCanceled(IssueEditCanceledEvent event) {
						doIssueEditCanceled(event.getIssue());
					}
				});

		eventBus.addHandler(IssueUpdatedEvent.getType(),
				new IssueUpdatedHandler() {
					public void onIssueUpdated(IssueUpdatedEvent event) {
						doIssueUpdated(event.getIssue());
					}
				});

	}

	private void doIssueUpdated(Issue issue) {
		BaseView displayView = displayPresenter.get().showIssue(issue);
		view.addContent(displayView);
	}

	private void doIssueEditCanceled(Issue issue) {
		if (issue != null) {
			BaseView displayView = displayPresenter.get().showIssue(issue);
			view.addContent(displayView);
		} else {
			view.removeContent();
		}
	}

	private void doAddNewIssue() {
		BaseView editView = editPresenter.get().createIssue();
		view.addContent(editView);
	}

	private void doIssueRemoved() {
		view.removeContent();
	}

	private void doIssueEdit(Issue issue) {
		BaseView editView = editPresenter.get().editIssue(issue);
		view.addContent(editView);
	}

	public View go() {
		view.addMenu(menuPresenter.showMenu());
		return view;
	}

	public BaseView getView() {
		return view;
	}

}
