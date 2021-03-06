/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import org.nirvawolf.douban.api.user.User;
import org.nirvawolf.douban.api.user.UserAuthentication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author bruce
 */
@RunWith(JUnit4.class)

public class UserAuthTest {

    @Test
    public void testValidUserTestAuth() {

        User user = new User();
       

        UserAuthDelegateTest testDelegate = new UserAuthDelegateTest();
        UserAuthentication auth = new UserAuthentication(user, testDelegate);
        auth.attemptToAuth();
        
       
    }
    
    @Test
    public void testInvalidUserTestAuth(){
        User user = new User();
      

        UserAuthDelegateTest testDelegate = new UserAuthDelegateTest();
        UserAuthentication auth = new UserAuthentication(user, testDelegate);
        auth.attemptToAuth();
    }
    
}
