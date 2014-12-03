package com.sjsu.hygiea.dto;

public class PersonalRewards
{
	private final String retailer;

	private final String name;

	private final String description;

	private final String percentageThreshold;

	public PersonalRewards(final String retailer, final String name, final String description, final String percentageThreshold)
	{
		this.retailer = retailer;
		this.name = name;
		this.description = description;
		this.percentageThreshold = percentageThreshold;
	}

	/**
	 * @return the retailer
	 */
	public String getRetailer()
	{
		return retailer;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return the percentageThreshold
	 */
	public String getPercentageThreshold()
	{
		return percentageThreshold;
	}

}
