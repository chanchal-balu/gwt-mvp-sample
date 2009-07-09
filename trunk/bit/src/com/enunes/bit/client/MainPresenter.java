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
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class MainPresenter {

	private final MenuPresenter menuPresenter;
	private final Provider<IssueDisplayPresenter> displayPresenter;
	private final Provider<IssueEditPresenter> editPresenter;
	private Panel panel;
	private Widget editWidget;
	private Widget displayWidget;

	@Inject
	public MainPresenter(HandlerManager eventBus, MenuPresenter menuPresenter,
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
		displayWidget = displayPresenter.get().go(issue);
		panel.add(displayWidget);
	}

	private void doIssueEditCanceled(Issue issue) {
		removeEditWidget();
		if (issue != null) {
			displayWidget = displayPresenter.get().go(issue);
			panel.add(displayWidget);
		}
	}

	private void doAddNewIssue() {
		removeDisplayWidget();
		removeEditWidget();
		editWidget = editPresenter.get().go();
		panel.add(editWidget);
	}

	private void doIssueRemoved() {
		removeDisplayWidget();
	}

	private void doIssueEdit(Issue issue) {
		removeDisplayWidget();
		editWidget = editPresenter.get().go(issue);
		panel.add(editWidget);
	}

	private void removeDisplayWidget() {
		if (displayWidget != null) {
			panel.remove(displayWidget);
			displayWidget = null;
		}
	}

	private void removeEditWidget() {
		if (editWidget != null) {
			panel.remove(editWidget);
			editWidget = null;
		}
	}

	public void go(Panel panel) {
		this.panel = panel;
		panel.add(menuPresenter.go());
	}

}
