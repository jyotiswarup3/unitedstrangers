package com.united.core.core.service.Impl;

import org.osgi.service.component.annotations.Component;

import com.united.core.core.service.MultiService;

@Component(service = MultiService.class, immediate = true, name = "serviceA")
public class MultiseviceAImpl implements MultiService {

    @Override
    public double operation(double p, double r, double t) {

        return ((p * r * t) / 100);
    }

}
