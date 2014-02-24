package com.example.shopmobile.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Represents a single item in catalog.
 * 
 * @author excelsior
 * 
 */
public class Item implements Serializable /*, Parcelable */
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6933477053497483342L;

	private int mID;
	private Map<String, String> mAttrValuesMap;

	public Item(int id, Map<String, String> attrValuesMap)
	{
		super();
		this.mID = id;
		this.mAttrValuesMap = attrValuesMap;
	}

	public int getId()
	{
		return mID;
	}

	public Map<String, String> getAttrValuesMap()
	{
		return mAttrValuesMap;
	}

	public Object getAttrValue(String attrName)
	{
		Object value = null;
		if(mAttrValuesMap.containsKey(attrName)) {
			value = mAttrValuesMap.get(attrName);
		}
		return value;
	}
}
