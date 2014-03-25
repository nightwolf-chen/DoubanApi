/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import api.user.AuthResponse;
import api.user.UserAuthenticationDelegate;

/**
 *
 * @author bruce
 */
public class UserAuthDelegateTest implements UserAuthenticationDelegate{
    
    public AuthResponse resp ;
    public boolean recievedData = false;
    
    @Override
    public void didRecieveLoginResponse(AuthResponse authResp) {
        
        this.resp = authResp;
        
        org.junit.Assert.assertTrue("failure - login result should be true",resp.isSuccessLogin());
    }
}
