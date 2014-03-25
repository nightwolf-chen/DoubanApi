/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.user;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author bruce
 */
public class AuthResponse {

    private String userid;
    private String errorDescription;
    private String token;
    private int result;
    private String username;
    private String email;
    private String expire;

    public AuthResponse(String jsonResp) {
        try {

            JSONObject respObj = new JSONObject(jsonResp);
            
            this.result = respObj.getInt("r");
            this.errorDescription = respObj.getString("err");
            this.errorDescription = this.errorDescription.replaceAll("_", " ");
            
            if (respObj.getInt("r") == 0) { 
                this.email = respObj.getString("email");
                this.expire = respObj.getString("expire");
                this.token = respObj.getString("token");
                this.userid = respObj.getString("user_id");
                this.username = respObj.getString("user_name");
            } 
            
        } catch (JSONException ex) {
            Logger.getLogger(AuthResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUserid() {
        return userid;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isSuccessLogin() {
        
        if(result == 0){
            return true;
        }else{
            return false;
        }
    }

}
