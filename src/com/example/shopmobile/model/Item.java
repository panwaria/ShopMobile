package com.example.shopmobile.model;

import java.util.Map;

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

	public Object getAttrValue(String attrName)
	{
		Object value = null;
		if(attrValuesMap.containsKey(attrName)) {
			value = attrValuesMap.get(attrName);
		}
		return value;
	}
}
