package com.united.core.core.models;

import java.util.Iterator;
import java.util.List;

import com.day.cq.wcm.api.Page;

public interface ResourceRequest {
    String getPlayerName();

    String getPlayerScore();

    String getBallsPlayed();

    String getStrikeRate();

    public String getRequestAttribute();

    public Iterator<Page> getPagesList();

    public List<String> getBooks();

    public String getPageTitle();

    public String getId();

    public String getReversestringA();

    public String getReversestringB();

    public String getLocation();
}
