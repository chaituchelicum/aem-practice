package com.marvel.core.servlets;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.Gson;
import com.marvel.core.models.ArticleModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Session;
import javax.servlet.Servlet;
import java.util.*;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(resourceTypes = {"wakandaForever/components/articles", "wakandaForever/components/page"},
        extensions = "json")


public class Articles extends SlingSafeMethodsServlet {
    @Reference
    private QueryBuilder queryBuilder;
    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response){
        try {

            ResourceResolver resourceResolver = request.getResourceResolver();
            Session session = resourceResolver.adaptTo(Session.class);
            String ARTICLE_RESOURCE_TYPE = "/conf/wakandaForever/settings/wcm/templates/rajraao-template";
            final Resource resource = request.getResource();

            PageManager pageManager = request.getResourceResolver().adaptTo(PageManager.class);
            Page currentPage = pageManager.getContainingPage(request.getResource());
            String pagePath = currentPage.getPath();

            final List<Resource> resources = new ArrayList<Resource>();
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", pagePath);
            map.put("type", "cq:PageContent");
            map.put("1_property","cq:template");
            map.put("1_property.value", ARTICLE_RESOURCE_TYPE);
            map.put("p.limit","-1");
            Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
            SearchResult result = query.getResult();
            for (final Hit hit : result.getHits()) {
                resources.add(resourceResolver.getResource(hit.getPath()));
            }
            ArrayList<ArticleModel> allArticles = new ArrayList<>();

            //Iterate over the resources in our list from the query.
            Iterator<Resource> resourceIterator= resources.iterator();
            while (resourceIterator.hasNext()) {

                //Grab the current Resource/Article
                Resource currentResource = resourceIterator.next();

                //Adapt it to our Sling Model (aka make it an article!)
                ArticleModel currenArticle = currentResource.adaptTo(ArticleModel.class);

                //Add it to our array.
                allArticles.add(currenArticle);
            }
            String responseJson = new Gson().toJson(allArticles);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(responseJson);
            resourceResolver.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
