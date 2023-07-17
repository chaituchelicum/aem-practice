package com.marvel.core.services.impl;

import com.day.cq.dam.api.Asset;
import com.marvel.core.services.CSVParserService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.resource.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
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
            saveJsonFile(jsonString, resourceResolver);
            deleteOnSuccess(path, resourceResolver);
            logger.info("JSON STRING : {}",jsonString);
            resourceResolver.close();
        }catch (Exception e){
            logger.error(e.getMessage());

        }
    }

    private void saveJsonFile(String json, ResourceResolver resourceResolver) {
        try{
            String targetFolderPath = "/content/dam/wakandaForever/jsonfolder";
            String jsonFileName = "output.json";
            Resource targetFolder = resourceResolver.getResource(targetFolderPath);
            // Create the JSON file
            String jsonFilePath = targetFolderPath + "/" + jsonFileName;
            Session session = resourceResolver.adaptTo(Session.class);
            Node targetNode = session.getNode(targetFolderPath);
            // Create the nt:file node
            Node fileNode = JcrUtils.getOrAddNode(targetNode, "students.json", "nt:file");
            // Create the jcr:content node under the nt:file node
            Node contentNode = JcrUtils.getOrAddNode(fileNode, "jcr:content", "nt:resource");
            // Set the file's binary content
            contentNode.setProperty("jcr:data", json);
            // Set the file's MIME type (optional)
            contentNode.setProperty("jcr:mimeType", "application/json");
            session.save();
            logger.info("JSON file created at: " + jsonFilePath);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    private void deleteOnSuccess(String path, ResourceResolver resourceResolver){
        try {
            Session session = resourceResolver.adaptTo(Session.class);
            session.removeItem(path);
            session.save();
        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }
    private void createCF(){

    }
}
