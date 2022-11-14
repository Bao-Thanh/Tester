package group5.tester.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccountDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;
  private static final String ROLE_ADMIN = "ROLE_ADMIN";
  private static final String ROLE_USER = "ROLE_USER";
  private Long id;

  private String username;

//  private String email;

  @JsonIgnore
  private String password;

  private String rrole;
  private Collection<? extends GrantedAuthority> authorities;

  public AccountDetailsImpl(Long id, String username, /*String email,*/ String password,
                            Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  public static AccountDetailsImpl build(Account account) {
	List<String> role = new ArrayList<String>();
	
	if(account.getRole().equals("admin"))
		role.add(ROLE_ADMIN);
	else role.add(ROLE_USER);
    List<GrantedAuthority> authorities = role.stream()
        .map(item -> new SimpleGrantedAuthority(item))
        .collect(Collectors.toList());

    return new AccountDetailsImpl(
        account.getId(),
        account.getUsername(),
        account.getPassword(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

//  public String getEmail() {
//    return email;
//  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    AccountDetailsImpl user = (AccountDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
