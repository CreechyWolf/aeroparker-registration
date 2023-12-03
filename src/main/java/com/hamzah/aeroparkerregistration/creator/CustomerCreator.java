package com.hamzah.aeroparkerregistration.creator;

import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import com.hamzah.aeroparkerregistration.model.Customer;

@Component
public class CustomerCreator
{
	public Customer createCustomer(String title, String firstName, String lastName, String email, String addressLine1,
			String addressLine2, String city, String postCode, String phoneNumber)
	{
		Customer customer = new Customer();
		customer.setTitle(escape(title));
		customer.setFirstName(escape(firstName));
		customer.setLastName(escape(lastName));
		customer.setEmail(escape(email));
		customer.setAddressLine1(escape(addressLine1));
		customer.setAddressLine2(escape(addressLine2));
		customer.setCity(escape(city));
		customer.setPostCode(escape(postCode));
		customer.setPhoneNumber(escape(phoneNumber));
		return customer;
	}
	
	private String escape(String val)
	{
		return HtmlUtils.htmlEscape(val);
	}
}
