package com.abc.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abc.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update Product p set p.pname=:pname where p.pid=:pid")
	public int updateProduct(@Param("pname") String pname,@Param("pid") int pid);

}
