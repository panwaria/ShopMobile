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

//	@Override
//	public int describeContents()
//	{
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void writeToParcel(Parcel dest, int flags)
//	{
//		dest.writeInt(mID);
//		dest.writeMap(mAttrValuesMap);
//	}
//
//	private void readFromParcel(Parcel in)
//	{
//		mID = in.readInt();
////		mAttrValuesMap = in.readMap(outVal, loader);
//	}

}
