package com.grocery.management.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordChangeRequest {
	private String currentPassword;
    private String newPassword;
    private String repeatNewPassword;
}
