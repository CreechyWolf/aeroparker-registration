package com.hamzah.aeroparkerregistration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hamzah.aeroparkerregistration.creator.CustomerCreator;
import com.hamzah.aeroparkerregistration.database.DatabaseManager;
import com.hamzah.aeroparkerregistration.model.Customer;
import com.hamzah.aeroparkerregistration.model.ValidationResult;
import com.hamzah.aeroparkerregistration.validation.RegistrationValidator;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest
{
	@Mock
	private HttpServletRequest req;
	@Mock
	private RegistrationValidator validator;
	@Mock
	private CustomerCreator creator;
	@Mock
	private DatabaseManager databaseManager;
	@InjectMocks
	private RegistrationController testedClass;
	
	@Test
	public void testRegisterGet()
	{
		assertEquals("registration-form", testedClass.register());
	}

	@Test
	public void testRegisterPost()
	{
		ValidationResult validationResult = new ValidationResult();
		validationResult.setValid(true);
		Customer customer = new Customer();
		
		
		when(validator.validateForm(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(validationResult);
		when(creator.createCustomer(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(customer);
		
		assertEquals("success", testedClass.registerPost("title", "firstName", "lastName", "email@test.com", "add1", "add2", "city", "pcode", "pNum"));
		
		verify(databaseManager).insertCustomer(customer);
	}


	@Test
	public void testRegisterPost_FailValidation()
	{
		ValidationResult validationResult = new ValidationResult();
		validationResult.setValid(false);
		validationResult.setErrorMessage("error");
		
		when(validator.validateForm(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(validationResult);
		
		assertEquals("registration-form", testedClass.registerPost("title", "firstName", "lastName", "email@test.com", "add1", "add2", "city", "pcode", "pNum"));
		
		verify(req).setAttribute("errorMessage", "error");
	}
}
