package com.hamzah.aeroparkerregistration.controller;

import java.time.LocalDateTime;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hamzah.aeroparkerregistration.model.Customer;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/registration")
public class RegistrationController
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private HttpServletRequest req;
	
	@GetMapping("/register")
	public String register()
	{
		return "registration-form";
	}
	
	@PostMapping("/register")
	public String registerPost(@RequestParam("title") String title,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("addressLine1") String addressLine1,
			@RequestParam("addressLine2") String addressLine2,
			@RequestParam("city") String city,
			@RequestParam("postCode") String postCode,
			@RequestParam("phoneNumber") String phoneNumber)
	{
		Customer customer = new Customer();
		customer.setTitle(title);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setAddressLine1(addressLine1);
		customer.setAddressLine2(addressLine2);
		customer.setCity(city);
		customer.setPostCode(postCode);
		customer.setPhoneNumber(phoneNumber);
		
		boolean valid = true;
		String errorMessage = "";
		
		if (!StringUtils.hasText(title) || title.length() >= 6)
		{
			errorMessage = "No title was provided or it was too long.";
			valid = false;
			
		}
		else if (!StringUtils.hasText(firstName) || firstName.length() >= 51)
		{
			errorMessage = "No first name was provided or it was too long.";
			valid = false;
			
		}
		else if (!StringUtils.hasText(lastName) || lastName.length() >= 51)
		{
			errorMessage = "No last name was provided or it was too long.";
			valid = false;
			
		}
		else if (!EmailValidator.getInstance().isValid(email))
		{
			errorMessage = "The email address provided was not valid.";
			valid = false;
			
		}
		else if (!StringUtils.hasText(email) || email.length() >= 256)
		{
			errorMessage = "No email address was provided or it was too long.";
			valid = false;
			
		}
		else if (!StringUtils.hasText(addressLine1) || addressLine1.length() >= 256)
		{
			errorMessage = "No address line was provided or it was too long.";
			valid = false;
			
		}
		else if (addressLine2 != null && addressLine2.length() >= 256)
		{
			errorMessage = "Address line 2 was too long";
			valid = false;
			
		}
		else if (city != null && city.length() >= 256)
		{
			errorMessage = "City entered was too long.";
			valid = false;
			
		}
		else if (!StringUtils.hasText(postCode) || postCode.length() >= 11)
		{
			errorMessage = "No post code was provided or it was too long.";
			valid = false;
			
		}
		else if (phoneNumber != null && phoneNumber.length() >= 21)
		{
			errorMessage = "The phone number entered was too long";
			valid = false;
			
		}
		boolean emailAlreadyExists = jdbcTemplate.queryForList("SELECT id FROM customers where email_address = ?", email).size() == 1;
		if (emailAlreadyExists)
		{
			errorMessage = "Email already in use";
			valid = false;
		}
		
		if (!valid)
		{
			req.setAttribute("errorMessage", errorMessage);
			return "registration-form";
		}
		
		// Insert customer into DB
		jdbcTemplate.update("INSERT INTO customers(registered, title, first_name, last_name, email_address, address_line_1, address_line_2, city, postcode, phone_number) VALUES (?,?,?,?,?,?,?,?,?,?)",
				LocalDateTime.now(),customer.getTitle(), customer.getFirstName(), customer.getLastName(), customer.getEmail().toLowerCase(), customer.getAddressLine1(), customer.getAddressLine2(), customer.getCity(), customer.getPostCode(),
				customer.getPhoneNumber());
		
		return "success";
	}
}
