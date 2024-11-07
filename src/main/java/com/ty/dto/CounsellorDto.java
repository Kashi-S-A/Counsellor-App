package com.ty.dto;

import com.ty.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CounsellorDto {

	private String name;

	private Long phone;

	private String email;

	private Status status;

//	private LocalDateTime createdDate;
}
