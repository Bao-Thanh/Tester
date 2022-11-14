package group5.tester.service;

import java.util.List;

import group5.tester.model.Account;

public interface AccountService {

	//User

	List<Account> getAllAccounts();

	Account getAccountById(long id);

	Account updateAccount(Account account, long id);

	void deleteAccount(long id);
}
