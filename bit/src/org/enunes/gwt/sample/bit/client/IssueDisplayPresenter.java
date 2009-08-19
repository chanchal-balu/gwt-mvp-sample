package org.enunes.gwt.sample.bit.client;

import org.enunes.gwt.mvp.client.presenter.Presenter;
import org.enunes.gwt.sample.bit.client.model.Issue;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public interface IssueDisplayPresenter extends
		Presenter<IssueDisplayPresenter.Display> {

	interface Display extends org.enunes.gwt.mvp.client.view.Display {

		HasClickHandlers getEditClickHandlers();

		HasClickHandlers getRemoveClickHandlers();

		HasText getTaskName();

		HasText getTaskReporter();

		HasValue<Integer> getStars();

	}

	void showIssue(Issue issue);

}
