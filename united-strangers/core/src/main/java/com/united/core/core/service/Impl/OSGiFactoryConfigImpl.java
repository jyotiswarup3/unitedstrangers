package com.united.core.core.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;

import com.united.core.core.config.OSGiFactory;
import com.united.core.core.service.OSGiFactoryConfig;

@Component(service = OSGiFactoryConfig.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = OSGiFactory.class, factory = true)
public class OSGiFactoryConfigImpl implements OSGiFactoryConfig {

    private int configID;
    private String servicesName;
    private String servicesURL;
    private List<OSGiFactoryConfig> configsList;

    @Activate
    @Modified
    protected void activate(final OSGiFactory config) {
        configID = config.configID();
        servicesName = config.servicesName();
        servicesURL = config.servicesURL();
    }

    @Reference(service = OSGiFactoryConfig.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindOSGiFactoryConfig(final OSGiFactoryConfig config) {
        if (configsList == null) {
            configsList = new ArrayList<>();
        }
        configsList.add(config);

    }

    public void unbindOSGiFactoryConfig(final OSGiFactoryConfig config) {
        configsList.remove(config);
    }

    @Override
    public int getConfigID() {
        return configID;
    }

    @Override
    public String getServicesName() {
        return servicesName;
    }

    @Override
    public String getServicesURL() {
        return servicesURL;
    }

    @Override
    public List<OSGiFactoryConfig> getAllConfigs() {
        return configsList;
    }

    @Override
    public OSGiFactoryConfig get(int configID) {
        for (OSGiFactoryConfig confFact : configsList) {
            if (configID == confFact.getConfigID())
                return confFact;
        }
        return null;
    }

}
