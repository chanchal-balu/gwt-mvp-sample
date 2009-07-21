package com.enunes.bit.client;

import com.enunes.bit.client.event.IssueEditEvent;
import com.enunes.bit.client.event.IssueRemovedEvent;
import com.enunes.bit.client.model.Issue;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueDisplayPresenterImpl implements IssueDisplayPresenter {

	private final View view;
	private Issue issue;

	@Inject
	public IssueDisplayPresenterImpl(final HandlerManager eventBus,
			final View view) {

		this.view = view;

		view.getEditClickHandlers().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new IssueEditEvent(issue));
			}
		});

		view.getRemoveClickHandlers().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// TODO: remove it from database
				eventBus.fireEvent(new IssueRemovedEvent());
			}
		});

	}

	public View showIssue(Issue issue) {
		this.issue = issue;
		view.getTaskName().setText(issue.getTaskName());
		view.getTaskReporter().setText(issue.getTaskReporter());
		view.getStars().setValue(issue.getStars());
		return view;
	}

	public BaseView getView() {
		return view;
	}

}
