package com.chooseme.proyect.controllersImpl;



import java.util.Collection;
import java.util.HashSet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chooseme.proyect.controllers.ProductsController;
import com.chooseme.proyect.dto.CommentsDTO;
import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Impressions;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.service.CommentsService;
import com.chooseme.proyect.service.ImpressionsService;
import com.chooseme.proyect.service.ProductsService;
import com.chooseme.proyect.util.JwtUtil;



@RestController
public class ProductsControllersImpl implements ProductsController {
	
	@Autowired
	ProductsService productService;
	@Autowired
	CommentsService commentsService;
	@Autowired
	ImpressionsService impressionsService;
	Products product;
	@Autowired
	JwtUtil jwtTokenUtil;
	
	@Override
	@RequestMapping(value = "/products/search/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Products> getProductByName(@RequestBody ProductsFilters filter) {
		
		return productService.getProductsFilterd(filter);
		
	}
	
	

	@Override
	@RequestMapping(value = "/products/test", method = RequestMethod.GET, produces = "application/json")
	public Boolean productTest() {
		return true;
	}
	
	
	

	@Override
	@RequestMapping(value = "/product_view", produces = "application/json")
	public Iterable<CommentsDTO> productView(@RequestBody Products product) {

		Iterable<CommentsDTO> comments = productService.ProductView(product);
		return comments;	
	}
	

	
	@Override
	@RequestMapping(value = "/review/{id}/{page}", method = RequestMethod.GET, produces = "application/json")
	public Iterable<CommentsDTO> getCommentsByProductsId(@PathVariable("id") int id, @PathVariable("page") int page){
		
		Iterable<Comments> comm = commentsService.findByProductId(id, page);
		Collection<CommentsDTO> commDTO = new HashSet<CommentsDTO>();
		comm.forEach((c) -> {
			commDTO.add(new CommentsDTO(c));
		});
		return commDTO;
	}


	
	
	@Override
	@RequestMapping(value = "/product/newreview")
	public boolean productNewReview(@RequestBody Comments comment, @RequestHeader String Authorization) {
		
		String name = jwtTokenUtil.extractUsername(Authorization.substring(7));
		return commentsService.newComment(comment, name);
	}
	
	
	
	@Override
	@RequestMapping(value = "/product/newimpression")
	public void productNewImpression (@RequestBody Impressions impression, @RequestHeader String Authorization) {
		
		String name = jwtTokenUtil.extractUsername(Authorization.substring(7));
		impressionsService.newImpression(impression, name);
	}
	
	




}
