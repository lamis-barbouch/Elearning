package tn.esprit.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import tn.esprit.spring.entity.SmsRequest;
import tn.esprit.spring.entity.SmsSender;

@org.springframework.stereotype.Service
public class SmsService {

    private final SmsSender smsSender;

    @Autowired
    public SmsService(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
}