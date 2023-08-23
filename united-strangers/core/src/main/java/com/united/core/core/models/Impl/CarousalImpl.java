package com.united.core.core.models.Impl;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.united.core.core.models.Carousal;

import java.util.*;


@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = Carousal.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CarousalImpl implements Carousal {
    private static final Logger LOG = LoggerFactory.getLogger(CarousalImpl.class);

    @SlingObject
    Resource componentResource;

    @ValueMapValue
    private String title;


    @Override
    public List<Map<String, String>> getCarouselImageList() {


        List<Map<String, String>> imageDetailsMap=new ArrayList<>();



        try {
            Resource imageDetail=componentResource.getChild("multifield");

                if(imageDetail.hasChildren()){
                    for (Resource image : imageDetail.getChildren()) {
                        LOG.info("\n PATH Bean {} ",image.getPath());

                        Map<String,String> imagemap=new HashMap<>();
                        imagemap.put("image",image.getValueMap().get("image",String.class));

                        LOG.info("image path is {}", image.getValueMap().get("image",String.class));

                        imagemap.put("imagetext",image.getValueMap().get("imagetext",String.class));

                        LOG.info("image path is {}", image.getValueMap().get("imagetext",String.class));


                        imageDetailsMap.add(imagemap);

                }
            }
        }catch (Exception e){
            LOG.info("\n ERROR while getting image Details {} ",e.getMessage());
        }
        LOG.info("\n SIZE {} ",imageDetailsMap.size());
        return imageDetailsMap;
    }








}