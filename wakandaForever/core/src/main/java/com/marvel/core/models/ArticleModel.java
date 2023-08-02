package com.marvel.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = Resource.class)
public class ArticleModel {


    public ArticleModel(Resource resource) {
        // Get Current Articles Path
        this.artPath = resource.getPath();

        // Get Current Articles Image
        Resource imageRes = resource.getChild("root/image");
        this.image = imageRes.getValueMap().get("fileReference", String.class);
    }

    //Need to get the resource.
    private String artPath;


    @Default(values="Test-default")
    @Inject
    @Named("feedDesc")
    protected String description;

    @Default(values="Test-default-title")
    @Inject
    protected String feedTitle;

    public String getDescription() {
        return description;
    }

    public String getFeedTitle() {
        return feedTitle;
    }


    private String image;

    @PostConstruct
    protected void init() {
        this.artPath = this.artPath.replace("/jcr:content",".html");
    }
}
