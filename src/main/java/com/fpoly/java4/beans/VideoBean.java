package com.fpoly.java4.beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Part;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoBean {
	private String name;
	private String desc;
	private Part image;
	private String url;
	private int category;

	public Map<String, String> getErrors() {
		Map<String, String> errors = new HashMap<String, String>();

		if (name.isBlank()) {
			errors.put("errName", "Tên không được bỏ trống");
		}

		if (desc.trim().split(" ").length < 15) {
			errors.put("errDesc", "Mô tả phải có ít nhất 15 từ");
		}

		double maxSize = 1024 * 50;

		if (!image.getContentType().startsWith("image/")) {
			errors.put("errImage", "File tải lên phải là ảnh");
		} else if (image.getSize() > maxSize) {
			errors.put("errImage", "File tải lên không lớn hơn 50KB");
		}

		if (!url.matches(
				"[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")) {
			errors.put("errUrl", "URL không đúng định dạng");
		}

		if (category == -1) {
			errors.put("errCat", "Danh mục bắt buộc chọn");
		}

		return errors;
	}
}
