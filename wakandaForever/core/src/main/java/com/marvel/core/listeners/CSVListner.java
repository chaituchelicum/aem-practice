package com.marvel.core.listeners;

import com.marvel.core.services.CSVParserService;
import org.apache.sling.api.SlingConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class,immediate = true,property = {
        EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED"
})
public class CSVListner implements EventHandler {

    @Reference
    private CSVParserService parser;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void handleEvent(Event event) {
        String eventPath = (String) event.getProperty(SlingConstants.PROPERTY_PATH);
        String resourceType = (String) event.getProperty(SlingConstants.PROPERTY_RESOURCE_TYPE);
        if (eventPath.contains("/content/dam/wakandaForever/students/") && resourceType.equals("dam:Asset")) {
            logger.info("Resource event inside: {} at: {} and resourseType : {}", event.getTopic(), event.getProperty(SlingConstants.PROPERTY_PATH), event.getProperty(SlingConstants.PROPERTY_RESOURCE_TYPE));
            parser.parseCSV(eventPath);
        }
    }
}
