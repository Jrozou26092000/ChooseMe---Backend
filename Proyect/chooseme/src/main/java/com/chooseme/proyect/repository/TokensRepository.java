package com.chooseme.proyect.repository;

import org.springframework.data.repository.CrudRepository;

import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.entities.Tokens;

public interface TokensRepository extends CrudRepository<Tokens, Integer> {
	

}
