/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.user;

import concurrent.ExecutorServiceManager;
import java.util.ArrayList;
import java.util.List;
import network.HttpClientAdaptor;
import network.HttpClientAdaptorFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import util.GlobleVarables;

/**
 *
 * @author bruce
 */
public class UserAuthentication {

    private BasicUser user;
    private String authAdrress;
    UserAuthenticationDelegate delegate;

    public UserAuthentication(BasicUser user, UserAuthenticationDelegate delegate) {
        this.user = user;
        this.delegate = delegate;
        this.authAdrress = GlobleVarables.apiProtocool + "://" + GlobleVarables.apiDomainName + "/j/app/login";
    }

    public void attemptToAuth() {
        
         ExecutorServiceManager.defaultExecutor.execute(new Runnable() {
             @Override
             public void run() {
                 doAuth();
             }
         });
         
    }

    private void doAuth() {
        HttpClientAdaptor clientAdaptor = HttpClientAdaptorFactory.createDefaultHttpClientAdaptor();
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("email", this.user.email));
        parameters.add(new BasicNameValuePair("password", this.user.password));
        parameters.add(new BasicNameValuePair("version", "100"));
        parameters.add(new BasicNameValuePair("app_name", "radio_desktop_win"));
        String respJsonStr = clientAdaptor.doPost(this.authAdrress, parameters);
        clientAdaptor.close();
        this.delegate.didRecieveLoginResponse(new AuthResponse(respJsonStr));
    }
}