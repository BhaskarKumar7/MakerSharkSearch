package com.makershark.poc.entities;

import com.makershark.poc.constants.AppConstants;
import com.makershark.poc.enums.ManufacturingProcessEnum;
import com.makershark.poc.enums.NatureOfBusinessEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplier",schema = AppConstants.SCHEMA)
@Getter
@Setter
public class Supplier {

	@Id
	@SequenceGenerator(schema = AppConstants.SCHEMA,sequenceName = "seq_supplier", name = "supplier_seq_generator",
	 initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "supplier_seq_generator")
	private Long supplierId;
	private String companyName;
	private String website;
	private String location;
	@Enumerated(EnumType.STRING)
	private NatureOfBusinessEnum natureOfBusiness;
	@Enumerated(EnumType.STRING)
	private ManufacturingProcessEnum manufacturingProcess;
}
