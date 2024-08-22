package com.makershark.poc.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierDto {

	
	private Long supplierId;
	@NotBlank
	private String companyName;
	@NotBlank
	private String website;
	@NotBlank
	private String location;
	@NotBlank
	private String natureOfBusiness;
	@NotBlank
	private String manufacturingProcesses;
}
