package com.chooseme.proyect.controllersImpl;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chooseme.proyect.controllers.ProductsController;
import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.service.ProductsService;



@RestController
public class ProductsControllersImpl implements ProductsController {
	
	@Autowired
	ProductsService productService;
	Products product;
	@Override
	@RequestMapping(value = "/products/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Products> getProductByName(@RequestBody ProductsFilters filter) {

		if(!(filter.getName() == null)) {
			return productService.findProductByCategory(filter);
		}
		else if(!(filter.getStars_puntuation() == 0)) {
			
			return productService.findProductByScore(filter.getStars_puntuation(), filter.getStars_puntuation()+1);
		}
		else if(!(filter.getCreate_at() == null)) {
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			int dias = 30;
			Date temp = (new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * dias)));
			System.out.println(new SimpleDateFormat("yyy-MM-dd").format(temp));
			return productService.findByDate(filter.getCreate_at(),timeStamp);
		}
		
		return null;
	}
	

	@Override
	@RequestMapping(value = "/products/test", method = RequestMethod.GET, produces = "application/json")
	public Boolean producttest() {
		return true;
	}
}
