package com.enunes.bit.client;

import net.customware.gwt.presenter.client.EventBus;

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
import com.enunes.bit.client.gin.AppGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class Bit implements EntryPoint {

    public void onModuleLoad() {

        final AppGinjector ginjector = GWT.create(AppGinjector.class);

        final MainPresenter mainPresenter = ginjector.getMainPresenter();
        mainPresenter.bind();
        mainPresenter.revealDisplay();

        RootPanel.get().add(mainPresenter.getDisplay().asWidget());

        logEvent(ginjector.getEventBus());

        RootPanel.get().add(new Button("exit", new ClickHandler() {
            public void onClick(ClickEvent event) {
                mainPresenter.unbind();
            }
        }));

    }

    private void logEvent(final EventBus eventBus) {
        eventBus.addHandler(IssueEditEvent.getType(), new IssueEditHandler() {
            public void onIssueEdit(IssueEditEvent event) {
                GWT.log(event.toDebugString(), null);
            }
        });
        eventBus.addHandler(IssueRemovedEvent.getType(),
                new IssueRemovedHandler() {
                    public void onIssueRemoved(IssueRemovedEvent event) {
                        GWT.log(event.toDebugString(), null);
                    }
                });
        eventBus.addHandler(AddNewIssueEvent.getType(),
                new AddNewIssueHandler() {
                    public void onAddNewIssue(AddNewIssueEvent event) {
                        GWT.log(event.toDebugString(), null);
                    }
                });
        eventBus.addHandler(IssueEditCanceledEvent.getType(),
                new IssueEditCanceledHandler() {
                    public void onIssueEditCanceled(IssueEditCanceledEvent event) {
                        GWT.log(event.toDebugString(), null);
                    }
                });
        eventBus.addHandler(IssueUpdatedEvent.getType(),
                new IssueUpdatedHandler() {
                    public void onIssueUpdated(IssueUpdatedEvent event) {
                        GWT.log(event.toDebugString(), null);
                    }
                });
    }

}
