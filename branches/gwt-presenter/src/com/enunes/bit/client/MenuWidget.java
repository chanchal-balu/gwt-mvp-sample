/**
 * 
 */
package com.enunes.bit.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class MenuWidget extends Composite implements MenuPresenter.Display {

    private final Button addIssueButton;

    public MenuWidget() {

        final FlowPanel panel = new FlowPanel();
        panel.setStyleName("menu");
        initWidget(panel);

        addIssueButton = new Button("add issue");
        panel.add(addIssueButton);

    }

    public HasClickHandlers getAddIssueClickHandlers() {
        return addIssueButton;
    }

    public Widget asWidget() {
        return this;
    }

    public void startProcessing() {
    }

    public void stopProcessing() {
    }

}
