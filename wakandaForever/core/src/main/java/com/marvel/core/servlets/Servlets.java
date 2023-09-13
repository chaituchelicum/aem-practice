package com.marvel.core.servlets;


import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, property = { "sling.servlet.methods=GET", "sling.servlet.paths=/bin/addressLookup" })
public class Servlets extends SlingAllMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        // Process the form submission and generate the response
        String responseData = "Response from path servlet";

        // Set the response data as a property to be displayed in the HTL component
        response.getWriter().write(responseData);
    }
}
