package com.united.core.core.models.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.united.core.core.models.Employee;
import com.united.core.core.service.Service1;

@Model(adaptables = Resource.class, adapters = Employee.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EmployeeImpl implements Employee {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeImpl.class);

    @Inject
    @Required
    public String empid;

    @Inject
    public String name;

    @Inject
    public String surname;

    @Inject
    @Named("jcr:lastModifiedBy")
    String modifiedBy;

    @ValueMapValue
    @Default(values = "Male")
    public String gender;

    @Inject
    public String contactNo;

    @ValueMapValue
    public String country;

    @Inject
    public String address;

    @ResourcePath(path = "/content/united-strangers/us/en")
    Resource resource;

    @SlingObject
    Resource componentResource;

    @Self
    private Resource newresource;

    @OSGiService(filter = "(component.name=serviceF)")
    Service1 service;
    @OSGiService(filter = "(component.name=serviceS)")
    Service1 service2;

    @Override
    public String getEmpId() {
        LOG.debug("Employee Id = {} ", empid);
        return empid;
    }

    @Override
    public String getName() {
        LOG.debug("Name = {} ", name);
        return name;
    }

    @Override
    public String getSurname() {
        LOG.debug("Surname = {} ", surname);
        return StringUtils.reverse(surname);
    }

    @Override
    public String getGender() {
        LOG.debug("Gender = {} ", gender);
        return gender;
    }

    @Override
    public String getContactNo() {
        LOG.debug("Contact Details = {} ", contactNo);
        return contactNo;
    }

    @Override
    public String getCountry() {
        LOG.debug("Country = ", country);
        return country;
    }

    @Override
    public String getAddress() {
        LOG.debug("Address = {} ", address);
        return address;
    }

    @Override
    public String getHomePageName() {
        LOG.debug("Home page Name = {} ", resource.getName());
        return resource.getName();
    }

    @Override
    public String getLastModifiedBy() {
        LOG.debug("from @named property LastModifiedBy = {} ", modifiedBy);
        return modifiedBy;
    }

    @Override

    public List<Map<String, String>> getProductDetailsWithMap() {
        List<Map<String, String>> product = new ArrayList<>();
        try {
            Resource productDetail = componentResource.getChild("product");
            if (productDetail != null) {
                for (Resource item : productDetail.getChildren()) {
                    Map<String, String> productMap = new HashMap<>();
                    productMap.put("text", item.getValueMap().get("text", String.class));
                    productMap.put("rate", item.getValueMap().get("rate", String.class));

                    product.add(productMap);
                }
            }
        } catch (Exception e) {
            LOG.info("\n ERROR while getting Book Details {} ", e.getMessage());
        }
        LOG.info("\n SIZE {} ", product.size());
        return product;
    }

    @Override
    public String getTitle() {
        ValueMap valueMapdemo = newresource.adaptTo(ValueMap.class);
        LOG.info("Title = {} ", address);
        return valueMapdemo.get("country", String.class);
    }

    @PostConstruct
    protected void init() {
        LOG.info("INSIDE THE INIT METHOD");

    }

    @Override
    public String getReversestringA() {

        return service.reverseString(name);
    }

    @Override
    public String getReversestringB() {

        return service2.reverseString(surname);
    }
}
