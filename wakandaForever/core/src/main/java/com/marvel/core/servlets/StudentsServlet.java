package com.marvel.core.servlets;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes = "wakandaForever/components/studentlookup",
        extensions = "json")
@ServiceDescription("Students Servlet")
public class StudentsServlet extends SlingAllMethodsServlet {

    @Reference
    QueryBuilder builder;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            ResourceResolver resourceResolver = request.getResourceResolver();
            String fragmentsPath = "/content/dam/wakandaForever/student_fragments";
            String filterValue = String.valueOf(request.getRequestParameter("filter"));
            List<Resource> fragments = queryAndGetCFs(resourceResolver,fragmentsPath,filterValue);
            JSONArray jsonArray = new JSONArray();
            for (Resource fragment : fragments) {
                ContentFragment frag = fragment.adaptTo(ContentFragment.class);
                JSONObject jsonObject = new JSONObject();
                if (frag !=null) {
                    jsonObject.put("class", frag.getElement("class").getContent());
                    jsonObject.put("rollNumber", frag.getElement("rollNumber").getContent());
                    jsonObject.put("studentName", frag.getElement("studentName").getContent());
                    jsonArray.put(jsonObject);
                }
            }
            response.getWriter().write(jsonArray.toString());
            logger.info("SERVLET CF JSON is {}", jsonArray);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

    }

    public List<Resource> queryAndGetCFs(ResourceResolver resourceResolver, String fragmentsPath, String filterValue){
        try {
            Map<String, String> queryParams = new HashMap<>();
            Session session = resourceResolver.adaptTo(Session.class);
            queryParams.put("path", fragmentsPath);
            queryParams.put("type", "dam:Asset");
            queryParams.put("property", "jcr:content/data/master/*");
            queryParams.put("property.value", "%"+filterValue+"%");
            queryParams.put("property.operation", "like");
            Query query = builder.createQuery(PredicateGroup.create(queryParams), session);
            SearchResult result = query.getResult();
            List<Resource> paths = new ArrayList<>();
            for (Hit hit : result.getHits()) {
                Resource resource = resourceResolver.getResource(hit.getPath());
                paths.add(resource);
            }
            return paths;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
