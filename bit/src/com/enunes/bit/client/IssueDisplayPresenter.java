package com.enunes.bit.client;

import com.enunes.bit.client.event.IssueEditEvent;
import com.enunes.bit.client.event.IssueRemovedEvent;
import com.enunes.bit.client.model.Issue;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueDisplayPresenter {

	public interface View {

		void addEditClickHandler(ClickHandler handler);

		void addRemoveClickHandler(ClickHandler handler);

		Widget getWidget();

		HasText getTaskName();

		HasText getTaskReporter();

		HasValue<Integer> getStars();

	}

	private final View view;
	private Issue issue;

	@Inject
	public IssueDisplayPresenter(final HandlerManager eventBus, final View view) {

		this.view = view;

		view.addEditClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new IssueEditEvent(issue));
			}
		});

		view.addRemoveClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// TODO: remove it from database
				eventBus.fireEvent(new IssueRemovedEvent());
			}
		});

	}

	public Widget go(Issue issue) {
		this.issue = issue;
		view.getTaskName().setText(issue.getTaskName());
		view.getTaskReporter().setText(issue.getTaskReporter());
		view.getStars().setValue(issue.getStars());
		return view.getWidget();
	}

}
