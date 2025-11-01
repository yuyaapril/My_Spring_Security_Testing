package b.b.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import b.b.book.models.AppUser;
import b.b.book.repos.UserRepo;




@Controller
public class PageController {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @GetMapping("/login")
  public String login() {
    return "login";
  }
    @GetMapping("/register")
  public String register() {
      return "register";
  }
   @GetMapping("/")
   public String home() {
     return "home";
   }

   @PostMapping("/register")
   public String createUser(
                            @RequestParam String username,
                            @RequestParam String email,
                            @RequestParam String password
   ) {
     System.out.println("Username : " + username);
     System.out.println("Email : " + email);
     System.out.println("Password : " + password);

     AppUser user = new AppUser();
     user.setUsername(username);
     user.setEmail(email);
     user.setPassword(password);

     userRepo.save(user);

     return "redirect:/login";
   } 
}