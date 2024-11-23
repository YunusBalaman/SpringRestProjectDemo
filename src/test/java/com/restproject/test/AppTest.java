package com.restproject.test;

import com.restproject.TestApplication;
import com.restproject.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestApplication.class})
@ActiveProfiles("test")
public class AppTest {

    Logger logger = LogManager.getLogger(AppTest.class);

    @Autowired(required = true)
    UserService userService;

    @Test
    public void saveProductTest(){

        logger.info(userService.findAll());
        //logger.info(userService.findByMusterino(1732237771657L).get().getMusterino());
        logger.info("test okito");
    }

}
