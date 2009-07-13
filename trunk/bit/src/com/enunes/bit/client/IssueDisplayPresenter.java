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
public interface IssueDisplayPresenter extends BasePresenter {

	interface View extends BaseView {

		HasClickHandlers getEditClickHandlers();

		HasClickHandlers getRemoveClickHandlers();

		HasText getTaskName();

		HasText getTaskReporter();

		HasValue<Integer> getStars();

	}

	View showIssue(Issue issue);

}
