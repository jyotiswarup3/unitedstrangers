package com.united.core.core.service.Impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.united.core.core.config.OSGiConfigModule;
import com.united.core.core.service.OSGiConfigServiceModule;

@Component(service = OSGiConfigServiceModule.class, immediate = true)
@Designate(ocd = OSGiConfigModule.class)
public class OSGiConfigServiceModuleImpl implements OSGiConfigServiceModule {

    private int serviceId;
    private String serviceName;
    private String serviceURL;

    @Activate
    protected void activate(OSGiConfigModule osGiConfigModule) {
        serviceId = osGiConfigModule.serviceID();
        serviceName = osGiConfigModule.serviceName();
        serviceURL = osGiConfigModule.serviceURL();
    }

    @Override
    public int getServiceId() {
        return serviceId;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getServiceURL() {
        return serviceURL;
    }

}
