package com.hamzah.aeroparkerregistration.validation;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import com.hamzah.aeroparkerregistration.model.ValidationResult;

public class RegistrationValidator
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ValidationResult validateForm(String title, String firstName, String lastName, String email, String addressLine1,
			String addressLine2, String city, String postCode, String phoneNumber)
	{
		ValidationResult validationResult = new ValidationResult();
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
		validationResult.setValid(valid);
		validationResult.setErrorMessage(errorMessage);
		return validationResult;
	}
}
