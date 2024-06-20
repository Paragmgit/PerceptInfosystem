package com.grocery.management.service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.grocery.management.constants.ApplicationResponseCode;
import com.grocery.management.constants.Constants;
import com.grocery.management.constants.EntityTypes;
import com.grocery.management.dao.VenderDao;
import com.grocery.management.entity.Vender;
import com.grocery.management.model.ResponseDetails;
import com.grocery.management.payload.VenderPayload;

import jakarta.transaction.Transactional;

@Service
public class VenderService {
	
	@Autowired
	private VenderDao venderDao;

	@Transactional
	public ResponseEntity<Object> create(VenderPayload venderPayload) {
		
		if (venderDao.isAlreadyExits(venderPayload.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ResponseDetails(ApplicationResponseCode.RESOURCE_CONFLICT, MessageFormat
							.format(Constants.ALREADY_EXITS, EntityTypes.VENDER.getTitle(), venderPayload.getEmail())));
		}

		if (Objects.isNull(venderPayload.getEmail()) || venderPayload.getEmail().equalsIgnoreCase("null")
				|| venderPayload.getEmail().isEmpty() || venderPayload.getEmail().trim().length() == 0) {
			return ResponseEntity.badRequest().body(new ResponseDetails(ApplicationResponseCode.CONSTRAINT_VOILATION,
					MessageFormat.format(Constants.RESOURCE_CANNOT_BE_EMPTY, Constants.EMAIL)));
		}

		if (!venderPayload.getEmail().matches(
				"^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$")) {
			return ResponseEntity.badRequest().body(new ResponseDetails(ApplicationResponseCode.CONSTRAINT_VOILATION,
					MessageFormat.format(Constants.RESOURCE_CORRECTION_EMAIL, venderPayload.getEmail())));
		}

		if (Objects.isNull(venderPayload.getName()) || venderPayload.getName().equalsIgnoreCase("null")
				|| venderPayload.getName().isEmpty() || venderPayload.getName().trim().length() == 0) {
			return ResponseEntity.badRequest().body(new ResponseDetails(ApplicationResponseCode.CONSTRAINT_VOILATION,
					MessageFormat.format(Constants.RESOURCE_CANNOT_BE_EMPTY, "Name")));
		}

		if (venderPayload.getName().length() >= 20) {
			return ResponseEntity.badRequest().body(new ResponseDetails(ApplicationResponseCode.CONSTRAINT_VOILATION,
					MessageFormat.format(Constants.NAME_SIZE_SHOULD_BE, venderPayload.getName())));
		}

		if (!venderPayload.getName().matches("^[a-zA-Z0-9 ]*$")) {
			return ResponseEntity.badRequest().body(new ResponseDetails(ApplicationResponseCode.CONSTRAINT_VOILATION,
					MessageFormat.format(Constants.RESOURCE_CORRECTION_NAME, venderPayload.getName())));
		}
		
		Vender vender = new Vender();
		vender.setId(EntityTypes.VENDER.getIdPrefix().concat(UUID.randomUUID().toString()));
		vender.setName(venderPayload.getName());
		vender.setEmail(venderPayload.getEmail());
		vender.setPhoneNo(venderPayload.getPhoneNo());
		vender.setAddress(venderPayload.getAddress());
		vender.setLocation(venderPayload.getLocation());
		vender.setCreatedAt(LocalDate.now());
		vender.setUpdatedAt(LocalDate.now());
		vender.setIsDeleted(false);
		
		vender = (Vender) venderDao.save(vender);
		
		venderPayload.setId(vender.getId());
		venderPayload.setName(vender.getName());
		venderPayload.setEmail(vender.getEmail());
		venderPayload.setPhoneNo(vender.getPhoneNo());
		venderPayload.setAddress(vender.getAddress());
		venderPayload.setLocation(vender.getLocation());
		venderPayload.setCreatedAt(LocalDate.now());
		venderPayload.setUpdatedAt(LocalDate.now());
		return ResponseEntity.ok(venderPayload);
	}

	public ResponseEntity<Object> vender(String id) {
		Vender vender = (Vender) venderDao.getById(id);
		if(!Objects.isNull(vender)) {
			VenderPayload venderPayload = new VenderPayload();
			venderPayload.setId(vender.getId());
			venderPayload.setName(vender.getName());
			venderPayload.setEmail(vender.getEmail());
			venderPayload.setPhoneNo(vender.getPhoneNo());
			venderPayload.setAddress(vender.getAddress());
			venderPayload.setLocation(vender.getLocation());
			venderPayload.setCreatedAt(LocalDate.now());
			venderPayload.setUpdatedAt(LocalDate.now());
			return ResponseEntity.ok(venderPayload);
		}
		return ResponseEntity.badRequest().body(new ResponseDetails(ApplicationResponseCode.RESOURCE_NOT_FOUND,
				MessageFormat.format(Constants.RESOURCE_NOT_FOUND, EntityTypes.VENDER.getTitle(), id)));

	}

	public List<VenderPayload> venders() {
		List<Vender>venders=venderDao.getAll();
		List<VenderPayload>venderPayloads = new ArrayList<>();
		for(Vender vender:venders) {
			VenderPayload venderPayload = new VenderPayload();
			venderPayload.setId(vender.getId());
			venderPayload.setName(vender.getName());
			venderPayload.setEmail(vender.getEmail());
			venderPayload.setPhoneNo(vender.getPhoneNo());
			venderPayload.setAddress(vender.getAddress());
			venderPayload.setLocation(vender.getLocation());
			venderPayload.setCreatedAt(LocalDate.now());
			venderPayload.setUpdatedAt(LocalDate.now());
			venderPayloads.add(venderPayload);
		}
		return venderPayloads;
	}

	@Transactional
	public ResponseEntity<Object> update(String id, VenderPayload venderPayload) {
		Vender vender = (Vender) venderDao.getById(id);
		if(!Objects.isNull(vender)) {
			vender.setName(venderPayload.getName());
			vender.setEmail(venderPayload.getEmail());
			vender.setPhoneNo(venderPayload.getPhoneNo());
			vender.setAddress(venderPayload.getAddress());
			vender.setLocation(venderPayload.getLocation());
			vender.setCreatedAt(venderPayload.getCreatedAt());
			vender.setUpdatedAt(LocalDate.now());
			return ResponseEntity.ok(venderDao.save(vender));
		}
		return ResponseEntity.badRequest().body(new ResponseDetails(ApplicationResponseCode.INTERNAL_SERVER_ERROR,
				MessageFormat.format(Constants.RESOURCE_NOT_FOUND, id)));
	}

	@Transactional
	public ResponseEntity<Object> delete(String id) {
		Vender vender = (Vender) venderDao.getById(id);
		if(!Objects.isNull(vender)) {
			vender.setIsDeleted(true);
			venderDao.save(vender);
			return ResponseEntity.ok(venderDao.deleteById(id));
		}
		return ResponseEntity.badRequest().body(new ResponseDetails(ApplicationResponseCode.RESOURCE_NOT_FOUND,
				MessageFormat.format(Constants.RESOURCE_NOT_FOUND, EntityTypes.VENDER.getTitle(), id)));
	}

}
