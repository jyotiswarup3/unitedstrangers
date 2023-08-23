package com.united.core.core.models.Impl;


import java.util.ArrayList;
import java.util.List;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.united.core.core.helper.Movie;
import com.united.core.core.helper.Person;
import com.united.core.core.models.NestedMultifield;



@Model(adaptables = Resource.class , adapters = NestedMultifield.class)
public class NestedMultifieldImpl implements NestedMultifield {


    private static final Logger LOG = LoggerFactory.getLogger(NestedMultifieldImpl.class);

    @SlingObject
    Resource componentResource;

    @Override
    public List<Person> getNestedField() {
        List<Person> DetailsNasted=new ArrayList<>();
        try {
            
            Resource DetailNasted=componentResource.getChild("multifield");
            // getting the resource inside a multifield resource

            if(DetailNasted!=null){
                for (Resource Nasted : DetailNasted.getChildren()) {
                    
                    Person multifieldHelper=new Person(Nasted);
                    // calling a simple pojo class named person

                    if(Nasted.hasChildren()){
                        List<Movie> NastedList=new ArrayList<>();
                        // creating a list of movie class named movie
                        
                        Resource nastedResource=Nasted.getChild("nestedmultifield");
                        // getting the resource inside a nestedmultifield resource


                        for(Resource nasted : nastedResource.getChildren()){
                            NastedList.add(new Movie(nasted));
                        }
                        multifieldHelper.setDetails(NastedList);
                    }
                    DetailsNasted.add(multifieldHelper);
                }
            }
        }catch (Exception e){
            LOG.info("\n ERROR while getting file Details With Nasted Multifield {} ",e.getMessage());
        }
        LOG.info("\n SIZE Multifield {} ",DetailsNasted.size());
        return DetailsNasted;
    }

}