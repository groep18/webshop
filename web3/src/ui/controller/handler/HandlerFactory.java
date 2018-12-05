package ui.controller.handler;

import domain.service.ShopService;
import ui.controller.ControllerException;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HandlerFactory {

	private Map<String, RequestHandler> handlers = new HashMap<>();
	
	public HandlerFactory(Properties handlerProperties, ShopService shopService) throws ControllerException {
		for(Object key : handlerProperties.keySet()) {
			RequestHandler handler = null;
			String action = (String) key;
			String handlerName = handlerProperties.getProperty(action);
			try {
				Class<?> handlerClass = Class.forName(handlerName);
				Constructor<?> handlerConstructor = handlerClass.getConstructor(ShopService.class, HandlerFactory.class);
				Object handlerObject = handlerConstructor.newInstance(shopService, this);
				handler = (RequestHandler) handlerObject;
			} catch(Exception e) {
				throw new ControllerException("Something went wrong creating a handler for " + key + ":\n" + e.getMessage());
			}
			this.handlers.put(action, handler);
		}
	}
	public RequestHandler getHandler(String key) {
		return this.handlers.get(key);
	}

}
