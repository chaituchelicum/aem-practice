package com.marvel.core.servlets;

import com.marvel.core.models.FirstServletResponse;
import com.marvel.core.services.FirstService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = { Servlet.class })
@ServiceDescription("My First Servlet")
@SlingServletResourceTypes(
        resourceTypes="wakandaForever/components/helloworld",
        methods=HttpConstants.METHOD_GET,
        extensions="json")
// To Test :- http://localhost:4502/content/wakandaForever/us/en/FirstPage/jcr:content/root/container/container/helloworld.txt
public class FirstServlet extends SlingAllMethodsServlet {

    @Reference
    private FirstService firstService;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        FirstServletResponse response = new FirstServletResponse();
        response.setName(firstService.getRandomName());
        response.setAge(20);
        JSONObject json = new JSONObject(response);
        resp.setContentType("json");
        resp.getWriter().write(json.toString());
    }
}
