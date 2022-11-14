package group5.tester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group5.tester.model.Account;
import group5.tester.service.AccountService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountService accSer;

	@GetMapping("/viewall")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Account> getAllUsers(){
		return accSer.getAllAccounts();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Account> getUserById(@PathVariable("id") long accId){
		return new ResponseEntity<Account> (accSer.getAccountById(accId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Account> updateUser(@PathVariable("id") long accId,
			@RequestBody Account account){
		return new ResponseEntity<Account> (accSer.updateAccount(account, accId), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long accId){
		accSer.deleteAccount(accId);
		return new ResponseEntity<String> ("Delete User successfully!.", HttpStatus.OK);
	}
}
