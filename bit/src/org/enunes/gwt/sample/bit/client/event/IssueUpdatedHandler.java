package org.enunes.gwt.sample.bit.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public interface IssueUpdatedHandler extends EventHandler {

	void onIssueUpdated(IssueUpdatedEvent event);

}
