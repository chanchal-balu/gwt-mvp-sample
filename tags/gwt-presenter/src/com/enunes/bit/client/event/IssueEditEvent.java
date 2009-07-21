/**
 * 
 */
package com.enunes.bit.client.event;

import com.enunes.bit.client.model.Issue;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueEditEvent extends GwtEvent<IssueEditHandler> {

	private static Type<IssueEditHandler> TYPE;

	public static Type<IssueEditHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<IssueEditHandler>());
	}

	private final Issue issue;

	public IssueEditEvent(Issue issue) {
		this.issue = issue;
	}

	public Issue getIssue() {
		return issue;
	}

	@Override
	public final Type<IssueEditHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(IssueEditHandler handler) {
		handler.onIssueEdit(this);
	}

}
