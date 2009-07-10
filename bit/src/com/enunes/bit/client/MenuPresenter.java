/**
 * 
 */
package com.enunes.bit.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public interface MenuPresenter {

	interface View {

		void addClickHandler(ClickHandler handler);

		Widget getWidget();

	}

	Widget showMenu();

}
