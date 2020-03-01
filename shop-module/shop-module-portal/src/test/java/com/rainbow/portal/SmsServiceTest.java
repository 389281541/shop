package com.rainbow.portal;


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



    @Test
    public void testSendMsg() {
    }

}
