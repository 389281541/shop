package com.rainbow.portal;


import com.rainbow.portal.mq.CancelOrderSender;
import com.rainbow.portal.service.ISmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopPortalApplication.class)
@ActiveProfiles("local")
public class SmsServiceTest {

    @Autowired
    private ISmsService smsService;

    @Autowired
    private CancelOrderSender cancelOrderSender;



    @Test
    public void testSendMsg() {
//        cancelOrderSender.sendMessage(18L,2000L);
//        System.out.println("123");
    }

}
