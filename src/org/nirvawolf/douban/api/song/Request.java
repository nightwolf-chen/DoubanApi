/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nirvawolf.douban.api.song;

import org.nirvawolf.douban.util.GlobleVarables;

/**
 *
 * @author bruce
 */
public abstract class Request {

    private final String appName = "radio_desktop_win";
    private final String version = "100";
    protected String apiAddress = GlobleVarables.apiProtocool
            + "://"
            + GlobleVarables.apiDomainName
            + "/j/app/radio/people";
    
    protected RequestDelegate delegate;

    public Request(RequestDelegate delegate) {
        apiAddress = apiAddress + "?app_name=" + appName + "&version=" + version;
        this.delegate = delegate;
    }

    abstract public void attemptToRequest();
}
