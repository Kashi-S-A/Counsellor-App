package com.ty.dto;

import com.ty.enums.ClassMode;
import com.ty.enums.Course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnquiryDto {

	private String name;

	private Long phone;

	private ClassMode classMode;

	private Course course;

}
