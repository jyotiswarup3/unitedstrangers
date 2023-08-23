package com.united.core.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
@ObjectClassDefinition(name="United Strangers-OSGi Factory Configuration",
     description = "OSGi Factory Configuration .")
public @interface OSGiFactory {
    @AttributeDefinition(
            name = "Config ID",
            description = "Enter service id.",
            type = AttributeType.INTEGER)
    int configID();

    @AttributeDefinition(
            name = "Services Name",
            description = "Enter service name.",
            type = AttributeType.STRING)
    public String servicesName() default "Service #";

    @AttributeDefinition(
            name = "Services URL",
            description = "Add Service URL.",
            type = AttributeType.STRING
    )
    public String servicesURL() default "URL #";
}
