package com.united.core.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="United Strangers-Modular OSGi Configuration",
     description = "Modular OSGi Configuration .")
public @interface OSGiConfigModule {
    @AttributeDefinition(
        name = "Service ID",
        description = "Enter service id.",
        type = AttributeType.INTEGER)
int serviceID();

@AttributeDefinition(
        name = "Service Name",
        description = "Enter service name.",
        type = AttributeType.STRING)
public String serviceName() default "United Strangers Service";

@AttributeDefinition(
        name = "Service URL",
        description = "Add Service URL.",
        type = AttributeType.STRING
)
String serviceURL() default "localhost";
    
}
