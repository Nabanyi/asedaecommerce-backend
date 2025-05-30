package com.aseda.demo.requestobjs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.aseda.demo.utils.UserRoleEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RegisterRequest {
	@NotEmpty(message = "Username is required")
	private String username;
	
	@NotEmpty(message = "Password is required")
    private String password;
    
	@NotNull(message = "User role name is required")
    private UserRoleEnum role;
    
    @JsonProperty("firstname")
    @NotEmpty(message = "First name is required")
    private String firstName;
    
    @JsonProperty("middlename")
    @NotEmpty(message = "Middle name is required")
    private String middleName;
    
    @JsonProperty("lastname")
    @NotEmpty(message = "Last name is required")
    private String lastName;
    
    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email is required")
    private String email;
    
    @NotEmpty(message = "Phone is required")
    private String phone;
    
    @NotEmpty(message = "Address is required")
    private String address;
    
	public RegisterRequest() {
	}
	
	public RegisterRequest(String username, String password, UserRoleEnum role, String firstName, String middleName,
			String lastName, String email, String phone, String address) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRoleEnum getRole() {
		return role;
	}
	public void setRole(UserRoleEnum role) {
		this.role = role;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
    
}
