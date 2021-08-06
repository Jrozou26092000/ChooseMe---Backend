package com.chooseme.proyect.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.chooseme.proyect.dto.CommentsDTO;
import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Impressions;
import com.chooseme.proyect.entities.Products;

@CrossOrigin(origins = "*", maxAge = 3600)
public interface ProductsController {

	public Iterable<Products> getProductByName(ProductsFilters Filters);

	public Boolean productTest();

	public Iterable<CommentsDTO> productView(Products product);
	
	public Iterable<CommentsDTO> getCommentsByProductsId(int id, int page);

	public boolean productNewReview(Comments comment, String Authorization);
	
	public void productNewImpression (Impressions impression, String Authorization);
	
	//public boolean addProducts(Products newproducts);

}
