package com.makershark.poc.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.makershark.poc.dtos.ServerResponseDto;
import com.makershark.poc.dtos.SupplierDto;
import com.makershark.poc.entities.Supplier;
import com.makershark.poc.enums.ManufacturingProcessEnum;
import com.makershark.poc.enums.NatureOfBusinessEnum;
import com.makershark.poc.repositories.SupplierRepository;
import com.makershark.poc.service.impl.SupplierServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceImplTest {

	@Mock
	SupplierRepository supplierRepo;
	@InjectMocks
	SupplierServiceImpl supplierServiceImpl;
	
	@Test
	void testSaveNewSupplier() {
		Supplier mockSupplier = new Supplier();
		mockSupplier.setSupplierId(134534534l);
		Mockito.when(supplierRepo.findByLocationAndNatureOfBusinessAndManufacturingProcess
				(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(null);
		Mockito.when(supplierRepo.save(Mockito.any())).thenReturn(mockSupplier);
		SupplierDto dto = new SupplierDto();
		dto.setLocation("pune");
		dto.setNatureOfBusiness("SMALL_SCALE");
		dto.setManufacturingProcesses("MOULDING");
		ServerResponseDto responseDto = supplierServiceImpl.saveSupplier(dto);
		assertNotNull(responseDto);
	}
	
	@Test
	void testSaveExistingSupplier() {
		Supplier mockSupplier = new Supplier();
		Mockito.when(supplierRepo.findByLocationAndNatureOfBusinessAndManufacturingProcess
				(Mockito.anyString(), Mockito.any() , Mockito.any())).thenReturn(mockSupplier);
		SupplierDto dto = new SupplierDto();
		dto.setLocation("pune");
		dto.setNatureOfBusiness("SMALL_SCALE");
		dto.setManufacturingProcesses("MOULDING");
		ServerResponseDto responseDto = supplierServiceImpl.saveSupplier(dto);
		assertNotNull(responseDto);
	}
	
	@Test
	void testSaveEmptySupplier() {
		ServerResponseDto responseDto = supplierServiceImpl.saveSupplier(null);
		assertNotNull(responseDto);
	}
	
	@Test
	void testFetchSuppliersByCriteria() {
		List<Supplier> mockList = new ArrayList<>();
		Supplier supplier = new Supplier();
		supplier.setManufacturingProcess(ManufacturingProcessEnum.valueOf("MOULDING"));
		supplier.setNatureOfBusiness(NatureOfBusinessEnum.valueOf("SMALL_SCALE"));
		mockList.add(supplier);
		Mockito.when(supplierRepo.fetchAllSuppliersByFilter
				(Mockito.anyString(),Mockito.any() , Mockito.any(), Mockito.any())).thenReturn(mockList);
		List<SupplierDto> mockDTOsList = supplierServiceImpl.fetchSuppliersByCriteria("Pune", "SMALL_SCALE", "MOULDING", 0, 10);
		assertNotNull(mockDTOsList);
	}
	
}
