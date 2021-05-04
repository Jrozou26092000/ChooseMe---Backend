package com.chooseme.proyect.controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;

import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Products;

@CrossOrigin(origins = "*", maxAge = 3600)
public interface ProductsController {



	public Iterable<Products> getProductByName(ProductsFilters Filters);


	public Boolean producttest();


	//public Iterable<Products> product_view(int id);


	public Iterable<Comments> product_view(Products product);





	public Iterable<Comments> products_id(int id, int page);



	public void product_newreview(Comments comment, String Authorization);


	//public boolean addProducts(Products newproducts);

}
