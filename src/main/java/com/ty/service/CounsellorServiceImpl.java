package com.ty.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.dto.CounsellorDto;
import com.ty.dto.LoginRequest;
import com.ty.entity.Counsellor;
import com.ty.enums.Status;
import com.ty.exception.CounsellorNotFound;
import com.ty.repository.CounsellorRepository;
import com.ty.responsestructure.ResponseStructure;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounsellorRepository counsellorRepository;

	/*
	 * Taking counsellor if not Registered then save it else don't save it
	 */
	@Override
	public ResponseEntity<?> register(Counsellor counsellor) {
		Optional<Counsellor> opt = counsellorRepository.findByEmail(counsellor.getEmail());
		if (opt.isPresent()) {
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
			rs.setMessage("Already Registered");
			rs.setData(counsellor.getEmail());
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		} else {
			Counsellor save = counsellorRepository.save(counsellor);

//			ObjectMapper mapper = new ObjectMapper();
//			CounsellorDto dto = mapper.convertValue(save, CounsellorDto.class);

			CounsellorDto dto = new CounsellorDto();

			BeanUtils.copyProperties(save, dto);

			ResponseStructure<CounsellorDto> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setMessage("Registered Successfully");
			rs.setData(dto);

			return new ResponseEntity<ResponseStructure<CounsellorDto>>(rs, HttpStatus.OK);

		}
	}

	/*
	 * First Find Counsellor by email if exist validate password, then if validated
	 * return success message else error messages or exceptions
	 */
	@Override
	public ResponseEntity<?> login(LoginRequest request) {
		Counsellor counsellor = counsellorRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new CounsellorNotFound("Counsellor is Not Registered"));
		ResponseStructure<String> rs = new ResponseStructure<>();
		if (counsellor.getPassword().equals(request.getPassword())) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Login Successfully");
			rs.setData(request.getEmail());
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		}
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("Invalid Password");
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}

	/*
	 * Checks counsellor if present update the status else throw exception
	*/
	@Override
	public ResponseEntity<?> updateStatus(Integer cid, Status status) {
		Counsellor counsellor = counsellorRepository.findById(cid)
				.orElseThrow(()-> new CounsellorNotFound("Counsellor Not registered"));
		counsellor.setStatus(status);
		Counsellor save = counsellorRepository.save(counsellor);
		ResponseStructure<Status> rs=new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Status updated successfully");
		rs.setData(save.getStatus());
		return new ResponseEntity<ResponseStructure<Status>>(rs,HttpStatus.OK);
	}

	
	/*
	 * Checks counsellor if present update the phone else throw exception
	*/
	@Override
	public ResponseEntity<?> updatePhone(Integer cid, Long phone) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Fetch Counsellor if exists else throw exception
	*/
	@Override
	public ResponseEntity<?> getCounsellor(Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*
	 * Delete Counsellor by id if exists else throw exception.
	*/
	@Override
	public ResponseEntity<?> deleteCounsellor(Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}
}
