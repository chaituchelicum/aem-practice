package com.marvel.core.services.impl;

import com.marvel.core.services.AddressService;
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

@Component(
        immediate = true,
        service = {AddressService.class}
)
@Designate(ocd = AddressServiceImpl.AddressConfig.class)
public class AddressServiceImpl implements AddressService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String url;

    private String apiKey;

    @ObjectClassDefinition(
            name = "Address - CONF",
            description = "OSGi Config supplying information to Address Service"
    )
    @interface AddressConfig {
        @AttributeDefinition(name = "URL", description = "URL")
        String url() default "https://api.ideal-postcodes.co.uk/v1/postcodes/" ;
        @AttributeDefinition(name = "apiKey", description = "APIKEY")
        String apiKey() default  "ak_ljs7lvgnfjECmS70kvGY8ROygz7Z4";
    }
    @Activate
    public void activate(AddressConfig config){
        logger.info("<<< Service Activated >>>");
        url = config.url();
        apiKey = config.apiKey();
        logger.info("URL ::: "+url);
        logger.info("KEY ::: "+apiKey);
    }
    @Override
    public String  getAddress(String postCode) {
        StringBuilder resp = new StringBuilder();
        try (CloseableHttpClient httpClient = HttpClients.createDefault();){
            HttpGet httpGet = new HttpGet(url+postCode+"?api_key="+apiKey);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                resp.append(inputLine);
            }
            reader.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return resp.toString();
    }
}
