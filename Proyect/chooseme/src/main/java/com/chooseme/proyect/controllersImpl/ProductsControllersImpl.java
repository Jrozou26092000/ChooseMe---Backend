package com.chooseme.proyect.controllersImpl;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.chooseme.proyect.util.ProductSorter;



@RestController
public class ProductsControllersImpl implements ProductsController {
	
	@Autowired
	ProductsService productService;
	Products product;
	@Override
	@RequestMapping(value = "/products/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Products> getProductByName(@RequestBody ProductsFilters filter) {
		
		Set<Products> searchSet = new HashSet<Products>();

		if(!(filter.getName() == null)) {
			Set<Products> tempSet = new HashSet<Products>();
			productService.findProductByCategory(filter).forEach((e) -> {
				tempSet.add(e);
			});
			if (searchSet.isEmpty()) {
				searchSet.addAll(tempSet);
			} else {
				searchSet.retainAll(tempSet);
			}
		} 
		if(!(filter.getStars_puntuation() == 0)) {
			Set<Products> tempSet = new HashSet<Products>();
			productService.findProductByScore(filter.getStars_puntuation(), filter.getStars_puntuation()+1).forEach((e) -> {
				tempSet.add(e);
			});
			if (searchSet.isEmpty()) {
				searchSet.addAll(tempSet);
			} else {
				searchSet.retainAll(tempSet);
			}
		} 
		if(!(filter.getCreate_at() == null)) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -(Integer.parseInt(filter.getCreate_at())));
			String nowStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String fromStamp = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Set<Products> tempSet = new HashSet<Products>();
			productService.findByDate(fromStamp,nowStamp).forEach((e) -> {
				tempSet.add(e);
			});
			if (searchSet.isEmpty()) {
				searchSet.addAll(tempSet);
			} else {
				searchSet.retainAll(tempSet);
			}
		}
		
		if (searchSet.isEmpty()) {
			return null;
		}
		
		List<Products> retL = searchSet.stream().collect(Collectors.toList());
		Collections.sort(retL, new ProductSorter());
		return retL;
	}
	

	@Override
	@RequestMapping(value = "/products/test", method = RequestMethod.GET, produces = "application/json")
	public Boolean producttest() {
		return true;
	}
}
