package com.gmail.antonsyzko.doctoradministrationpanel.controller;

import com.gmail.antonsyzko.doctoradministrationpanel.model.User;
import com.gmail.antonsyzko.doctoradministrationpanel.model.UserProfile;
import com.gmail.antonsyzko.doctoradministrationpanel.service.UserDocumentService;
import com.gmail.antonsyzko.doctoradministrationpanel.service.UserProfileService;
import com.gmail.antonsyzko.doctoradministrationpanel.service.UserService;
import com.gmail.antonsyzko.doctoradministrationpanel.utils.EmailUtil;
import com.gmail.antonsyzko.doctoradministrationpanel.utils.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.Properties;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());

		final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");

		if (runnable != null) runnable.run();
		return "userslist";
	}


	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());

		return "registration";
	}


	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}

             sendEmail(user);

		userService.saveUser(user);

		model.addAttribute("success", "User  " + user.getFirstName() + "  "+ user.getLastName() + " You have successfully registered.");
		model.addAttribute("email_confirmation"," Confirmation email has  been sent to :");
		model.addAttribute("very_email",user.getEmail());
		model.addAttribute("close_mail"," Please, check Your email. ");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}


     public void sendEmail(User user){
		 final String fromEmail = "antonsyzko@gmail.com";
		 final String password = "uspehsomnoi";
		 System.out.println("SSLEmail Start");
		 Properties props = new Properties();
		 props.put("mail.smtp.host", "smtp.gmail.com");
		 props.put("mail.smtp.socketFactory.port", "465");
		 props.put("mail.smtp.socketFactory.class",
				 "javax.net.ssl.SSLSocketFactory");
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.port", "465");

		 Authenticator auth = new Authenticator() {
			 protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication(fromEmail, password);
			 }
		 };
		 Session session = Session.getDefaultInstance(props, auth);
		 System.out.println("Session created");
		 EmailUtil.sendImageEmail(session, user.getEmail(), "Welcome to Private Doctor Administration  Panel",
				 "  Welcome to  Private Doctor Administration  Panel, You have succesfully registered . Your  credentials are :\r\n" +
						 "  Name :   "+user.getFirstName() + "\r\n  Surname :   " + user.getLastName()+ "\r\n  registration email :   "+ user.getEmail() + "\r\n  phone  "+user.getPhone()+
						 "\r\n  LOGIN (to be  used enter the Adminstration Page ) :   " +  user.getSsoId() +"\r\n  PASSWORD  ( to be  used to enter the Administration page ) :   " +
						 user.getPassword() + " \r\n  Thank Your for  joining , we will take care  of Your health ! \r\n  call us +(351) 916 111 042 \r\n  or visit us "+
						 "    Rua Capitaes  de Abril 13 - 4 ESQ \r\n  Alfornelos Lisbon\r\n  2650 - 352 \r\n  Portugal ")  ;


	 }

	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}


	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}

		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}

	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}

	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}


	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {


			return "login";
	    } else {
	    	return "redirect:/list";
	    }
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}

	@Autowired
	UserDocumentService userDocumentService;

	@Autowired
	FileValidator fileValidator;

	@InitBinder("fileBucket")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

}