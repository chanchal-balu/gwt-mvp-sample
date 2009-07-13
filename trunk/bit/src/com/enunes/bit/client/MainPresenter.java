/**
 * 
 */
package com.enunes.bit.client;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
public interface MainPresenter extends BasePresenter {

	interface View extends BaseView {

		void addMenu(BaseView view);

		void addContent(BaseView view);

		void removeContent();

	}

	View go();

}
