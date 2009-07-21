/**
 * 
 */
package com.enunes.bit.client;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.enunes.bit.client.event.AddNewIssueEvent;
import com.enunes.bit.client.event.AddNewIssueHandler;
import com.enunes.bit.client.event.IssueEditCanceledEvent;
import com.enunes.bit.client.event.IssueEditCanceledHandler;
import com.enunes.bit.client.event.IssueEditEvent;
import com.enunes.bit.client.event.IssueEditHandler;
import com.enunes.bit.client.event.IssueRemovedEvent;
import com.enunes.bit.client.event.IssueRemovedHandler;
import com.enunes.bit.client.event.IssueUpdatedEvent;
import com.enunes.bit.client.event.IssueUpdatedHandler;
import com.enunes.bit.client.model.Issue;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class MainPresenter extends WidgetPresenter<MainPresenter.Display> {

	public interface Display extends WidgetDisplay {

		void addMenu(WidgetDisplay display);

		void addContent(WidgetDisplay display);

		void removeContent();

	}

	private final MenuPresenter menuPresenter;
	private final Provider<IssueEditPresenter> editPresenter;
	private final Provider<IssueDisplayPresenter> displayPresenter;

	@Inject
	public MainPresenter(Display display, EventBus eventBus,
			MenuPresenter menuPresenter,
			Provider<IssueEditPresenter> editPresenter,
			Provider<IssueDisplayPresenter> displayPresenter) {
		super(display, eventBus);
		this.menuPresenter = menuPresenter;
		this.editPresenter = editPresenter;
		this.displayPresenter = displayPresenter;
	}

	@Override
	public Place getPlace() {
		return null;
	}

	@Override
	protected void onBind() {
		menuPresenter.bind();
		eventBus.addHandler(AddNewIssueEvent.getType(),
				new AddNewIssueHandler() {
					public void onAddNewIssue(AddNewIssueEvent event) {
						doAddNewIssue();
					}
				});

		eventBus.addHandler(IssueEditEvent.getType(), new IssueEditHandler() {
			public void onIssueEdit(IssueEditEvent event) {
				doIssueEdit(event.getIssue());
			}
		});

		eventBus.addHandler(IssueRemovedEvent.getType(),
				new IssueRemovedHandler() {
					public void onIssueRemoved(IssueRemovedEvent event) {
						doIssueRemoved();
					}
				});

		eventBus.addHandler(IssueEditCanceledEvent.getType(),
				new IssueEditCanceledHandler() {
					public void onIssueEditCanceled(IssueEditCanceledEvent event) {
						doIssueEditCanceled(event.getIssue());
					}
				});

		eventBus.addHandler(IssueUpdatedEvent.getType(),
				new IssueUpdatedHandler() {
					public void onIssueUpdated(IssueUpdatedEvent event) {
						doIssueUpdated(event.getIssue());
					}
				});
	}

	private void doIssueUpdated(Issue issue) {
		final IssueDisplayPresenter presenter = displayPresenter.get();
		presenter.bind();
		presenter.showIssue(issue);
		display.addContent(presenter.getDisplay());
	}

	private void doIssueEditCanceled(Issue issue) {
		if (issue != null) {
			final IssueDisplayPresenter presenter = displayPresenter.get();
			presenter.bind();
			presenter.showIssue(issue);
			display.addContent(presenter.getDisplay());
		} else {
			display.removeContent();
		}
	}

	private void doAddNewIssue() {
		IssueEditPresenter presenter = editPresenter.get();
		presenter.createIssue();
		presenter.bind();
		display.addContent(presenter.getDisplay());
	}

	private void doIssueRemoved() {
		display.removeContent();
	}

	private void doIssueEdit(Issue issue) {
		IssueEditPresenter presenter = editPresenter.get();
		presenter.editIssue(issue);
		presenter.bind();
		display.addContent(presenter.getDisplay());
	}

	@Override
	protected void onPlaceRequest(PlaceRequest request) {
	}

	@Override
	protected void onUnbind() {
	}

	public void refreshDisplay() {
	}

	public void revealDisplay() {
		display.addMenu(menuPresenter.getDisplay());
	}

}
