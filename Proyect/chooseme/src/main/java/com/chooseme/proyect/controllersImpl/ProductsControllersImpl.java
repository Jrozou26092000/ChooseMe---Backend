package com.chooseme.proyect.controllersImpl;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chooseme.proyect.controllers.ProductsController;
import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.ProductToFront;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.service.ProductsService;

import utils.Exceptions.ApiUnprocessableEntity;


@RestController
public class ProductsControllersImpl implements ProductsController {
	
	@Autowired
	ProductsService productService;
	Products product;
	@Override
	@RequestMapping(value = "/products/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Products> getProductByName(@RequestBody ProductsFilters filter) {
		
		if(!(filter.getCategory() == null || filter.getName() == null)) {
			return productService.findProductByCategory(filter);
		}
		else if(!(filter.getStars_punctuation() == 0)) {
			
			return productService.findProductByScore(filter.getStars_punctuation(), filter.getStars_punctuation()+1);
		}
		else if(!(filter.getCreate_at() == null)) {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			return productService.findByDate(timeStamp, timeStamp);
		}
		
		return null;
	}
	

	@Override
	@RequestMapping(value = "/products/test", method = RequestMethod.GET, produces = "application/json")
	public Boolean producttest() {
		return true;
	}
}
