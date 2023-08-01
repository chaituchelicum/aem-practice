package com.marvel.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

public class Articles extends SlingSafeMethodsServlet {
    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response){
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource("/content/wakandaForever/us/articles");
        
    }

}
