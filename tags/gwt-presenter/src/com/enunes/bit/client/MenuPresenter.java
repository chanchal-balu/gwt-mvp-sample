/**
 * 
 */
package com.enunes.bit.client;

import com.google.gwt.event.dom.client.HasClickHandlers;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public interface MenuPresenter extends BasePresenter {

	interface View extends BaseView {

		HasClickHandlers getAddIssueClickHandlers();

	}

	View showMenu();

}
