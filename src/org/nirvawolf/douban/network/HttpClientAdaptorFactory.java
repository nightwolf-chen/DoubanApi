/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.nirvawolf.douban.network;

/**
 *
 * @author bruce
 */
public class HttpClientAdaptorFactory {
    public static HttpClientAdaptor createDefaultHttpClientAdaptor(String connectionEncode){
        return new HttpClientAdaptor(connectionEncode);
    }
    
     public static HttpClientAdaptor createDefaultHttpClientAdaptor(){
         return new HttpClientAdaptor();
    }
}
