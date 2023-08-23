package com.united.core.core.models.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import com.united.core.core.helper.PathDetail;
import com.united.core.core.models.Navigation;
import com.united.core.core.utils.ResolverUtil;

@Model(adaptables = SlingHttpServletRequest.class,adapters = Navigation.class)
public class NaviagationImpl implements Navigation {
    private static final Logger LOG= LoggerFactory.getLogger(NaviagationImpl.class);


    @OSGiService
    private ResourceResolverFactory resourceResolverFactory;

    @ValueMapValue
    private String parentpath;
    // getting the value of path


    List<PathDetail> MultifieldList = new ArrayList<PathDetail>();


    @Override
    public List<PathDetail> getChildPages() {
        LOG.info("inside the childpages");

        LOG.info("Parent path is {}",parentpath);


        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);

            PageManager pageManager=resourceResolver.adaptTo(PageManager.class);

            Page page=pageManager.getPage(parentpath);
            // providing path to the page manager to get the paerticular page resource

            Iterator<Page> pages=page.listChildren();
            //iterating the page into list of pages for getting the path.
            Iterator<Page> pagepaths=page.listChildren();
            //iterating the page into list of pages for getting the title.



            
            while(pages.hasNext() && pagepaths.hasNext()){
                PathDetail pathObject = new PathDetail();


                pathObject.setTitle(pages.next().getTitle()); 
                LOG.info("Title of the page"+pathObject.getTitle());

            

                pathObject.setPagepath(pagepaths.next().getPath());
                LOG.info("path of the page"+pathObject.getPagepath());


                MultifieldList.add(pathObject);
                



            }
            return MultifieldList;
            

        } catch (LoginException e) {
            e.printStackTrace();
        }

        


        return null;
    }


 
    
}
