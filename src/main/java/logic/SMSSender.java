/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author hecto
 */
public class SMSSender {
    public static final String ACCOUNT_SID = "AC0eb9609a86a182563fcb3269e9183391";
    public static final String AUTH_TOKEN = "8e4876d96cf1fa1b1e806c48a8ce5f81";
    
    public void enviarSMS(String text){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("+50688692848"), 
                new PhoneNumber("+14157022035"),
                text).create();
    }
}
