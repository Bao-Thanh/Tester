package group5.tester.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group5.tester.model.Account;
import group5.tester.model.AccountDetailsImpl;
import group5.tester.repository.AccountRepository;
@Service
public class AccountDetailsServiceImpl implements UserDetailsService{
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	@Transactional
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   Account account = accountRepository.findByUsername(username)
	        .orElseThrow(() -> new UsernameNotFoundException("Account Not Found with username: " + username));
	
	   return AccountDetailsImpl.build(account);
	 }
}
