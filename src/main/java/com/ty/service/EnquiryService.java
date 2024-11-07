package com.ty.service;

import org.springframework.http.ResponseEntity;

import com.ty.dto.FilterDto;
import com.ty.entity.Enquiry;
import com.ty.enums.ClassMode;

public interface EnquiryService {

	ResponseEntity<?> addEnquiry(Integer cid,Enquiry enquiry);
	
	ResponseEntity<?> updateClassMode(Integer eid,ClassMode classMode);
	
	ResponseEntity<?> updateFees(Integer eid,Double fees);
	
	ResponseEntity<?> deleteEnquiry(Integer cid,Integer eid);
	
	ResponseEntity<?> getEnquiry(Integer eid);
	
	ResponseEntity<?> getEnquiries(Integer cid);
	
	ResponseEntity<?> filter(FilterDto dto);
	
	ResponseEntity<?> getEnquiryPage(Integer pageNumber);
}
