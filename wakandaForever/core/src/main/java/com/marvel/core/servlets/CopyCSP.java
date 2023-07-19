package com.marvel.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.BufferedReader;

@Component(service = Servlet.class,
           property = { "sling.servlet.paths=/bin/csp", "sling.servlet.method=post"})
public class CopyCSP extends SlingAllMethodsServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse res){
        try {
            logger.info("From CSP servlet ..1111....");
            BufferedReader br = req.getReader();
            StringBuilder sb = new StringBuilder();
            System.out.println("----Stage 1111111-----");
            String line;
            while((line = br.readLine())!=null){
                sb.append(line).append("\n");
                System.out.println("----Stage 222-----"+line);
                logger.info("From CSP servlet ...."+line);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
