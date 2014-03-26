/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nirvawolf.douban.api.user;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author bruce
 */
public class AuthResponse {

    private String errorDescription;
    private int result;
    private User user = new User();
    
    public AuthResponse(String jsonResp) {
        try {

            JSONObject respObj = new JSONObject(jsonResp);
            
            this.result = respObj.getInt("r");
            this.errorDescription = respObj.getString("err");
            this.errorDescription = this.errorDescription.replaceAll("_", " ");
            
            if (respObj.getInt("r") == 0) { 
                this.user.email = respObj.getString("email");
                this.user.expire = respObj.getString("expire");
                this.user.token = respObj.getString("token");
                this.user.userid = respObj.getString("user_id");
                this.user.username = respObj.getString("user_name");
            } 
            
        } catch (JSONException ex) {
            Logger.getLogger(AuthResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isSuccessLogin() {
        
        if(result == 0){
            return true;
        }else{
            return false;
        }
    }

     public String getErrorDescription() {
        return errorDescription;
    }

    public User getUser() {
        return user;
    }

     
}
