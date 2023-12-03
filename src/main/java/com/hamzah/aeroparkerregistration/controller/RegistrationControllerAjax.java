package com.hamzah.aeroparkerregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hamzah.aeroparkerregistration.database.DatabaseManager;

@RestController
@RequestMapping("/registration-ajax")
public class RegistrationControllerAjax
{
	@Autowired
	private DatabaseManager databaseManager;
	
	@GetMapping("/check-email")
	public boolean isEmailInUse(@RequestParam("email") String email) 
	{
		return databaseManager.isEmailInUse(email);
	}
}
