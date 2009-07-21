package com.enunes.bit.client.extra;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public class StarsWidget extends Widget implements HasValue<Integer> {

    private final int count;

    private final Element[] liElements;

    private final Element[] aElements;

    private boolean readOnly = false;

    private Integer value;

    public StarsWidget(final int count) {

        this.count = count;

        liElements = new Element[count];
        aElements = new Element[count];

        final Element divElement = DOM.createDiv();
        setElement(divElement);
        
        divElement.setClassName("gwt-StarsWidget");

        final Element ulElement = DOM.createElement("ul");
        divElement.appendChild(ulElement);

        final Element spanTrick = DOM.createSpan();
        spanTrick.getStyle().setProperty("display", "block");
        spanTrick.getStyle().setProperty("clear", "both");
        spanTrick.getStyle().setProperty("height", "0px");
        spanTrick.setInnerHTML("<!-- -->");
        divElement.appendChild(spanTrick);

        value = 0;

        for (int i = 0; i < count; i++) {
            final Element li = DOM.createElement("li");
            final Element a = DOM.createAnchor();
            a.setAttribute("href", "javascript:;");
            li.appendChild(a);
            liElements[i] = li;
            aElements[i] = a;
            DOM.sinkEvents(a, Event.ONCLICK);
            ulElement.appendChild(li);
        }

        render();

    }

    @Override
    public void onBrowserEvent(Event event) {

        final com.google.gwt.dom.client.Element target = Element.as(event.getEventTarget());

        for (int i = 0; i < count; i++) {
            if (aElements[i] == target) {
                onStarClicked(i);
                break;
            }
        }

    }

    protected void onStarClicked(int i) {
        if (i == value - 1) {
            setValue(0, true);
        } else {
            setValue(i + 1, true);
        }
    }

    private void render() {

        for (int i = 0; i < count; i++) {
            if (i <= value - 1) {
                liElements[i].getStyle().setProperty("backgroundPosition",
                        "0 -16px");
            } else {
                liElements[i].getStyle().setProperty("backgroundPosition",
                        "0 0");
            }
            if (readOnly) {
                aElements[i].getStyle().setProperty("display", "none");
            } else {
                aElements[i].getStyle().setProperty("display", "block");
            }
        }

    }

    public boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        render();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        setValue(value, true);
    }

    public void setValue(Integer value, boolean fireEvents) {

        if (this.value.compareTo(value) != 0) {
            final Integer oldValue = this.value;
            this.value = value;
            render();
            ValueChangeEvent.<Integer> fireIfNotEqual(this, oldValue, value);
        }

    }

    public HandlerRegistration addValueChangeHandler(
            ValueChangeHandler<Integer> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

}
