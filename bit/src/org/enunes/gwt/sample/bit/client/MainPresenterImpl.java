package org.enunes.gwt.sample.bit.client;

import org.enunes.gwt.mvp.client.EventBus;
import org.enunes.gwt.mvp.client.presenter.BasePresenter;
import org.enunes.gwt.mvp.client.presenter.Presenter;
import org.enunes.gwt.sample.bit.client.MainPresenter.Display;
import org.enunes.gwt.sample.bit.client.event.AddNewIssueEvent;
import org.enunes.gwt.sample.bit.client.event.AddNewIssueHandler;
import org.enunes.gwt.sample.bit.client.event.IssueEditCanceledEvent;
import org.enunes.gwt.sample.bit.client.event.IssueEditCanceledHandler;
import org.enunes.gwt.sample.bit.client.event.IssueEditEvent;
import org.enunes.gwt.sample.bit.client.event.IssueEditHandler;
import org.enunes.gwt.sample.bit.client.event.IssueRemovedEvent;
import org.enunes.gwt.sample.bit.client.event.IssueRemovedHandler;
import org.enunes.gwt.sample.bit.client.event.IssueUpdatedEvent;
import org.enunes.gwt.sample.bit.client.event.IssueUpdatedHandler;
import org.enunes.gwt.sample.bit.client.model.Issue;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class MainPresenterImpl extends BasePresenter<Display> implements
		MainPresenter {

	private final Provider<IssueDisplayPresenter> displayProvider;
	private final Provider<IssueEditPresenter> editProvider;
	private Presenter<? extends org.enunes.gwt.mvp.client.view.Display> presenter;

	@Inject
	public MainPresenterImpl(EventBus eventBus, Display display,
			MenuPresenter menuPresenter,
			Provider<IssueDisplayPresenter> displayPresenter,
			Provider<IssueEditPresenter> editPresenter) {

		super(eventBus, display);

		this.displayProvider = displayPresenter;
		this.editProvider = editPresenter;

		menuPresenter.bind();
		display.addMenu(menuPresenter.getDisplay());

	}

	private void switchPresenter(
			Presenter<? extends org.enunes.gwt.mvp.client.view.Display> presenter) {

		if (this.presenter != null) {
			this.presenter.unbind();
			display.removeContent();
		}

		this.presenter = presenter;

		if (presenter != null) {
			display.addContent(presenter.getDisplay());
			this.presenter.bind();
		}

	}

	@Override
	public void bind() {

		super.bind();

		registerHandler(eventBus.addHandler(AddNewIssueEvent.getType(),
				new AddNewIssueHandler() {
					public void onAddNewIssue(AddNewIssueEvent event) {
						doAddNewIssue();
					}
				}));

		registerHandler(eventBus.addHandler(IssueEditEvent.getType(),
				new IssueEditHandler() {
					public void onIssueEdit(IssueEditEvent event) {
						doIssueEdit(event.getIssue());
					}
				}));

		registerHandler(eventBus.addHandler(IssueRemovedEvent.getType(),
				new IssueRemovedHandler() {
					public void onIssueRemoved(IssueRemovedEvent event) {
						doIssueRemoved();
					}
				}));

		registerHandler(eventBus.addHandler(IssueEditCanceledEvent.getType(),
				new IssueEditCanceledHandler() {
					public void onIssueEditCanceled(IssueEditCanceledEvent event) {
						doIssueEditCanceled(event.getIssue());
					}
				}));

		registerHandler(eventBus.addHandler(IssueUpdatedEvent.getType(),
				new IssueUpdatedHandler() {
					public void onIssueUpdated(IssueUpdatedEvent event) {
						doIssueUpdated(event.getIssue());
					}
				}));

	}

	@Override
	public void unbind() {
		super.unbind();
		if (presenter != null) {
			presenter.unbind();
		}
	}

	private void doIssueUpdated(Issue issue) {
		final IssueDisplayPresenter displayPresenter = displayProvider.get();
		displayPresenter.showIssue(issue);
		switchPresenter(displayPresenter);
	}

	private void doIssueEditCanceled(Issue issue) {
		if (issue != null) {
			final IssueDisplayPresenter displayPresenter = displayProvider
					.get();
			displayPresenter.showIssue(issue);
			switchPresenter(displayPresenter);
		} else {
			switchPresenter(null);
		}
	}

	private void doAddNewIssue() {
		final IssueEditPresenter editPresenter = editProvider.get();
		editPresenter.createIssue();
		switchPresenter(editPresenter);
	}

	private void doIssueRemoved() {
		switchPresenter(null);
	}

	private void doIssueEdit(Issue issue) {
		final IssueEditPresenter editPresenter = editProvider.get();
		editPresenter.editIssue(issue);
		switchPresenter(editPresenter);
	}

}
