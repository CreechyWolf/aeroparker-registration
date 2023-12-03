package com.hamzah.aeroparkerregistration.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hamzah.aeroparkerregistration.database.DatabaseManager;
import com.hamzah.aeroparkerregistration.model.ValidationResult;

@ExtendWith(MockitoExtension.class)
public class RegistrationValidatorTest
{
	@Mock
	private DatabaseManager databaseManager;
	@InjectMocks
	private RegistrationValidator testedClass;
	
	@Test
	public void testValidateForm_Success()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "add1", "add2", "city", "pcode", "pNum");
		
		assertTrue(result.isValid());
		assertTrue(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_TitleMissing()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("", "firstName", "lastName", "email@test.com", "add1", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_TitleTooLong()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("longlonglongtitle", "firstName", "lastName", "email@test.com", "add1", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_FirstNameMissing()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "", "lastName", "email@test.com", "add1", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_FirstNameTooLong()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstName",
				"lastName", "email@test.com", "add1", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_LastNameMissing()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "", "email@test.com", "add1", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_LastNameTooLong()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastNamelastNamelastNamelastNamelastNamelastNamelastNamelastNamelastNamelastName", 
				"email@test.com", "add1", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_EmailMissing()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "", "add1", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_EmailTooLong()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "emailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemail"
				+ "emailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemail"
				+ "emailemailemailemailemailemailemailemailemailemailemailemail", 
				"add1", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_EmailNotValid()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "notvalidEmail.com", "add1", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_AddressLine1Missing()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_AddressLine1TooLong()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "add1add1add1add1add1add1add1add1add1add1"
				+ "add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1"
				+ "add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_AddressLine2Missing()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "add1", "", "city", "pcode", "pNum");
		
		// We don't need address line 2 so it is valid
		assertTrue(result.isValid());
		assertTrue(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_AddressLine2TooLong()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "add1", "add1add1add1add1add1add1add1add1add1add1"
				+ "add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1"
				+ "add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_CityMissing()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "add1", "add2", "", "pcode", "pNum");
		
		// We don't need city so it is valid
		assertTrue(result.isValid());
		assertTrue(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_CityTooLong()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "add1", "add2", "add1add1add1add1add1add1add1add1add1add1"
				+ "add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1"
				+ "add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1add1", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_PostCodeMissing()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "city", "add2", "city", "", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_PostCodeTooLong()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "add1", "add2", "city", "pcodepcodepcodepcodepcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_PhoneNumberMissing()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "add1", "add2", "city", "pcode", "");
		
		// We don't need phone number so it is valid
		assertTrue(result.isValid());
		assertTrue(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_PhoneNumberTooLong()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "add1", "add2", "city", "pcode", "pNumpNumpNumpNumpNumpNumpNumpNumpNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
	
	@Test
	public void testValidateForm_EmailInUse()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(true);
		
		ValidationResult result = testedClass.validateForm("title", "firstName", "lastName", "email@test.com", "add1", "add2", "city", "pcode", "pNum");
		
		assertFalse(result.isValid());
		assertFalse(result.getErrorMessage().isEmpty());
	}
}
