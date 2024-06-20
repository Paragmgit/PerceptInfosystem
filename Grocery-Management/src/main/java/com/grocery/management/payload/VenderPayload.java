package com.grocery.management.payload;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VenderPayload {
	
	private String id;
	private String name;
	private String email;
	private String phoneNo;
	private String address;
	private String location;
	private LocalDate createdAt;
	private LocalDate updatedAt;

}
