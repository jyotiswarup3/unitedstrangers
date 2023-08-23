package com.united.core.core.models;

import java.util.Iterator;
import java.util.List;

import com.day.cq.wcm.api.Page;
import com.united.core.core.service.OSGiFactoryConfig;

public interface OSGIConfigmodel {
    public String getServiceName();

    public int getServiceCount();

    public boolean isLiveData();

    public String[] getCountries();

    public String getRunModes();

    public int getServiceId();

    public String getServiceNameModule();

    public String getServiceURL();

    public List<OSGiFactoryConfig> getAllOSGiConfigs();

    public Iterator<Page> getPagesList();
}
