package com.marvel.core.services.impl;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.dam.cfm.FragmentTemplate;
import com.day.cq.dam.api.Asset;
import com.marvel.core.services.CSVParserService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Component(service = CSVParserService.class, immediate = true)
public class CSVParserServiceImpl implements CSVParserService {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void parseCSV(String path) {
        logger.info("CSV PATH : {}", path);
        try {
            Map<String, Object> authenticationInfo = new HashMap<>();
            authenticationInfo.put("sling.service.subservice", "wakanda-service");

            // Get the service resource resolver.
            ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(authenticationInfo);
            Resource res = resourceResolver.getResource(path);
            Session session = resourceResolver.adaptTo(Session.class);
            Asset asset = res.adaptTo(Asset.class);
            BufferedReader br = new BufferedReader(new InputStreamReader(asset.getRendition("original").getStream()));
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(br);
            JSONArray jsonArray = new JSONArray();
            for (CSVRecord record : parser) {
                JSONObject jsonObject = new JSONObject();
                for (String header : parser.getHeaderMap().keySet()) {
                    jsonObject.put(header.trim(), record.get(header).trim());
                }
                jsonArray.put(jsonObject);
            }
            if (createCF(jsonArray, resourceResolver)) {
                String jsonString = jsonArray.toString();
                saveJsonFile(jsonString, session);
                deleteOnSuccess(path, session);
                logger.info("JSON STRING : {}", jsonString);
            } else {
                moveOnFailure(path, session);
            }
            resourceResolver.close();
        } catch (Exception e) {
            logger.error("=====" + e.getMessage());
            logger.error(e.getMessage());

        }
    }

    private void saveJsonFile(String json, Session session) {
        try {
            String targetFolderPath = "/content/dam/wakandaForever/students_success";
            String jsonFileName = "output.json";
            // Create the JSON file
            String jsonFilePath = targetFolderPath + "/" + jsonFileName;
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
        } catch (Exception e) {
            logger.error("!!!!!=====" + e.getMessage());
            logger.error(e.getMessage());
        }
    }

    private void deleteOnSuccess(String path, Session session) {
        try {
            session.removeItem(path);
            session.save();
        } catch (Exception e) {
            logger.error("#####=====" + e.getMessage());
            logger.error(e.getMessage());
        }

    }

    private void moveOnFailure(String path, Session session) {
        try {
            String failPath = "/content/dam/wakandaForever/students_failure/";
            SimpleDateFormat geek = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            Date now = new Date();
            String date = geek.format(now);
            session.move(path, failPath + date + ".csv");
            session.save();
        } catch (Exception e) {
            logger.error("#####=====" + e.getMessage());
            logger.error(e.getMessage());
        }
    }

    private boolean createCF(JSONArray jsonArray, ResourceResolver rs) throws JSONException, Exception {
        try {
            Resource templateOrModelRsc = rs.getResource("/conf/wakandaForever/settings/dam/cfm/models/studentmodel");
            FragmentTemplate tpl = templateOrModelRsc.adaptTo(FragmentTemplate.class);
            String fragmentPath = "/content/dam/wakandaForever/student_fragments";
            Resource parentRsc = rs.getResource(fragmentPath);
            logger.info("-----jsonArray size is is::" + jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject excelObj = jsonArray.getJSONObject(i);
                Iterable<Resource> itr = parentRsc.getChildren();
                AtomicBoolean flag = new AtomicBoolean(false);
                itr.forEach(
                        e -> {
                            logger.info("Name is::" + e.getName() + "---Path is--" + e.getPath());
                            try {
                                if (e.getName().equalsIgnoreCase(excelObj.getString("Roll No"))) {
                                    logger.info("Existing Recordsssss.....");
                                    ContentFragment existingCF = e.adaptTo(ContentFragment.class);
                                    existingCF.getElement("studentName").setContent(excelObj.getString("Name"), "text/plain");
                                    existingCF.getElement("class").setContent(excelObj.getString("Class"), "text/plain");
                                    flag.set(true);
                                }

                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                );
                if (!flag.get()) {
                    logger.info("New Record creation");
                    ContentFragment newFragment = tpl.createFragment(parentRsc, excelObj.getString("Roll No"), excelObj.getString("Name"));
                    newFragment.getElement("studentName").setContent(excelObj.getString("Name"), "text/plain");
                    newFragment.getElement("rollNumber").setContent(excelObj.getString("Roll No"), "text/plain");
                    newFragment.getElement("class").setContent(excelObj.getString("Class"), "text/plain");
                }

            }
            return true;
        } catch (Exception e) {
            logger.error("=====" + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }
}
