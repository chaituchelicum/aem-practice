package com.marvel.core.servlets;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.scripting.SlingScriptHelper;

public class MyComponent extends WCMUsePojo {

    private SlingScriptHelper slingScriptHelper;

    @Override
    public void activate() {
        slingScriptHelper = getSlingScriptHelper();
    }

    public String getServiceValue() {
        MyService service = slingScriptHelper.getService(MyService.class);
        //return service.getHelloMessage();
        return "RETURNNNNNNN";
    }
}
