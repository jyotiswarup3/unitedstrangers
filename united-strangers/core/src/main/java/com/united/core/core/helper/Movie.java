package com.united.core.core.helper;
import org.apache.sling.api.resource.Resource;

public class Movie {
    private String imageName;
    public Movie(Resource resource){
        if(resource.getValueMap().get("imagename", String.class)!=null) {
            this.imageName = resource.getValueMap().get("imagename", String.class);
        }

    }

    public String getImageName() {
        return imageName;
    }


}

