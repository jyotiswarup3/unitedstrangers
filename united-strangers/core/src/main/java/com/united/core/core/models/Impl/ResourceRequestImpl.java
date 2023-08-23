package com.united.core.core.models.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.united.core.core.models.ResourceRequest;
import com.united.core.core.service.Practice;
import com.united.core.core.service.Service1;

@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class, }, adapters = ResourceRequest.class, resourceType = ResourceRequestImpl.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ResourceRequestImpl implements ResourceRequest {
    private static final Logger LOG = LoggerFactory.getLogger(ResourceRequestImpl.class);
    
    protected static final String RESOURCE_TYPE = "united-strangers/components/resourserequestcomponent";

    @Inject
    @Default(values = "sachin")
    String name;

    @ValueMapValue
    String score;

    @Inject
    String ball;

    @Inject
    @Required
    String strikerate;

    @Inject
    String city;

    @RequestAttribute(name = "variable")
    private String reqAttribute;

    @ValueMapValue
    private List<String> item;

    @ScriptVariable
    Page currentPage;

    @SlingObject
    SlingHttpServletRequest request;

    @OSGiService
    Practice practice;

    @OSGiService(filter = "(component.name=serviceF)")
    Service1 service;
    @OSGiService(filter = "(component.name=serviceS)")
    Service1 service2;

    @ResourcePath(path = "/content/united-strangers/us/en")
    Resource resourcePage;

    @ValueMapValue
    String id;

    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public String getPlayerScore() {
        return score;
    }

    @Override
    public String getBallsPlayed() {
        return ball;
    }

    @Override
    public String getStrikeRate() {

        return strikerate;
    }

    @Override
    public String getRequestAttribute() {
        LOG.debug("method value {} ", reqAttribute);
        return reqAttribute;
    }

    @Override
    public String getPageTitle() {
        return currentPage.getTitle();
    }

    @Override
    public Iterator<Page> getPagesList() {
        LOG.info("start of the method page list ");
        LOG.debug("method value {} ", practice.getPages());
        LOG.error("method value error");
        return practice.getPages();

    }

    @Override
    public List<String> getBooks() {
        LOG.info("Start of the method");
        if (item != null) {
            LOG.debug("method value {} ", item);
            return new ArrayList<String>(item);
        } else {
            LOG.info("End of the method");
            return Collections.emptyList();
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getReversestringA() {
        return service.reverseString(name);
    }

    @Override
    public String getReversestringB() {
        return service2.reverseString(id);
    }

    @Override
    public String getLocation() {
        return city;
    }

    @PostConstruct
    protected void init() {
        // Get the unique id from generator
        LOG.info("Inside INIT Method");
        String generatedId = IDGenerator.generateUniqueID(8);
        LOG.debug(" Generated id is: {}", generatedId);
        // Getting the reference of the current node
        Node currentNode = request.getResource().adaptTo(Node.class);
        // Stored id, if any ID IS already
        String storedId;
        // Getting the current session
        Session session = request.getResourceResolver().adaptTo(Session.class);

        try {
            if (currentNode != null && !currentNode.hasProperty("id")) {
                currentNode.setProperty("id", generatedId);
                LOG.info("The id is" + id);
            } else {
                // Getting the stored id from the node
                storedId = Objects.requireNonNull(currentNode).getProperty("id").getString();
                if (storedId == null || storedId.isEmpty()) {
                    Objects.requireNonNull(currentNode).setProperty("id", generatedId);
                }
            }
            // Saving the session
            Objects.requireNonNull(session).save();
        } catch (RepositoryException e) {
            LOG.error(" Error occurred: {}", e.getMessage());
        }
    }

}
