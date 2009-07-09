/**
 * 
 */
package com.enunes.bit.client;

import com.enunes.bit.client.event.IssueEditCanceledEvent;
import com.enunes.bit.client.event.IssueUpdatedEvent;
import com.enunes.bit.client.model.Issue;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueEditPresenterImpl implements IssueEditPresenter {

	private final View view;
	private Issue issue;

	@Inject
	public IssueEditPresenterImpl(final HandlerManager eventBus, final View view) {

		this.view = view;

		view.addCancelClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new IssueEditCanceledEvent(issue));
			}
		});

		view.addSaveClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// TODO: replace current code by a service call to save it in
				// the database
				if (issue == null) {
					issue = new Issue(1);
				}
				issue.setTaskName(view.getTaskName().getText());
				issue.setTaskReporter(view.getTaskReporter().getText());
				try {
					issue.setStars(view.getStars().getValue());
				} catch (NumberFormatException e) {
					issue.setStars(0);
				}
				eventBus.fireEvent(new IssueUpdatedEvent(issue));
			}
		});

	}

	public Widget go() {
		issue = null;
		return view.getWidget();
	}

	public Widget go(Issue issue) {
		this.issue = issue;
		view.getTaskName().setText(issue.getTaskName());
		view.getTaskReporter().setText(issue.getTaskReporter());
		view.getStars().setValue(issue.getStars());
		return view.getWidget();
	}
}
