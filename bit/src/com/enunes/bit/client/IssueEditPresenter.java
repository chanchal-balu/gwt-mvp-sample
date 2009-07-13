/**
 * 
 */
package com.enunes.bit.client;

import com.enunes.bit.client.model.Issue;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public interface IssueEditPresenter extends BasePresenter {

	interface View extends BaseView {

		HasClickHandlers getSaveClickHandlers();

		HasClickHandlers getCancelClickHandlers();

		HasText getTaskName();

		HasText getTaskReporter();

		HasValue<Integer> getStars();

	}

	/**
	 * Creates a new Issue
	 */
	View createIssue();

	/**
	 * Edits an Issue
	 */
	View editIssue(Issue issue);

}
