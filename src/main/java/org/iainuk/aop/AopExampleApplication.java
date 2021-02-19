package org.iainuk.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AopExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AopExampleApplication.class, args);
        AccountDAO test = ctx.getBean("accountDAO", AccountDAO.class);
        test.addAccount(new Account("Iain"));
        test.getAccountName();
        test.getAccounts();

    }

}
