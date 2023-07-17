package com.marvel.core.services.impl;

import com.day.cq.dam.api.Asset;
import com.marvel.core.services.CSVParserService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
@Component(service = CSVParserService.class, immediate = true)
public class CSVParserServiceImpl implements CSVParserService {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void parseCSV(String path) {
        logger.info("CSV PATH : {}",path);

        try {
            // Create a map of authentication information.
            Map<String, Object> authenticationInfo = new HashMap<>();
            authenticationInfo.put("sling.service.subservice", "wakanda-service");

            // Get the service resource resolver.
            ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(authenticationInfo);
            Resource res = resourceResolver.getResource(path);
            Asset asset = res.adaptTo(Asset.class);
            BufferedReader br = new BufferedReader(new InputStreamReader(asset.getRendition("original").getStream()));
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(br);
            JSONArray jsonArray = new JSONArray();
            for (CSVRecord record : parser){
                JSONObject jsonObject = new JSONObject();
                for (String header : parser.getHeaderMap().keySet()) {
                    jsonObject.put(header, record.get(header));
                }
                jsonArray.put(jsonObject);
            }
            String jsonString = jsonArray.toString();
            logger.info("JSON STRING : {}",jsonString);
            resourceResolver.close();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
