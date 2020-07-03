package com.unisoma.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unisoma.model.Readjustment;

@Repository
public interface ReadjustmentRepository extends JpaRepository<Readjustment, Long>{

	@Query("select c from Readjustment c  where c.id = :id")
	Optional<Readjustment> findById(@Param("id") Long id);
	
	@Query("select c from Readjustment c  where :salary BETWEEN c.minSalary AND c.maxSalary")
	Optional<Readjustment> findBySalary(@Param("salary") Double id);
		
	@Query("select c from Readjustment c")
	List<Readjustment> findAll();
	
	@Query("select c from Readjustment c")
	Page<Readjustment> findAllPage(Pageable pageable);
}
