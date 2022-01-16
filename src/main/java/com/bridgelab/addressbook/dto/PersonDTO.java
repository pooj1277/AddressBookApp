package com.bridgelab.addressbook.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class PersonDTO {

	private int id;

	@NotEmpty(message = "Name Cannot be Empty.")
	@Pattern(regexp = "^[A-Z]{1}[A-Za-z]{2,}$", message = "Name is Invalid.")
	private String name;

	@Email(message = "Email is not in the proper format.")
	private String email;

	@NotEmpty(message = "City connot be Empty.")
	private String mobileNo;
	private String city;
	private String state;
	private String zipcode;
	private String address;
	

}
