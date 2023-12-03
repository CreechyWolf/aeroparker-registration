package com.hamzah.aeroparkerregistration.creator;

import com.hamzah.aeroparkerregistration.model.Customer;

public class CustomerCreator
{
	public Customer createCustomer(String title, String firstName, String lastName, String email, String addressLine1,
			String addressLine2, String city, String postCode, String phoneNumber)
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
		return customer;
	}
}
