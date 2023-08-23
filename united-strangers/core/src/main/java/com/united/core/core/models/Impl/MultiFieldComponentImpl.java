package com.united.core.core.models.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.united.core.core.helper.ImageDetail;
import com.united.core.core.models.MultiFieldComponent;

@Model(adaptables=Resource.class, adapters = MultiFieldComponent.class)
public class MultiFieldComponentImpl implements MultiFieldComponent {

    
    private static final Logger LOG = LoggerFactory.getLogger(MultiFieldComponentImpl.class);

    public List<ImageDetail> MultifieldList = new ArrayList<ImageDetail>();

    @Inject
    private List<Resource> multifield;    
    /* INJECTING MULTIFIELD RESOURCE FROM CUSTOM MULTIFIELD COMPONENT */
    

    @Override
    public List<ImageDetail> getImageDetails() {
    
        for (Resource resources : multifield) {

            Node itemNode = resources.adaptTo(Node.class);

            ImageDetail imageObject = new ImageDetail();
            try {

                
                imageObject.setImagetitle(itemNode.getProperty("imagetitle").getString());
                // getting the property value of imagetitle

                imageObject.setImagepath(itemNode.getProperty("image").getString());

                // getting the property value of image

                LOG.info("title of the image {0} ",imageObject.getImagetitle());
                LOG.info("path of the image {0}",imageObject.getImagepath());

            } catch (ValueFormatException e) {
                e.printStackTrace();
            } catch (PathNotFoundException e) {
                e.printStackTrace();
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            MultifieldList.add(imageObject);
            


        }

        LOG.info("MultiField list is: "+MultifieldList);
        return MultifieldList;
        


    }
}
