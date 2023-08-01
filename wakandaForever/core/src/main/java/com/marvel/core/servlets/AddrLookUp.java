package com.marvel.core.servlets;

import com.marvel.core.services.AddressService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "wakandaForever/components/addresslookup",
                         extensions = "json")
@ServiceDescription("This is just desc")
public class AddrLookUp extends SlingAllMethodsServlet {

    @Reference
    private AddressService addressService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
            String resp = addressService.getAddress(String.valueOf(request.getRequestParameter("postCode")));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(resp);
            out.flush();
            System.out.println("Conflict");
    }
}

