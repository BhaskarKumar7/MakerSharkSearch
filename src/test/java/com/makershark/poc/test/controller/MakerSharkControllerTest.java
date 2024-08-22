package com.makershark.poc.test.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.makershark.poc.controller.MakerSharkController;
import com.makershark.poc.dtos.ServerResponseDto;
import com.makershark.poc.dtos.SupplierDto;
import com.makershark.poc.service.SupplierService;

@ExtendWith(MockitoExtension.class)
public class MakerSharkControllerTest {

	@Mock
	SupplierService supplierService;
	@InjectMocks
	MakerSharkController makerSharkController;
	
	@Test
	void testcreateSupplier()	{
		ServerResponseDto dto = new ServerResponseDto("success", "200");
		Mockito.when(supplierService.saveSupplier(Mockito.any())).thenReturn(dto);
		ResponseEntity<ServerResponseDto> response = makerSharkController.createSupplier(new SupplierDto());
		assertNotNull(response);
	}
	
	@Test
	void testGetAllSuppliersWithEmptyData() {
		List<SupplierDto> list = new ArrayList<>();
		Mockito.when(supplierService.fetchSuppliersByCriteria(Mockito.anyString(),
				Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(list);
		ResponseEntity<?> resp = makerSharkController.getAllSuppliersByCriteria("Kolkata", "SMALL_SCALE", "COATING", "0", "10");
		assertNotNull(resp);
	}
	
	@Test
	void testGetAllSuppliersWithData() {
		List<SupplierDto> list = new ArrayList<>();
		list.add(new SupplierDto());
		Mockito.when(supplierService.fetchSuppliersByCriteria(Mockito.anyString(),
				Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(list);
		ResponseEntity<?> resp = makerSharkController.getAllSuppliersByCriteria("Kolkata", "SMALL_SCALE", "COATING", "0", "10");
		assertNotNull(resp);
	}
}
