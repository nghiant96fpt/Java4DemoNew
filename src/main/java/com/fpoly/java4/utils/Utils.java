package com.fpoly.java4.utils;

import javax.servlet.http.Cookie;

public class Utils {
//	cookieName => user_id ==> return 0;
	public static String getCookieByName(Cookie[] cookies, String cookieName) {
		if (cookies == null) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				return cookie.getValue();
			}
		}

		return null;
	}
}
