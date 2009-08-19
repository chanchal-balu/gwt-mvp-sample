package org.enunes.gwt.sample.bit.client;

import org.enunes.gwt.mvp.client.EventBus;
import org.enunes.gwt.mvp.client.presenter.BasePresenter;
import org.enunes.gwt.sample.bit.client.IssueDisplayPresenter.Display;
import org.enunes.gwt.sample.bit.client.event.IssueEditEvent;
import org.enunes.gwt.sample.bit.client.event.IssueRemovedEvent;
import org.enunes.gwt.sample.bit.client.model.Issue;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueDisplayPresenterImpl extends BasePresenter<Display> implements
		IssueDisplayPresenter {

	private Issue issue;

	@Inject
	public IssueDisplayPresenterImpl(EventBus eventBus, Display display) {
		super(eventBus, display);
	}

	@Override
	public void bind() {

		super.bind();

		registerHandler(display.getEditClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						eventBus.fireEvent(new IssueEditEvent(issue));
					}
				}));

		registerHandler(display.getRemoveClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						eventBus.fireEvent(new IssueRemovedEvent());
					}
				}));

	}

	public void showIssue(Issue issue) {
		this.issue = issue;
		display.getTaskName().setText(issue.getTaskName());
		display.getTaskReporter().setText(issue.getTaskReporter());
		display.getStars().setValue(issue.getStars());
	}

}
