package com.marvel.core.servlets;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes="wakandaForever/components/helloworld",
        selectors = "pathServlet",
        methods= HttpConstants.METHOD_GET,
        extensions="json")
public class PathServlet extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();){

            HttpGet httpGet = new HttpGet("https://api.ideal-postcodes.co.uk/v1/postcodes/BR11DE?api_key=ak_ljs7lvgnfjECmS70kvGY8ROygz7Z4");
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
