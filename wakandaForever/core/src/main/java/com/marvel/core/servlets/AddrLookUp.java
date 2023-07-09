package com.marvel.core.servlets;
import java.io.*;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "wakandaForever/components/addresslookup",
                         extensions = "json")
public class AddrLookUp extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();){
            String PostCode = String.valueOf(request.getRequestParameter("postCode"));
            HttpGet httpGet = new HttpGet("https://api.ideal-postcodes.co.uk/v1/postcodes/"+PostCode+"?api_key=ak_ljs7lvgnfjECmS70kvGY8ROygz7Z4");
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));
            String inputLine;
            StringBuilder resp = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                resp.append(inputLine);
            }
            reader.close();
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(resp);
            out.flush();
        }catch (Exception e){
            PrintWriter out = response.getWriter();
            out.print(e.getMessage());
            out.flush();
        }
    }
}
