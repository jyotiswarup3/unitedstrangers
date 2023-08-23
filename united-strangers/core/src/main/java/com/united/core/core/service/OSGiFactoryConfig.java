package com.united.core.core.service;

import java.util.List;

public interface OSGiFactoryConfig {
    public int getConfigID();

    public String getServicesName();

    public String getServicesURL();

    public OSGiFactoryConfig get(int configID);

    public List<OSGiFactoryConfig> getAllConfigs();
}
