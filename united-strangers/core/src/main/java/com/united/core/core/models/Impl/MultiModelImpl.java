package com.united.core.core.models.Impl;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.united.core.core.models.MultiModel;
import com.united.core.core.service.MultiService;
import com.united.core.core.service.Service1;

@Model(adaptables = SlingHttpServletRequest.class, adapters = MultiModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiModelImpl implements MultiModel {
    private static final Logger LOG = LoggerFactory.getLogger(MultiModelImpl.class);
    @Inject
    String name;

    @Inject
    double principle;

    @Inject
    double rate;

    @Inject
    double time;

    @ValueMapValue
    String surname;

    @OSGiService(filter = "(component.name=serviceF)")
    Service1 service;
    @OSGiService(filter = "(component.name=serviceS)")
    Service1 service2;

    @OSGiService(filter = "(component.name=serviceA)")
    MultiService multiServiceA;

    @OSGiService(filter = "(component.name=serviceB)")
    MultiService multiServiceB;

    @Override
    public String getReversestringA() {

        LOG.debug("Reverse string from service A = {} ", service.reverseString(name));
        
        return service.reverseString(name);
    }

    @Override
    public String getReversestringB() {
        LOG.debug("Reverse string from service B = {} ", service2.reverseString(name));

        return service2.reverseString(surname);
    }

    @Override
    public double getInterest() {
        LOG.debug("Simple Interest = {} ", multiServiceA.operation(principle, rate, time));

        return multiServiceA.operation(principle, rate, time);
    }

    @Override
    public double getCompoundInterest() {
        LOG.debug("Compound Interest = {} ", multiServiceA.operation(principle, rate, time));

        return multiServiceB.operation(principle, rate, time);
    }

}
