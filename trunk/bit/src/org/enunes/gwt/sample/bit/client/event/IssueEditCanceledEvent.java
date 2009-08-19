package org.enunes.gwt.sample.bit.client.event;

import org.enunes.gwt.sample.bit.client.model.Issue;

import com.google.gwt.event.shared.GwtEvent;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueEditCanceledEvent extends GwtEvent<IssueEditCanceledHandler> {

	private static Type<IssueEditCanceledHandler> TYPE;

	public static Type<IssueEditCanceledHandler> getType() {
		return TYPE != null ? TYPE
				: (TYPE = new Type<IssueEditCanceledHandler>());
	}

	private final Issue issue;

	public IssueEditCanceledEvent(Issue issue) {
		this.issue = issue;
	}

	public Issue getIssue() {
		return issue;
	}

	@Override
	public final Type<IssueEditCanceledHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(IssueEditCanceledHandler handler) {
		handler.onIssueEditCanceled(this);
	}

}
