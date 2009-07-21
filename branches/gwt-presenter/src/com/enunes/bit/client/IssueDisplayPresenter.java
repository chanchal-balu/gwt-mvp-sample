/**
 * 
 */
package com.enunes.bit.client;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.enunes.bit.client.event.IssueEditEvent;
import com.enunes.bit.client.event.IssueRemovedEvent;
import com.enunes.bit.client.model.Issue;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueDisplayPresenter extends
		WidgetPresenter<IssueDisplayPresenter.Display> {

	public interface Display extends WidgetDisplay {

		HasClickHandlers getEditClickHandlers();

		HasClickHandlers getRemoveClickHandlers();

		HasText getTaskName();

		HasText getTaskReporter();

		HasValue<Integer> getStars();

	}

	private Issue issue;

	@Inject
	public IssueDisplayPresenter(Display display, EventBus eventBus) {
		super(display, eventBus);
	}

	@Override
	public Place getPlace() {
		return null;
	}

	@Override
	protected void onBind() {
		display.getEditClickHandlers().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new IssueEditEvent(issue));
			}
		});

		display.getRemoveClickHandlers().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// TODO: remove it from database
				eventBus.fireEvent(new IssueRemovedEvent());
			}
		});
	}

	public void showIssue(Issue issue) {
		this.issue = issue;
		display.getTaskName().setText(issue.getTaskName());
		display.getTaskReporter().setText(issue.getTaskReporter());
		display.getStars().setValue(issue.getStars());
	}

	@Override
	protected void onPlaceRequest(PlaceRequest request) {
	}

	@Override
	protected void onUnbind() {
	}

	public void refreshDisplay() {
	}

	public void revealDisplay() {
	}

}
