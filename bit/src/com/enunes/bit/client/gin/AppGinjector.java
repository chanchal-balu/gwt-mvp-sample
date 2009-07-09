/**
 * 
 */
package com.enunes.bit.client.gin;

import com.enunes.bit.client.MainPresenter;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
@GinModules(AppModule.class)
public interface AppGinjector extends Ginjector {

	MainPresenter getMainPresenter();

	HandlerManager getEventBus();

}
