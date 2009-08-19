package org.enunes.gwt.sample.bit.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueRemovedEvent extends GwtEvent<IssueRemovedHandler> {

	private static Type<IssueRemovedHandler> TYPE;

	public static Type<IssueRemovedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<IssueRemovedHandler>());
	}

	public IssueRemovedEvent() {
	}

	@Override
	public final Type<IssueRemovedHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(IssueRemovedHandler handler) {
		handler.onIssueRemoved(this);
	}

}
