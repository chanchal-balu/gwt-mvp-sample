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
public interface IssueDisplayPresenter {

	interface View {

		void addEditClickHandler(ClickHandler handler);

		void addRemoveClickHandler(ClickHandler handler);

		Widget getWidget();

		HasText getTaskName();

		HasText getTaskReporter();

		HasValue<Integer> getStars();

	}

	Widget go(Issue issue);

}
