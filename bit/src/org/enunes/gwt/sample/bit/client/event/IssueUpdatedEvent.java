package org.enunes.gwt.sample.bit.client.event;

import org.enunes.gwt.sample.bit.client.model.Issue;

import com.google.gwt.event.shared.GwtEvent;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueUpdatedEvent extends GwtEvent<IssueUpdatedHandler> {

	private static Type<IssueUpdatedHandler> TYPE;

	public static Type<IssueUpdatedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<IssueUpdatedHandler>());
	}

	private final Issue issue;

	public IssueUpdatedEvent(Issue issue) {
		this.issue = issue;
	}

	public Issue getIssue() {
		return issue;
	}

	@Override
	public final Type<IssueUpdatedHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(IssueUpdatedHandler handler) {
		handler.onIssueUpdated(this);
	}

}
