/**
 * 
 */
package com.enunes.bit.client;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.enunes.bit.client.event.IssueEditCanceledEvent;
import com.enunes.bit.client.event.IssueUpdatedEvent;
import com.enunes.bit.client.model.Issue;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class IssueEditPresenter extends
        WidgetPresenter<IssueEditPresenter.Display> {

    public interface Display extends WidgetDisplay {

        HasClickHandlers getSaveClickHandlers();

        HasClickHandlers getCancelClickHandlers();

        HasText getTaskName();

        HasText getTaskReporter();

        HasValue<Integer> getStars();

    }

    private Issue issue;

    @Inject
    public IssueEditPresenter(Display display, EventBus eventBus) {
        super(display, eventBus);
    }

    @Override
    public Place getPlace() {
        return null;
    }

    @Override
    protected void onBind() {

        registerHandler(display.getCancelClickHandlers().addClickHandler(
                new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        eventBus.fireEvent(new IssueEditCanceledEvent(issue));
                    }
                }));

        registerHandler(display.getSaveClickHandlers().addClickHandler(
                new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        if (issue == null) {
                            issue = new Issue(1);
                        }
                        issue.setTaskName(display.getTaskName().getText());
                        issue.setTaskReporter(display.getTaskReporter()
                                .getText());
                        try {
                            issue.setStars(display.getStars().getValue());
                        } catch (NumberFormatException e) {
                            issue.setStars(0);
                        }
                        eventBus.fireEvent(new IssueUpdatedEvent(issue));
                    }
                }));

    }

    @Override
    protected void onPlaceRequest(PlaceRequest request) {
    }

    @Override
    protected void onUnbind() {
    }

    public void refreshDisplay() {
    }

    public void createIssue() {
        issue = null;
    }

    public void editIssue(Issue issue) {
        this.issue = issue;
        display.getTaskName().setText(issue.getTaskName());
        display.getTaskReporter().setText(issue.getTaskReporter());
        display.getStars().setValue(issue.getStars());
    }

    public void revealDisplay() {
    }

}
