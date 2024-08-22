package com.makershark.poc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makershark.poc.entities.Supplier;
import com.makershark.poc.enums.ManufacturingProcessEnum;
import com.makershark.poc.enums.NatureOfBusinessEnum;

public interface SupplierRepository extends JpaRepository<Supplier,Long>, CustomDBRepository {

	Supplier findByLocationAndNatureOfBusinessAndManufacturingProcess
	(String location, NatureOfBusinessEnum natureOfBusiness, ManufacturingProcessEnum manufacturingProcess);
}
