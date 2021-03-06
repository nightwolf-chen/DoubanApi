/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nirvawolf.douban.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author bruce
 */
public class HttpClientAdaptor {

    /**
     *
     */
    protected CloseableHttpClient httpclient = HttpClients.createDefault();
    private final HttpClientContext localContext = HttpClientContext.create();
    private final int timeout = 3000;
    private String encode = "utf8";

    /**
     *
     * @param encode
     */
    public HttpClientAdaptor(String encode) {

        CookieStore cookieStore = new BasicCookieStore();
        localContext.setCookieStore(cookieStore);
        this.encode = encode;

    }

    /**
     *
     */
    public HttpClientAdaptor() {

        CookieStore cookieStore = new BasicCookieStore();
        localContext.setCookieStore(cookieStore);

    }

    /**
     *
     * @param url
     * @return
     */
    public String doGet(String url) {

        try {
            
            HttpGet httpGet = new HttpGet(url);
            
            RequestConfig requestConfig = RequestConfig
                    .custom()
                    .setSocketTimeout(timeout)
                    .setConnectTimeout(timeout)
                    .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                    .build();
            httpGet.setConfig(requestConfig);

            CloseableHttpResponse response = this.httpclient.execute(httpGet, localContext);
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), this.encode));
            String htmlStr = null;
            int c = 0;
            StringBuilder temp = new StringBuilder();
            while ((c = br.read()) != -1) {
                temp.append((char) c);
            }
            htmlStr = temp.toString();

            httpGet.releaseConnection();
            httpGet.completed();

            return htmlStr;
        } catch (IOException ex) {
            Logger.getLogger(HttpClientAdaptor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     *
     * @param url
     * @param parameters
     * @return
     */
    public String doPost(String url, List<NameValuePair> parameters) {

        try {

            HttpPost httpPost = new HttpPost(url);
            
            RequestConfig requestConfig = RequestConfig
                    .custom()
                    .setSocketTimeout(timeout)
                    .setConnectTimeout(timeout)
                    .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                    .build();
            
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(new UrlEncodedFormEntity(parameters));

            CloseableHttpResponse response = this.httpclient.execute(httpPost, localContext);
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), this.encode));
            String htmlStr = null;
            int c = 0;
            StringBuilder temp = new StringBuilder();
            while ((c = br.read()) != -1) {
                temp.append((char) c);
            }
            htmlStr = temp.toString();

            httpPost.completed();
            httpPost.releaseConnection();

            return htmlStr;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HttpClientAdaptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HttpClientAdaptor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     *
     */
    public void close(){
        try {
            this.httpclient.close();
        } catch (IOException ex) {
            Logger.getLogger(HttpClientAdaptor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
