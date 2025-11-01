package b.b.book.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import b.b.book.models.AppUser;
import b.b.book.repos.UserRepo;

//Constructor
//public class UserService {
//  private final UserRepo userRepo;
//  public  UserService(UserRepo userRepo){
//      this.userRepo = userRepo;
//  }
//}
@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    Optional<AppUser> user = userRepo.findByUsername(username);
    if (user.isPresent()) {
      var userObj = user.get();
      return User.builder()
            .username(userObj.getUsername())
            .password(userObj.getPassword())
            .build();
    } else {
      throw new UsernameNotFoundException("No User with that name");
    }
  
  }

}
