package com.marvel.core.schedulers;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

@Component(service = Runnable.class,immediate = true)
@Designate(ocd=ThirdPartyHealthCheck.Config.class)
public class ThirdPartyHealthCheck implements Runnable{
    @ObjectClassDefinition(name="Third Party Health Check Scheduler",
            description = "Simple demo for cron-job like task with properties")
    public static @interface Config {

        @AttributeDefinition(name = "Cron-job expression")
        String scheduler_expression() default "*/30 * * * * ?";

        @AttributeDefinition(name = "Concurrent task",
                description = "Whether or not to schedule this task concurrently")
        boolean scheduler_concurrent() default false;

//        @AttributeDefinition(name = "A parameter",
//                description = "Can be configured in /system/console/configMgr")
//        String myParameter() default "";
    }
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void run() {
        StringBuilder resp = new StringBuilder();
        try (CloseableHttpClient httpClient = HttpClients.createDefault();){
            HttpGet httpGet = new HttpGet("http://localhost:8080/health");
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                resp.append(inputLine);
            }
            reader.close();
            logger.info("STATUS {}", resp);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        logger.info("Task Running Successfully every 30s"+new Date());
    }
}
