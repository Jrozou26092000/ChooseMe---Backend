package com.chooseme.proyect.serviceImpl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chooseme.proyect.dto.CommentsDTO;
import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.repository.CategoryRepository;
import com.chooseme.proyect.repository.CommentsRepository;
import com.chooseme.proyect.repository.ProductsRepository;
import com.chooseme.proyect.service.CategoriesService;
import com.chooseme.proyect.service.ProductsService;
import com.chooseme.proyect.util.ProductSorter;


@Service
public class ProductsServiceImpl implements ProductsService {
	@Autowired
	ProductsRepository productRepository;
	@Autowired
	CommentsRepository commentRepo;
	Iterable<Products> nameproductcheck;
	Iterable<Products> nameprod;
	CategoriesService categoryService;
	
	@Autowired
	CategoryRepository catRepo;
	Products product;
	
	
	@Override
	public Iterable<Products> findUserByPName(ProductsFilters filter) {

		String name = filter.getName();
						
		nameproductcheck =  productRepository.getProductByProductname(name);
		
		return nameproductcheck;
	}

	@Override
	public Iterable<Products> findProductByCategory(ProductsFilters filter) {
		

		if(!(filter.getName() == null)) {
			String nameorcat = filter.getName();
			nameprod = productRepository.getQuery(nameorcat);
		}
		
		return nameprod;
	}
	@Override
	public Iterable<Products> findProductByScore(double stars_punctuation, double d) {
		
		
		return productRepository.getByScore(stars_punctuation, d);
	}
	@Override
	public Iterable<Products> findByDate(String create_atStart, String create_atEnd) {
		
		return  productRepository.getByDate(create_atStart, create_atEnd);
	}

	@Override
	public Iterable<CommentsDTO> ProductView(Products product) {
		int id = product.getProduct_id();
		Iterable<Comments> comm = commentRepo.getById(id);
		Collection<CommentsDTO> commDTO = new HashSet<CommentsDTO>();
		comm.forEach((c) -> {
			commDTO.add(new CommentsDTO(c));
		});
		return commDTO;
	}

	@Override
	public Iterable<Products> getProductsFilterd(ProductsFilters filter) {
		Set<Products> searchSet = new HashSet<Products>();
		
		boolean firstFilter = true; 

		if(!(filter.getName() == null)) {
			Set<Products> tempSet = new HashSet<Products>();
			findProductByCategory(filter).forEach((e) -> {
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
			findProductByScore(Double.parseDouble(filter.getStars_puntuation()), Double.parseDouble(filter.getStars_puntuation())+1).forEach((e) -> {
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
			findByDate(fromStamp,nowStamp).forEach((e) -> {
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
	public Products searchById(int id) {
		return productRepository.searchByIdx(id);
	}
	
	
	
}
