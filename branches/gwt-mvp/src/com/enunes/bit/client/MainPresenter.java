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

    private final Provider<IssueEditPresenter> editPresenterProvider;

    private final Provider<IssueDisplayPresenter> displayPresenterProvider;

    private IssueEditPresenter editPresenter;

    private IssueDisplayPresenter displayPresenter;

    @Inject
    public MainPresenter(Display display, EventBus eventBus,
            MenuPresenter menuPresenter,
            Provider<IssueEditPresenter> editPresenter,
            Provider<IssueDisplayPresenter> displayPresenter) {
        super(display, eventBus);
        this.menuPresenter = menuPresenter;
        this.editPresenterProvider = editPresenter;
        this.displayPresenterProvider = displayPresenter;
    }

    @Override
    public Place getPlace() {
        return null;
    }

    @Override
    protected void onBind() {

        menuPresenter.bind();

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
    protected void onUnbind() {
        menuPresenter.unbind();
        if (editPresenter != null) {
            editPresenter.unbind();
        }
        if (displayPresenter != null) {
            displayPresenter.unbind();
        }
    }

    private void doIssueUpdated(Issue issue) {
        final IssueDisplayPresenter presenter = ensureDisplayPresenter();
        presenter.showIssue(issue);
        display.addContent(presenter.getDisplay());
    }

    private void doIssueEditCanceled(Issue issue) {
        if (issue != null) {
            final IssueDisplayPresenter presenter = ensureDisplayPresenter();
            presenter.showIssue(issue);
            display.addContent(presenter.getDisplay());
        } else {
            display.removeContent();
        }
    }

    private void doAddNewIssue() {
        final IssueEditPresenter presenter = ensureEditPresenter();
        presenter.createIssue();
        display.addContent(presenter.getDisplay());
    }

    private void doIssueRemoved() {
        display.removeContent();
    }

    private void doIssueEdit(Issue issue) {
        final IssueEditPresenter presenter = ensureEditPresenter();
        presenter.editIssue(issue);
        display.addContent(presenter.getDisplay());
    }

    @Override
    protected void onPlaceRequest(PlaceRequest request) {
    }

    public void refreshDisplay() {
    }

    public void revealDisplay() {
        menuPresenter.revealDisplay();
        display.addMenu(menuPresenter.getDisplay());
    }

    private IssueEditPresenter ensureEditPresenter() {
        if (editPresenter == null) {
            editPresenter = editPresenterProvider.get();
            editPresenter.bind();
        }
        return editPresenter;
    }

    private IssueDisplayPresenter ensureDisplayPresenter() {
        if (displayPresenter == null) {
            displayPresenter = displayPresenterProvider.get();
            displayPresenter.bind();
        }
        return displayPresenter;
    }

}
