package net.codejava;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.codejava.service.UserBankAccountDetailServiceImpl;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserBankAccountDetailServiceImpl accountDetailService;
	
	
	@GetMapping("")
	public String viewHomePage() {
		return "/index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}
	
	
	
	
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	
		boolean status = accountDetailService.addNewCustomerWithJoiningBonus(user);
		if(status)
		{
			return "register_success";
		}
		else
		{
			return "errorPage";
		}
		
		
		
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User user = userRepo.findByEmail(username);
		
		
		
		System.out.println("username is"+username);
		List<User> listUsers = new ArrayList<User>();
		listUsers.add(user);
		//userRepo.findAll();
		
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("ListUserBankAccountDetaile", accountDetailService.getBankAccountDeatilsByUserId(user));
		return "users";
	}
}
