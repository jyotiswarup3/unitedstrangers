package com.united.core.core.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
    resourceTypes = "united-strangers/components/page",
    selectors = {"dim", "light"},
    extensions = {"io", "ext"}    // This is the mandatory property to be given
    )
@ServiceDescription("Resource Site service description")
// resouceType : Any resource that has a resourceType of the given path in a base page and 
// as soon as this resource will be access this servlet is invoked.
public class ResourceTypeServlet extends SlingSafeMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceTypeServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req,
                        final SlingHttpServletResponse resp) throws ServletException, IOException {
        
        LOG.info(req.getResource().getPath().toString());
        

        // So with whatever the Resource this servlet has bound, whenever that resource will get called that Servlet will get invoked.

        resp.setContentType("application/json");
        List<RequestParameter> requestParameterList=req.getRequestParameterList();
        JsonArray pagesArray = new JsonArray();
            for(RequestParameter requestParameter : requestParameterList){
                
                LOG.info("\n ==PARAMETERS===>  {} : {} ",requestParameter.getName(),requestParameter.getString());
                
                JsonObject pageObject = new JsonObject();
                pageObject.addProperty(requestParameter.getName(), requestParameter.getString());
                pagesArray.add(pageObject);

            }
            
            
            final Resource resource=req.getResource();
            // Here from the "req" object we are getting the resource.
            Resource parentresource=resource.getParent();         
        
        try { 
                JsonObject pageObject = new JsonObject();   /*adding page details to json object*/
                pageObject.addProperty("Page Title", resource.getValueMap().get("jcr:title", String.class));
                pageObject.addProperty("Page Name:",parentresource.getName());
                pageObject.addProperty("Path of the Page", parentresource.getPath());
                pageObject.addProperty("Template Used", resource.getValueMap().get("cq:template", String.class));
                pageObject.addProperty("Page Resource Type", parentresource.getResourceType());
                pageObject.addProperty("Resource Type", resource.getValueMap().get("sling:resourceType", String.class));
                pageObject.addProperty("If this Resource have any children resource", parentresource.hasChildren());
                pagesArray.add(pageObject);
            
            
        } catch (Exception e) {
            LOG.info("Error {}", e.getMessage());
        }
        resp.getWriter().write(pagesArray.toString());



    }
}