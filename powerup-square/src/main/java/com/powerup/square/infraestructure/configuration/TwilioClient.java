package com.powerup.square.infraestructure.configuration;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
public class TwilioClient {
    public void sendSMS(String body) {
        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));
        Message.creator(new PhoneNumber("+573014800300"), new PhoneNumber("+15076525564"), body).create();
    }
}
