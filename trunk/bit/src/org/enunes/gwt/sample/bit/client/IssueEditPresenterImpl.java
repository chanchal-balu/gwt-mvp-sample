package org.enunes.gwt.sample.bit.client;

import org.enunes.gwt.mvp.client.EventBus;
import org.enunes.gwt.mvp.client.presenter.BasePresenter;
import org.enunes.gwt.sample.bit.client.IssueEditPresenter.Display;
import org.enunes.gwt.sample.bit.client.event.IssueEditCanceledEvent;
import org.enunes.gwt.sample.bit.client.event.IssueUpdatedEvent;
import org.enunes.gwt.sample.bit.client.model.Issue;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueEditPresenterImpl extends BasePresenter<Display> implements
		IssueEditPresenter {

	private Issue issue;

	@Inject
	public IssueEditPresenterImpl(EventBus eventBus, Display display) {
		super(eventBus, display);
	}

	@Override
	public void bind() {

		super.bind();

		registerHandler(display.getCancelClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						eventBus.fireEvent(new IssueEditCanceledEvent(issue));
					}
				}));

		registerHandler(display.getSaveClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						if (issue == null) {
							issue = new Issue(1);
						}
						issue.setTaskName(display.getTaskName().getText());
						issue.setTaskReporter(display.getTaskReporter()
								.getText());
						try {
							issue.setStars(display.getStars().getValue());
						} catch (NumberFormatException e) {
							issue.setStars(0);
						}
						eventBus.fireEvent(new IssueUpdatedEvent(issue));
					}
				}));

	}

	public void createIssue() {
		issue = null;
	}

	public void editIssue(Issue issue) {
		this.issue = issue;
		display.getTaskName().setText(issue.getTaskName());
		display.getTaskReporter().setText(issue.getTaskReporter());
		display.getStars().setValue(issue.getStars());
	}

}
