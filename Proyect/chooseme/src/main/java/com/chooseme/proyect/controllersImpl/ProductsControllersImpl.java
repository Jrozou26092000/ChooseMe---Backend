package com.chooseme.proyect.controllersImpl;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chooseme.proyect.controllers.ProductsController;
import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.repository.CommentsRepository;
import com.chooseme.proyect.repository.ProductsRepository;
import com.chooseme.proyect.service.CommentsService;
import com.chooseme.proyect.service.ProductsService;
import com.chooseme.proyect.util.JwtUtil;
import com.chooseme.proyect.util.ProductSorter;



@RestController
public class ProductsControllersImpl implements ProductsController {
	
	@Autowired
	ProductsService productService;
	@Autowired
	CommentsRepository commentsRepo;
	@Autowired
	CommentsService commentsService;
	
	Products product;
	@Autowired
	JwtUtil jwtTokenUtil;
	
	@Override
	@RequestMapping(value = "/products/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Products> getProductByName(@RequestBody ProductsFilters filter) {
		
		Set<Products> searchSet = new HashSet<Products>();
		
		boolean firstFilter = true; 

		if(!(filter.getName() == null)) {
			Set<Products> tempSet = new HashSet<Products>();
			productService.findProductByCategory(filter).forEach((e) -> {
				tempSet.add(e);
			});
			if (firstFilter) {
				searchSet.addAll(tempSet);
				firstFilter = false;
			} else {
				searchSet.retainAll(tempSet);
			}
		} 
		if(!(filter.getStars_puntuation() == null)) {
			Set<Products> tempSet = new HashSet<Products>();
			productService.findProductByScore(Double.parseDouble(filter.getStars_puntuation()), Double.parseDouble(filter.getStars_puntuation())+1).forEach((e) -> {
				tempSet.add(e);
			});
			if (firstFilter) {
				searchSet.addAll(tempSet);
				firstFilter = false;
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
			if (firstFilter) {
				searchSet.addAll(tempSet);
				firstFilter = false;
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
	
	
	

	@Override
	@RequestMapping(value = "/product_view", produces = "application/json")
	public Iterable<Comments> product_view(@RequestBody Products product) {

		Iterable<Comments> p = productService.ProductView(product);
	
		return p;
		
	}

	@Override
	@RequestMapping(value = "/review/{id}/{page}", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Comments> products_id(@PathVariable("id") int id, @PathVariable("page") int page){

		return commentsRepo.findByIdx(id, page);
	}

	@Override
	@RequestMapping(value = "/product/newreview")
	public void product_newreview(@RequestBody Comments comment, @RequestHeader String Authorization) {
		
		String name = jwtTokenUtil.extractUsername(Authorization.substring(7));
		
		commentsService.newComment(comment, name);
	}
	
	/*@Override
	@RequestMapping(value = "/comments/up_down")*/
	
	
	




}
