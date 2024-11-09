package com.ty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.dto.LoginRequest;
import com.ty.entity.Counsellor;
import com.ty.enums.Status;
import com.ty.service.CounsellorService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/counsellor")
@CrossOrigin(value = "http://localhost:1234/")
public class CounsellorController {

	@Autowired
	private CounsellorService counsellorService;

	@PostMapping("/register")
	public ResponseEntity<?> registerCounsellor(@RequestBody Counsellor counsellor) {
		return counsellorService.register(counsellor);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		return counsellorService.login(request);
	}
	
	@PatchMapping("/status")
	@Deprecated
	public ResponseEntity<?> updateStatus(@RequestParam Integer cid,@RequestParam Status status) {
		return counsellorService.updateStatus(cid, status);
	}

}
