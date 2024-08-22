package com.makershark.poc.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.makershark.poc.dtos.ServerResponseDto;
import com.makershark.poc.dtos.SupplierDto;
import com.makershark.poc.entities.Supplier;
import com.makershark.poc.enums.ManufacturingProcessEnum;
import com.makershark.poc.enums.NatureOfBusinessEnum;
import com.makershark.poc.repositories.SupplierRepository;
import com.makershark.poc.service.SupplierService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
	
	private final SupplierRepository supplierRepo;

	@Override
	public ServerResponseDto saveSupplier(SupplierDto supplierDto) {
		 
		if(supplierDto != null) {
			boolean doesExist = supplierRepo.findByLocationAndNatureOfBusinessAndManufacturingProcess
					 (supplierDto.getLocation(), NatureOfBusinessEnum.valueOf(supplierDto.getNatureOfBusiness().toUpperCase()), 
							 ManufacturingProcessEnum.valueOf(supplierDto.getManufacturingProcesses().toUpperCase())) != null;
			 if(doesExist) {
					return new ServerResponseDto("supplier already exists", HttpStatus.BAD_REQUEST.name());
				}
			 else {
				 Supplier supplier = new Supplier();
					supplier.setCompanyName(supplierDto.getCompanyName());
					supplier.setLocation(supplierDto.getLocation());
					supplier.setManufacturingProcess(ManufacturingProcessEnum.valueOf(supplierDto.getManufacturingProcesses().toUpperCase()));
					supplier.setNatureOfBusiness(NatureOfBusinessEnum.valueOf(supplierDto.getNatureOfBusiness().toUpperCase()));
					supplier.setWebsite(supplierDto.getWebsite());
				Supplier savedSupplier = supplierRepo.save(supplier);
				return new ServerResponseDto("supplier is saved with id of "+savedSupplier.getSupplierId(), HttpStatus.OK.name());
			 }
		}
		else {
			return new ServerResponseDto("bad credentials entered", HttpStatus.BAD_REQUEST.name());	
		}
	}

	@Override
	public List<SupplierDto> fetchSuppliersByCriteria(String location, String natureOfBusiness,
			String manufacturingProcess, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("companyName").ascending());
		List<Supplier> suppliersList = supplierRepo.fetchAllSuppliersByFilter
				(location, natureOfBusiness, manufacturingProcess, pageable);
		return suppliersList.stream().map(this::convertToDTO).toList();
	}
	
	private SupplierDto convertToDTO(Supplier supplier)	{
		SupplierDto supplierDto = new SupplierDto();
		supplierDto.setSupplierId(supplier.getSupplierId());
		supplierDto.setLocation(supplier.getLocation());
		supplierDto.setCompanyName(supplier.getCompanyName());
		supplierDto.setManufacturingProcesses(supplier.getManufacturingProcess().toString());
		supplierDto.setNatureOfBusiness(supplier.getNatureOfBusiness().toString());
		supplierDto.setWebsite(supplier.getWebsite());
		return supplierDto;
	}

}
