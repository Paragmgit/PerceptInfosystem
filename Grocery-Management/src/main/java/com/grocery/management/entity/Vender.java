package com.grocery.management.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "gm_vender")
public class Vender {
	
	@Id
	private String id;
	private String name;
	private String email;
	private String phoneNo;
	private String address;
	private String location;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private Boolean isDeleted;

}
