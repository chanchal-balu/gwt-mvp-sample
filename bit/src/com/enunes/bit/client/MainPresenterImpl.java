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
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class MainPresenterImpl implements MainPresenter {

	private final MenuPresenter menuPresenter;
	private final Provider<IssueDisplayPresenter> displayPresenter;
	private final Provider<IssueEditPresenter> editPresenter;
	private HasWidgets container;
	private Widget editWidget;
	private Widget displayWidget;

	@Inject
	public MainPresenterImpl(HandlerManager eventBus,
			MenuPresenter menuPresenter,
			Provider<IssueDisplayPresenter> displayPresenter,
			Provider<IssueEditPresenter> editPresenter) {

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
		removeEditWidget();
		displayWidget = displayPresenter.get().showIssue(issue);
		container.add(displayWidget);
	}

	private void doIssueEditCanceled(Issue issue) {
		removeEditWidget();
		if (issue != null) {
			displayWidget = displayPresenter.get().showIssue(issue);
			container.add(displayWidget);
		}
	}

	private void doAddNewIssue() {
		removeDisplayWidget();
		removeEditWidget();
		editWidget = editPresenter.get().createIssue();
		container.add(editWidget);
	}

	private void doIssueRemoved() {
		removeDisplayWidget();
	}

	private void doIssueEdit(Issue issue) {
		removeDisplayWidget();
		editWidget = editPresenter.get().editIssue(issue);
		container.add(editWidget);
	}

	private void removeDisplayWidget() {
		if (displayWidget != null) {
			container.remove(displayWidget);
			displayWidget = null;
		}
	}

	private void removeEditWidget() {
		if (editWidget != null) {
			container.remove(editWidget);
			editWidget = null;
		}
	}

	public void go(HasWidgets container) {
		this.container = container;
		container.add(menuPresenter.showMenu());
	}

}
