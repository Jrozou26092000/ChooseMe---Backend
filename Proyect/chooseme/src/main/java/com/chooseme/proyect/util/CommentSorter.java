package com.chooseme.proyect.util;

import java.util.Comparator;

import com.chooseme.proyect.entities.Comments;


public class CommentSorter implements Comparator<Comments>{

	@Override
	public int compare(Comments p1, Comments p2) {
		return (int)(p2.getScore()*10) - (int)(p1.getScore()*10);
	}

}
