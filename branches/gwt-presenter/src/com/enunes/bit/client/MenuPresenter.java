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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.inject.Inject;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class MenuPresenter extends WidgetPresenter<MenuPresenter.Display> {

    public interface Display extends WidgetDisplay {

        HasClickHandlers getAddIssueClickHandlers();

    }

    @Inject
    public MenuPresenter(Display display, EventBus eventBus) {
        super(display, eventBus);
    }

    @Override
    public Place getPlace() {
        return null;
    }

    @Override
    protected void onBind() {
        registerHandler(display.getAddIssueClickHandlers().addClickHandler(
                new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        eventBus.fireEvent(new AddNewIssueEvent());
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

    public void revealDisplay() {
    }

}
