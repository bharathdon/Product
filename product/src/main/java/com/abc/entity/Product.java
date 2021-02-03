package com.abc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "pid can not be blank")
	private Integer pid;

	@NotEmpty(message = "name must not be null")
	private String pname;

	@NotNull(message = "price ust not be null")
	//@NotBlank(message = "price must not be null")
	private Integer price;

	@DateTimeFormat(pattern = "dd-mm-yy")
	@Column(name = "purchase_date")
	private String purchaseDate;

	@NotNull(message = "quantity must not be null")
	private Integer qty;
}