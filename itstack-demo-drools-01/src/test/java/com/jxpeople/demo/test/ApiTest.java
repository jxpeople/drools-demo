package com.jxpeople.demo.test;

import com.jxpeople.demo.model.User;
import com.jxpeople.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class ApiTest {

    private KieContainer kieContainer;

    @Before
    public void init() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.getKieClasspathContainer();
    }

    @Test
    public void doRun() {
        KieSession kieSession = kieContainer.newKieSession("all-rules");

        User user = new User();
        user.setSex("男");
        user.setAge(30);

        kieSession.insert(user);
        UserService userService = new UserService();
        kieSession.setGlobal("userService", userService);
        int count = kieSession.fireAllRules();

        System.out.println("Fire rule(s)：" + count);
        System.out.println("Fire result：" + userService.result());

        kieSession.dispose();
    }

}
