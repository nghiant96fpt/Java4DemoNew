package com.fpoly.java4.beans;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBean {
	private int id;
	private String username;
	private String password;
	private String name;
	private String email;

	public Map<String, String> getErrors() {
		Map<String, String> errors = new HashMap<String, String>();
		if (username.isBlank()) {
			errors.put("errUsername", "Ten tai khong trong");
		}
		if (password.isBlank() || password.length() < 6) {
			errors.put("errPassword", "Mat khau phai co it nhat 6 ky tu");
		}
		if (name.isBlank()) {
			errors.put("errName", "Ten khong trong");
		}
		if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			errors.put("errEmail", "Email khong dung");
		}
		return errors;
	}
}
