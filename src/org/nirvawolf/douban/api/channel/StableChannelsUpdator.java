/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nirvawolf.douban.api.channel;

import java.io.Serializable;
import org.nirvawolf.douban.concurrent.ExecutorServiceManager;
import org.nirvawolf.douban.network.HttpClientAdaptor;
import org.nirvawolf.douban.network.HttpClientAdaptorFactory;
import org.nirvawolf.douban.util.GlobleVarables;

/**
 *
 * @author bruce
 */
public class StableChannelsUpdator extends ChannelUpdator implements Serializable{

    private String apiAddress;

    public StableChannelsUpdator(ChannelUpdatorDelegate delegate) {
        super(delegate);
        this.apiAddress = GlobleVarables.apiProtocool + "://" + GlobleVarables.apiDomainName + "/j/app/radio/channels";
    }

    @Override
    public void attemptToUpdate() {

        ExecutorServiceManager.defaultExecutor.execute(new Runnable() {

            @Override
            public void run() {
                
                HttpClientAdaptor httpAdaptor = HttpClientAdaptorFactory.createDefaultHttpClientAdaptor();
                String respStr = httpAdaptor.doGet(apiAddress);
                httpAdaptor.close();

                ChannelsJSONPaser jsonPaser = new StableChannelsJSONPaser(respStr);
                ChannelUpdateResult result = new ChannelUpdateResult(jsonPaser.paser());
                delegate.didRecieveLatestChannelRecords(result);
                
            }
            
        });

    }

}
