package com.chooseme.proyect.util;

import java.util.Comparator;

import com.chooseme.proyect.entities.Products;

public class ProductSorter implements Comparator<Products>{

	@Override
	public int compare(Products p1, Products p2) {
		return (int)(p2.getScore()*10) - (int)(p1.getScore()*10);
	}

}
