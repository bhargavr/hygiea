/**
 * 
 */
package com.sjsu.hygiea.dto;

/**
 * @author ankur
 * 
 */
public class Reward
{

	private String name;

	private String points;

	private String sku;

	private String path;


	public Reward(String name,String points,String sku,String path)
	{
		this.name = name;
		this.points = points;
		this.sku = sku;
		this.path = path;
	}

	public String getName()
	{
		return name;
	}

	public String getPoints()
	{
		return points;
	}

	/**
	 * @return the displayName
	 */
	public String getSku()
	{
		return sku;
	}

	/**
	 * @return the user_id
	 */
	public String getPath()
	{
		return path;
	}


}
