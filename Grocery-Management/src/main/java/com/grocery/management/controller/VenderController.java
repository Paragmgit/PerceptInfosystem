package com.grocery.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.management.payload.VenderPayload;
import com.grocery.management.service.VenderService;

@RestController
@RequestMapping(value = "/api")
public class VenderController {

	@Autowired
	private VenderService venderService;

	@PostMapping(value = "/vender/create")
	public ResponseEntity<Object> create(@RequestBody VenderPayload venderPayload) {
		return venderService.create(venderPayload);
	}

	@GetMapping(value = "/vender/{id}")
	public ResponseEntity<Object> get(@PathVariable String id) {
		return venderService.vender(id);
	}

	@GetMapping(value = "/venders")
	public List<VenderPayload> getAll() {
		return venderService.venders();
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody VenderPayload venderPayload) {
		return venderService.update(id, venderPayload);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		return venderService.delete(id);
	}

}
