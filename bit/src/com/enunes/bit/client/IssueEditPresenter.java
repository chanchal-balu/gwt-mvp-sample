/**
 * 
 */
package com.enunes.bit.client;

import com.enunes.bit.client.model.Issue;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public interface IssueEditPresenter {

	interface View {

		Widget getWidget();

		void addSaveClickHandler(ClickHandler handler);

		void addCancelClickHandler(ClickHandler handler);

		HasText getTaskName();

		HasText getTaskReporter();

		HasValue<Integer> getStars();

	}

	/**
	 * Creates a new Issue
	 */
	Widget createIssue();

	/**
	 * Edits an Issue
	 */
	Widget editIssue(Issue issue);

}
