package com.makershark.poc.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.makershark.poc.entities.Supplier;

public interface CustomDBRepository {

	public List<Supplier> fetchAllSuppliersByFilter(String location, String natureOfBusiness,
			String manufacturingProcess, Pageable pageable);
}
