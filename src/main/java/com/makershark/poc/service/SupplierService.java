package com.makershark.poc.service;

import java.util.List;

import com.makershark.poc.dtos.ServerResponseDto;
import com.makershark.poc.dtos.SupplierDto;

public interface SupplierService {

	public ServerResponseDto saveSupplier(SupplierDto supplierDto);
	public List<SupplierDto> fetchSuppliersByCriteria(
			String location, String natureOfBusiness, String manufacturingProcess,
			int page, int size);
}
