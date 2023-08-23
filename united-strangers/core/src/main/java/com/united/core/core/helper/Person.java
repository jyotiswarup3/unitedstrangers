package com.united.core.core.helper;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Person {
    private static final Logger LOG = LoggerFactory.getLogger(Person.class);
    private String fieldName;
    private List<Movie> movieDetails;
    public Person(Resource resource){
        try {
            if(StringUtils.isNotBlank(resource.getValueMap().get("fieldname", String.class))) {
                this.fieldName = resource.getValueMap().get("fieldname", String.class);
            }
           
        }catch (Exception e){
            LOG.info("\n BEAN ERROR : {}",e.getMessage());
        }

    }

    public String getFieldName() {
        return fieldName;
    }

    public List<Movie> getMovieDetails() {
        return movieDetails;
    }

    public void setDetails(List<Movie> movieDetails) {
        this.movieDetails = movieDetails;
    }
}