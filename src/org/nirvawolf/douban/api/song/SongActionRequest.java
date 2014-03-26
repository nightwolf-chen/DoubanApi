/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nirvawolf.douban.api.song;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.utils.URIBuilder;
import org.nirvawolf.douban.api.channel.Channel;
import org.nirvawolf.douban.api.user.User;
import org.nirvawolf.douban.concurrent.ExecutorServiceManager;
import org.nirvawolf.douban.network.HttpClientAdaptor;
import org.nirvawolf.douban.network.HttpClientAdaptorFactory;

/**
 *
 * @author bruce
 */
public class SongActionRequest extends Request {

    public SongActionRequest(RequestDelegate delegate, SongActionRequestInfo songRequestInfo) {

        super(delegate);

        if (songRequestInfo.song != null) {
            this.apiAddress = this.appdendSongInfoToAddress(apiAddress, songRequestInfo.song);
        }

        if (songRequestInfo.user != null) {
            this.apiAddress = this.appendUserInfoToAddress(apiAddress, songRequestInfo.user);
        }

        if (songRequestInfo.channel != null) {
            this.apiAddress = this.appendChannelInfoToAddress(apiAddress, songRequestInfo.channel);
        }

        String typeStr = "";
        switch (songRequestInfo.type) {
            case BYE:
                typeStr = "b";
                break;
            case END:
                typeStr = "e";
                break;
            case RATE:
                typeStr = "r";
                break;
            case SKIP:
                typeStr = "s";
                break;
            case UNRATE:
                typeStr = "u";
                break;
        }

        this.apiAddress += "&type=" + typeStr;
    }

    private String appendUserInfoToAddress(String url, User user) {

        try {

            URIBuilder builder = new URIBuilder(this.apiAddress);
            builder.addParameter("user_id", user.userid);
            builder.addParameter("expire", user.expire);
            builder.addParameter("token", user.token);

            url = builder.build().toString();

        } catch (URISyntaxException ex) {
            Logger.getLogger(SongActionRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return url;
    }

    private String appdendSongInfoToAddress(String url, Song song) {

        try {

            URIBuilder builder = new URIBuilder(this.apiAddress);
            builder.addParameter("sid", song.sid);

            url = builder.build().toString();

        } catch (URISyntaxException ex) {
            Logger.getLogger(SongActionRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return url;
    }

    private String appendChannelInfoToAddress(String url, Channel channel) {
        try {

            URIBuilder builder = new URIBuilder(this.apiAddress);
            builder.addParameter("channel", String.valueOf(channel.channel_id));

            url = builder.build().toString();

        } catch (URISyntaxException ex) {
            Logger.getLogger(SongActionRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return url;
    }

    @Override
    public void attemptToRequest() {

        ExecutorServiceManager.defaultExecutor.execute(new Runnable() {
            @Override
            public void run() {

                HttpClientAdaptor clientAdaptor = HttpClientAdaptorFactory.createDefaultHttpClientAdaptor();
                String jsonStr = clientAdaptor.doGet(apiAddress);
                clientAdaptor.close();
                delegate.didRecieveResponse(new RequestResponse(jsonStr));

            }
        });

    }

    public static void main(String[] args) {
        
        RequestDelegate delegate = new RequestDelegate() {

            @Override
            public void didRecieveResponse(RequestResponse response) {
                System.out.print("It's good!("+response.isSuccess()+")\n");
            }
            
        };

        Song song = new Song();
        song.sid = "10086";

        Channel channel = new Channel();
        channel.channel_id = 2;

        SongActionRequestInfo info = new SongActionRequestInfo();
        info.song = song;
        info.channel = channel;
        info.type = SongActionRequestInfo.ActionType.SKIP;

        new SongActionRequest(delegate, info).attemptToRequest();
    }

}
