/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.user;

/**
 *
 * @author bruce
 */
public class UserAuthTest implements UserAuthenticationDelegate {

    @Override
    public void didRecieveLoginResponse(AuthResponse authResp) {
        if (authResp.isSuccessLogin()) {
            System.out.println("Successfully login:" + authResp.getEmail() + "|" + authResp.getUsername());
        } else {
            System.out.println("Login failed with:" + authResp.getErrorDescription());
        }
    }

    public void doTestAuth() {

        BasicUser user = new BasicUser();
        user.email = "466202783@qq.com";
        user.password = "a13827970772b";

        UserAuthentication auth = new UserAuthentication(user, this);
        auth.attemptToAuth();
    }

    public static void main(String[] args) {

       
        UserAuthTest test = new UserAuthTest();
        test.doTestAuth();


    }
}
