package org.iainuk.aop;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class AccountDAO {

    private String accountName;
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        System.out.println(getClass() + " ... Adding an account ...\n");
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        System.out.println(getClass() + " ... Getting all accounts!\n");
        return accounts;
    }
}
