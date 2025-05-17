package com.project.hotel_management;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	public static void main(String[] args) {
	    
	  	
	      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	      String adminPassword = "admin123";
	      String managerPassword = "manager123";
	      String guestPassword = "guest123";
	      String receptionistPassword= "rec123";
	      

	      // Encrypt the password
	      System.out.println("Admin Password: " + encoder.encode(adminPassword));
	      System.out.println("Manager Password: " + encoder.encode(managerPassword));
	      System.out.println("Guest Password: " + encoder.encode(guestPassword));
	      System.out.println("Receptionist Password: " + encoder.encode(receptionistPassword));
	  }
}