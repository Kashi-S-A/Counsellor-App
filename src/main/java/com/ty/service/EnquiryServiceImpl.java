package com.ty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.dto.EnquiryDto;
import com.ty.dto.FilterDto;
import com.ty.entity.Counsellor;
import com.ty.entity.Enquiry;
import com.ty.enums.ClassMode;
import com.ty.exception.CounsellorNotFound;
import com.ty.repository.CounsellorRepository;
import com.ty.repository.EnquiryRepository;
import com.ty.responsestructure.ResponseStructure;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private CounsellorRepository counsellorRepository;

	/*
	 * Check for the counsellor, check for enquiry, if counsellor exist and enquiry
	 * does not exists then add counsellor to the enquiry and save the enquiry, else
	 * throw respective messages and exceptions
	 */
	@Override
	public ResponseEntity<?> addEnquiry(Integer cid, Enquiry enquiry) {
		Counsellor counsellor = counsellorRepository.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("Counsellor Does not exist"));
		Optional<Enquiry> opt = enquiryRepository.findByEmail(enquiry.getEmail());

		if (opt.isPresent()) {
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
			rs.setMessage("Enquiry already added");
			rs.setData(enquiry.getEmail());

			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		} else {
			enquiry.setCounsellor(counsellor);
			Enquiry save = enquiryRepository.save(enquiry);

			EnquiryDto dto = new EnquiryDto();

			BeanUtils.copyProperties(save, dto);

			ResponseStructure<EnquiryDto> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setMessage("Enquiry added successfully");
			rs.setData(dto);

			return new ResponseEntity<ResponseStructure<EnquiryDto>>(rs, HttpStatus.OK);
		}

	}

	@Override
	public ResponseEntity<?> updateClassMode(Integer eid, ClassMode classMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> updateFees(Integer eid, Double fees) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteEnquiry(Integer cid, Integer eid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getEnquiry(Integer eid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getEnquiries(Integer cid) {
		Counsellor counsellor = counsellorRepository.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("COunsellor does not exist"));
		List<Enquiry> enquiries = counsellor.getEnquiries();

		// implement pagination here if needed

		ResponseStructure<List<Enquiry>> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Fetched All enquiries successfully");
		rs.setData(enquiries);

		return new ResponseEntity<ResponseStructure<List<Enquiry>>>(rs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> filter(FilterDto dto) {

		Enquiry enquiry = new Enquiry();

		BeanUtils.copyProperties(dto, enquiry);

		Example<Enquiry> of = Example.of(enquiry);

		List<Enquiry> all = enquiryRepository.findAll(of);

		ResponseStructure<List<Enquiry>> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Retrieved Enquiries");
		rs.setData(all);

		return new ResponseEntity<ResponseStructure<List<Enquiry>>>(rs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getEnquiryPage(Integer pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}
