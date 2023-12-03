package com.hamzah.aeroparkerregistration.database;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hamzah.aeroparkerregistration.model.Customer;

@Component
public class DatabaseManager
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertCustomer(Customer customer)
	{
		jdbcTemplate.update("INSERT INTO customers(registered, title, first_name, last_name, email_address, address_line_1, address_line_2, city, postcode, phone_number) VALUES (?,?,?,?,?,?,?,?,?,?)",
				LocalDateTime.now(), customer.getTitle(), customer.getFirstName(), customer.getLastName(), customer.getEmail().toLowerCase(), customer.getAddressLine1(), customer.getAddressLine2(), 
				customer.getCity(), customer.getPostCode(), customer.getPhoneNumber());
	}
	
	public boolean isEmailInUse(String email)
	{
		return jdbcTemplate.queryForList("SELECT id FROM customers where email_address = ?", email).size() == 1;
	}
}
