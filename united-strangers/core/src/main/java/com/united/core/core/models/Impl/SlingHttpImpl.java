package com.united.core.core.models.Impl;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.day.cq.wcm.api.Page;
import com.united.core.core.models.SlingHttp;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class, 
adapters = SlingHttp.class, 
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SlingHttpImpl implements SlingHttp {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlingHttpImpl.class);

    @Inject
    @Default(values = "Nazia")
    private String firstName;

    
    // @Inject
    @Named("jcr:createdBy")
       // @Via("resource")
    @ValueMapValue 
    private String namedAnnotation;
    
    @Inject
    @Required
    @Default(values = "Irfan")
    private String lastName;
    
    @Inject
    @Default(intValues={1,2,3,4,5,6,7,8,9})
    private int[] twoMethod;

    

    @ScriptVariable
    Page currentPage;

    @SlingObject
    ResourceResolver resourceResolver;

    @Self
    SlingHttpServletRequest slingHttpServletRequest;

    @RequestAttribute (name = "rAttribute")
    private String reqAttribute;


    @ResourcePath(path="/content/united-strangers/language-masters/en/Requirements/sling")@Via("resource")
    Resource resource;  
    

    @Override
    public String getFirstName() {

        return firstName;
    }
    
    @Override
    public String getCurrentPage() {


        return currentPage.getTitle(); 
    }

    @Override
    public String getLastName() {
        
        return StringUtils.reverse(lastName);
    }

    @Override
    public String getNamedProperty() {
        
        return namedAnnotation;
    }

    @Override
    public int[] getTwoMethod() {
        
        return twoMethod;
    }

    @Override
    public String getRequestAttribute() {
        
        return reqAttribute;
    }

    @Override
    public String getHomePageName() {

        return resource.getName(); 
    }

    @Override
    public String getHomePagePath() {

        return resource.getPath();
    }

    @PostConstruct
    public void init() { 
        LOGGER.debug("Inside Init Method {}", firstName);
        
        LOGGER.info(firstName);

        LOGGER.info(currentPage.getTitle());
        
        LOGGER.info(StringUtils.reverse(lastName));

        LOGGER.info(namedAnnotation);

        LOGGER.info(reqAttribute);

        LOGGER.info(resource.getName());

        LOGGER.info(resource.getPath());

    }

}
