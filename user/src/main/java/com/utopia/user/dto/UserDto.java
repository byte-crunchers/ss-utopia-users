package com.utopia.user.dto;


import java.lang.reflect.Field;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Long id;
	private String username, first_name, last_name, email, street_address, city, us_state;
	private Integer zip;
	private Long phone;
	private LocalDate dob;
	private Boolean is_admin, active, confirmed;
	
	private String full_name, full_address, phone_str, active_str, confirmed_str, admin_str, zip_str;
	
	public UserDto(Long id, String username, String first_name, String last_name, String email, String street_address,
			String city, String us_state, Integer zip, Long phone, LocalDate dob, Boolean is_admin,
			Boolean active, Boolean confirmed) {
		super();
		this.id = id;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.street_address = street_address;
		this.city = city;
		this.us_state = us_state;
		this.zip = zip;
		this.phone = phone;
		this.dob = dob;
		this.is_admin = is_admin;
		this.active = active;
		this.confirmed = confirmed;
		
		//combined fields for viewing users in table
		this.full_name = first_name + " " + last_name;
		zip_str = zip.toString();
		while(zip_str.length() < 5)
			zip_str = "0" + zip_str;
		this.full_address = street_address.trim() + ", " + city + ", " + us_state + " " + zip_str;
		String p = phone.toString();
		if(p.length() == 11)
			p = p.substring(1);
		this.phone_str = "(" + p.substring(0, 3) + ") " + p.substring(3, 6) + "-" + p.substring(6);
		this.active_str = active ? "Yes" : "No";
		this.confirmed_str = confirmed ? "Yes" : "No";
		this.admin_str = is_admin ? "Yes" : "No";
	}

	// print all variables to console
	public void printFields() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append("{" + newLine);

		// determine fields declared in this class only (no fields of superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			try {
				result.append("    " + field.getName() + " = " + field.get(this) + newLine);
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
		}
		result.append("}");

		System.out.println(result.toString());
	}
}
