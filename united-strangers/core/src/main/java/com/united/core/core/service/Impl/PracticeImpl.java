package com.united.core.core.service.Impl;

import java.util.Iterator;

import org.apache.sling.api.resource.LoginException;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.united.core.core.service.Practice;
import com.united.core.core.utils.ResolverUtil;

@Component(service = Practice.class, immediate = true)
public class PracticeImpl implements Practice {
    private static final Logger LOG = LoggerFactory.getLogger(PracticeImpl.class);
    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Activate
    protected void activate() throws LoginException {

        LOG.info("------------> System user activated <---------------");

        ResourceResolver resolver = ResolverUtil.newResolver(resourceResolverFactory);

        LOG.info("UserID: " + resolver.getUserID());

    }

    @Override

    public Iterator<Page> getPages() {
        try {

            // ResourceResolver resolver =
            // ResolverUtil.newResolver(resourceResolverFactory);
            ResourceResolver abc = ResolverUtil.newResolver(resourceResolverFactory);

            LOG.info("UserID: " + abc.getUserID());
            LOG.info("Validity: " + abc.isLive());

            // PageManager pageManager = resolver.adaptTo(PageManager.class);

            PageManager pageManager = abc.adaptTo(PageManager.class);

            LOG.info("------------> PAGE OPERATIONS STARTED <---------------");

            Page page = pageManager.getPage("/content/united-strangers/language-masters/en/Requirements");

            LOG.info("The name of current page is" + page.getName());

            Iterator<Page> pages = page.listChildren();
            return pages;

        } catch (LoginException e) {
            LOG.info("\n Login Exception {} ", e.getMessage());
        }

        return null;

    }
}
