package com.hamzah.aeroparkerregistration.creator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hamzah.aeroparkerregistration.model.Customer;

@ExtendWith(MockitoExtension.class)
public class CustomerCreatorTest
{
	@InjectMocks
	private CustomerCreator creator;

	@Test
	public void testCreateCustomer()
	{
		String title = "title";
		String firstName = "firstName";
		String lastName = "lastName";
		String email = "email";
		String addressLine1 = "add1";
		String addressLine2 = "add2";
		String city = "city";
		String postCode = "postCode";
		String phoneNumber = "phoneNumber";
		
		Customer customer = creator.createCustomer(title, firstName, lastName, email, addressLine1, addressLine2, city, postCode, phoneNumber);
		
		assertEquals(title, customer.getTitle());
		assertEquals(firstName, customer.getFirstName());
		assertEquals(lastName, customer.getLastName());
		assertEquals(email, customer.getEmail());
		assertEquals(addressLine1, customer.getAddressLine1());
		assertEquals(addressLine2, customer.getAddressLine2());
		assertEquals(city, customer.getCity());
		assertEquals(postCode, customer.getPostCode());
		assertEquals(phoneNumber, customer.getPhoneNumber());
	}
	
	@Test
	public void testCreateCustomer_SpecialCharsEscaped()
	{
		String title = "<>";
		
		Customer customer = creator.createCustomer(title, null, null, null, null, null, null, null, null);
		
		assertEquals("&lt;&gt;", customer.getTitle());
	}
}
