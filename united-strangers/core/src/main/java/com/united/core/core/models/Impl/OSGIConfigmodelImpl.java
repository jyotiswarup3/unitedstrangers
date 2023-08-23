package com.united.core.core.models.Impl;

import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.united.core.core.models.OSGIConfigmodel;
import com.united.core.core.service.OSGiConfig;
import com.united.core.core.service.OSGiConfigServiceModule;
import com.united.core.core.service.OSGiFactoryConfig;
import com.united.core.core.service.Practice;

@Model(adaptables = Resource.class, adapters = OSGIConfigmodel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGIConfigmodelImpl implements OSGIConfigmodel {
    private static final Logger LOG = LoggerFactory.getLogger(OSGIConfigmodelImpl.class);
    @OSGiService
    OSGiConfig osGiconfig;

    @OSGiService
    Practice practice;

    @OSGiService
    OSGiConfigServiceModule osGiConfigServiceModule;

    @OSGiService
    OSGiFactoryConfig oSGiFactoryConfig;

    @Override
    public String getServiceName() {
        LOG.info("ServiceName:{} ", osGiconfig.getServiceName());
        return osGiconfig.getServiceName();
    }

    @Override
    public int getServiceCount() {
        LOG.info("ServiceCount:{} ", osGiconfig.getServiceCount());
        return osGiconfig.getServiceCount();
    }

    @Override
    public boolean isLiveData() {
        LOG.info("LiveData:{} ", osGiconfig.isLiveData());
        return osGiconfig.isLiveData();
    }

    @Override
    public String[] getCountries() {

        return osGiconfig.getCountries();
    }

    @Override
    public String getRunModes() {
        LOG.info("RunModes: {} ", osGiconfig.getRunModes());
        return osGiconfig.getRunModes();
    }

    // Modular OSGi Configuration

    @Override
    public int getServiceId() {
        LOG.debug("ServiceId: {} ", osGiConfigServiceModule.getServiceId());
        return osGiConfigServiceModule.getServiceId();
    }

    @Override
    public String getServiceNameModule() {
        LOG.debug("ServiceNameModule: {} ", osGiConfigServiceModule.getServiceName());
        return osGiConfigServiceModule.getServiceName();
    }

    @Override
    public String getServiceURL() {
        LOG.debug("ServiceURL: {} ", osGiConfigServiceModule.getServiceURL());
        return osGiConfigServiceModule.getServiceURL();
    }

    // FactoryConfig

    @Override
    public List<OSGiFactoryConfig> getAllOSGiConfigs() {
        LOG.info("Start of the method");
        LOG.debug("LIST OF CONFIGURATION : {} ", oSGiFactoryConfig.getAllConfigs());
        return oSGiFactoryConfig.getAllConfigs();
    }

    @Override
    public Iterator<Page> getPagesList() {
        LOG.info("Start of the method");
        LOG.debug("LIST OF PAGES : {} ", practice.getPages());
        return practice.getPages();

    }
}
