package com.united.core.core.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;

import com.united.core.core.service.Service1;

@Component(service = Service1.class, immediate = true, name = "serviceF")
public class ServiceVersionA implements Service1 {

    @Override
    public String reverseString(String str) {

        return StringUtils.reverse(str);
    }

}
