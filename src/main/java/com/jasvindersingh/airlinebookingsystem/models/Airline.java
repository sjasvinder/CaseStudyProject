package com.jasvindersingh.airlinebookingsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                                              // This class is a Entity class
@AllArgsConstructor
@Data                                                // getters, setters and constructors will be done through @Data annotation
@NoArgsConstructor
@Table(name="airlines")
public class Airline {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)      // Id will be auto generated
	private Long id;                                         // primary key data type long                  
	@Column(length=100,nullable = false,unique=true)
	private String airlineName;
	@Column(length=100,nullable = false)
	private String placeFrom;
	@Column(length=100,nullable = false)
	private String placeTo;
	@Column(length=100,nullable = false)
	private String phone;

}
