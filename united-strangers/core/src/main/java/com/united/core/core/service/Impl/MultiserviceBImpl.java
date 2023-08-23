package com.united.core.core.service.Impl;

import org.osgi.service.component.annotations.Component;

import com.united.core.core.service.MultiService;

@Component(service = MultiService.class, immediate = true, name = "serviceB")

public class MultiserviceBImpl implements MultiService {

    @Override
    public double operation(double p, double r, double t) {

        return (p * Math.pow(1.0 + r / 100.0, t) - p);
    }

}
