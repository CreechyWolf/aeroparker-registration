package com.hamzah.aeroparkerregistration.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hamzah.aeroparkerregistration.creator.CustomerCreator;
import com.hamzah.aeroparkerregistration.model.Customer;
import com.hamzah.aeroparkerregistration.model.ValidationResult;
import com.hamzah.aeroparkerregistration.validation.RegistrationValidator;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/registration")
public class RegistrationController
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private RegistrationValidator validator;
	@Autowired
	private CustomerCreator creator;
	
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
		ValidationResult validationResult = validator.validateForm(title, firstName, lastName, email, addressLine1, addressLine2, city, postCode, phoneNumber);
		
		if (!validationResult.isValid())
		{
			req.setAttribute("errorMessage", validationResult.getErrorMessage());
			return "registration-form";
		}
		
		Customer customer = creator.createCustomer(title, firstName, lastName, email, addressLine1, addressLine2, city, postCode, phoneNumber);
		
		// Insert customer into DB
		jdbcTemplate.update("INSERT INTO customers(registered, title, first_name, last_name, email_address, address_line_1, address_line_2, city, postcode, phone_number) VALUES (?,?,?,?,?,?,?,?,?,?)",
				LocalDateTime.now(),customer.getTitle(), customer.getFirstName(), customer.getLastName(), customer.getEmail().toLowerCase(), customer.getAddressLine1(), customer.getAddressLine2(), customer.getCity(), customer.getPostCode(),
				customer.getPhoneNumber());
		
		return "success";
	}
}
