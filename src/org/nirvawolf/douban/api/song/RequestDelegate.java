/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.nirvawolf.douban.api.song;

/**
 *
 * @author bruce
 */
public interface RequestDelegate {
    
    public void didRecieveResponse(RequestResponse response);
        
}
