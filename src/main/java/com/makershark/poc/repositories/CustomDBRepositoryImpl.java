package com.makershark.poc.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.makershark.poc.entities.Supplier;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomDBRepositoryImpl implements CustomDBRepository {

	private final EntityManager em;

	@Override
	public List<Supplier> fetchAllSuppliersByFilter(String location, String natureOfBusiness,
			String manufacturingProcess, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Supplier> criteriaQuery = criteriaBuilder.createQuery(Supplier.class);
		// select * from supplier entity equivalent
		Root<Supplier> root = criteriaQuery.from(Supplier.class);
		List<Predicate> predicates = new ArrayList<>();
		if (location != null && !location.isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("location"), "%" + location + "%"));
		}
		if (natureOfBusiness != null && !natureOfBusiness.isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("natureOfBusiness"), "%" + natureOfBusiness + "%"));
		}
		if (manufacturingProcess != null && !manufacturingProcess.isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("manufacturingProcess"), "%" + manufacturingProcess + "%"));
		}
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		TypedQuery<Supplier> query = em.createQuery(criteriaQuery);
		query.setFirstResult((int) pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		return query.getResultList();
	}
}
