package com.example.shopmobile.model;

import java.util.List;
import java.util.Map;

import com.example.shopmobile.Constants;
import com.google.common.collect.Lists;

/**
 * Represents a single item in catalog.
 * 
 * @author excelsior
 *
 */
public class Item {
	private int id;
	private Map<String, String> attrValuesMap;
	
	public Item(int id, Map<String, String> attrValuesMap) {
		super();
		this.id = id;
		this.attrValuesMap = attrValuesMap;
	}
	
	public int getId() {
		return id;
	}

	public Map<String, String> getAttrValuesMap() {
		return attrValuesMap;
	}
	
}
