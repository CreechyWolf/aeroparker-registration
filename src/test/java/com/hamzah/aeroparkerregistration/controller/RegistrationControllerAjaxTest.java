package com.hamzah.aeroparkerregistration.controller;

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

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerAjaxTest
{
	@Mock
	private DatabaseManager databaseManager;
	@InjectMocks
	private RegistrationControllerAjax testClass;

	@Test
	public void testIsEmailInUse_NotInUse()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(false);
		
		assertFalse(testClass.isEmailInUse("email"));
	}
	
	@Test
	public void testIsEmailInUse_IsInUse()
	{
		when(databaseManager.isEmailInUse(anyString())).thenReturn(true);
		
		assertTrue(testClass.isEmailInUse("email"));
	}
}
