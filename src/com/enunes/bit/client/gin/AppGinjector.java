/**
 * 
 */
package com.enunes.bit.client.gin;

import net.customware.gwt.presenter.client.EventBus;

import com.enunes.bit.client.MainPresenter;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
@GinModules(AppModule.class)
public interface AppGinjector extends Ginjector {

	MainPresenter getMainPresenter();

	EventBus getEventBus();

}
