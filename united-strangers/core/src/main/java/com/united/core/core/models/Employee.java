package com.united.core.core.models;

import java.util.List;
import java.util.Map;

public interface Employee {
    public String getEmpId();

    public String getName();

    public String getSurname();

    public String getGender();

    public String getContactNo();

    public String getCountry();

    public String getAddress();

    public String getHomePageName();

    public String getLastModifiedBy();

    public List<Map<String, String>> getProductDetailsWithMap();

    public String getTitle();

    public String getReversestringA();

    public String getReversestringB();
}
