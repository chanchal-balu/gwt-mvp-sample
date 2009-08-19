package org.enunes.gwt.sample.bit.client.gin;

import org.enunes.gwt.mvp.client.EventBus;
import org.enunes.gwt.sample.bit.client.MainPresenter;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * 
 * @author esnunes@gmail.com (Eduardo S. Nunes)
 * 
 */
@GinModules(Module.class)
public interface Injector extends Ginjector {

	MainPresenter getMainPresenter();

	EventBus getEventBus();

}
