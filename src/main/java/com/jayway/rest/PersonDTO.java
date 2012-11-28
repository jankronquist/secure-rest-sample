package com.jayway.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonDTO {
	public PersonDTO(String first, String last) {
		firstName = first;
		lastName = last;
	}
	public PersonDTO() {
	}
	public String firstName;
	public String lastName;
}
