package com.aseda.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aseda.demo.dto.RefreshTokenDTO;
import com.aseda.demo.dto.UserDTO;
import com.aseda.demo.requestobjs.AuthRequest;
import com.aseda.demo.requestobjs.RegisterRequest;
import com.aseda.demo.requestobjs.UpdatePassword;
import com.aseda.demo.requestobjs.UserUpdateRequest;
import com.aseda.demo.service.AuthService;
import com.aseda.demo.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "APIs for user authentication")
public class AuthController {

	@Autowired
    private AuthService authService;
	
	@Operation(summary = "Login", description = "Authenticates user and returns a User Object with JWT token")
    @PostMapping("/login")
    public ApiResponse<UserDTO> login(@RequestBody AuthRequest request) {
        return new ApiResponse<UserDTO> (true, "Login Successful", authService.authenticate(request));
    }
	
	//	Refresh Access Token
	@Operation(summary = "Access Token Refresh", description = "Refresh Access Token")
	@PostMapping("/refresh")
    public ApiResponse<RefreshTokenDTO> refresh(@RequestBody RefreshTokenDTO refreshToken) {
        return new ApiResponse<RefreshTokenDTO> (true, "Refresh Successful", authService.refreshAccessToken(refreshToken) );
    }
    
    // Register End points
	@Operation(summary = "Register", description = "Registers a new user")
    @PostMapping("/register")
    public ApiResponse<String> register(@Valid @RequestBody RegisterRequest request) {
    	authService.register(request);
        return new ApiResponse<>(true, "Registration Successful, You may now login", null);
    }
	
	// Update
	@Operation(summary = "Update User", description = "Update current user details")
	@PostMapping("/update")
	public ApiResponse<String> updateUser(@Valid @RequestBody UserUpdateRequest request) {
		authService.updateUser(request);
		return new ApiResponse<>(true, "Account update Successful", null);
	}
	
	// Update Password
	@Operation(summary = "Update Password", description = "Update current user password")
	@PostMapping("/update-password")
	public ApiResponse<String> updatePassword(@Valid @RequestBody UpdatePassword request) {
		authService.updatePassword(request);
		return new ApiResponse<>(true, "Password update Successful", null);
	}
}
