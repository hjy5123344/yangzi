package com.hjy.yyyeurekaclient.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private AsyncService asyncService;

    @GetMapping("hello")
    public String hello() {
        logger.info("start submit");

        //调用service层的任务
        asyncService.executeAsync();

        logger.info("end submit");
        return "success";
    }
}
