package com.jasvindersingh.airlinebookingsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)      // Id auto generated
	private Long id;
	@Column(length=1000,nullable = false)                          // primary key Id
	@NotEmpty(message = "Name is mandatory")
	private String name;
	@Column(length=100,nullable = false,unique=true)
	@NotBlank(message = "Email is mandatory")
	private String email;
	@Column(length=100,nullable = false)
	//'${validatedValue}'
	@Size(min=8,message = "The password must be atleast {min} characters long")
	@NotBlank(message = "Password is mandatory")
	private String password;
	@Column(length=50,nullable = true)
	private String phoneNumber;
	@Column(columnDefinition="varchar(45) default 'USER'")
	private String role;
	
	

}
