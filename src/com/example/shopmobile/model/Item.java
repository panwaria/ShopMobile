package com.example.shopmobile.model;

import java.io.Serializable;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a single item in catalog.
 * 
 * @author excelsior
 * 
 */
public class Item implements Serializable /*, Parcelable */
{
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
