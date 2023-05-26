package com.marvel.core.listeners;


import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class,immediate = true,property = {"event.topics=org/apache/sling/api/auth/LogoutEvent"})
@ServiceDescription("Listener for Logout events")
public class LogOutListener implements EventHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void handleEvent(Event event) {
            logger.info("@@@@@@@ USER LOGGED OUT @@@@@@@",event);
    }
}
