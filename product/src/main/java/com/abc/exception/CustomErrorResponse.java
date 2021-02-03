package com.abc.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime localDateTime;
	private Integer status;
	private String error;
}
