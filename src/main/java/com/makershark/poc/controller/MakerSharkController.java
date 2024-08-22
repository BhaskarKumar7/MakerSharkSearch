package com.makershark.poc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makershark.poc.dtos.ServerResponseDto;
import com.makershark.poc.dtos.SupplierDto;
import com.makershark.poc.service.SupplierService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/")
@Validated
@RequiredArgsConstructor
public class MakerSharkController {

	private final SupplierService supplierService;
	
	@PostMapping("supplier/add")
	public ResponseEntity<ServerResponseDto> createSupplier(@RequestBody @Valid SupplierDto supplierDto) {
		return new ResponseEntity<>(supplierService.saveSupplier(supplierDto), HttpStatus.OK);
	}	 
	
	@PostMapping("supplier")
	public ResponseEntity<?> getAllSuppliersByCriteria(
			@RequestParam(required = false,defaultValue = "") String location,
			@RequestParam(required = false,defaultValue = "") String natureOfBusiness,
			@RequestParam(required = false,defaultValue = "") String manufacturingProcess,
			@RequestParam(required = false,defaultValue = "0") @Valid @Pattern(regexp = "^[0-9]*$", message = "page number should be a valid number") String page,
			@RequestParam(required = false,defaultValue = "10") @Valid  @Pattern(regexp = "^[0-9]*$", message = "page size should be a valid number") String size)	{
		
		List<SupplierDto> supplierDtosList = supplierService.fetchSuppliersByCriteria
				(location.trim(), natureOfBusiness.trim(), manufacturingProcess.trim(), Integer.parseInt(page),Integer.parseInt(size));
		if(CollectionUtils.isEmpty(supplierDtosList))	{
			return new ResponseEntity<>("{\"message\":\"No suppliers found with given criteria\"}", HttpStatus.OK);
		}
		return new ResponseEntity<>(supplierDtosList, HttpStatus.OK);
	}
}
