/**
 * 
 */
package com.sjsu.hygiea.dto;

/**
 * @author bhargav
 * 
 */
public class Account
{

	private final String username;

	private final String password;

	private final String displayName;

	private final String user_id;

	private final String oauthToken;

	private final String oauthSecret;

	private final String cluster;

	private final String personalReward;

	private final String communityReward;

	private final String predicted_avg;

	public Account(final String username, final String password, final String displayName, final String user_id,
			final String oauthToken, final String oauthSecret, final String cluster, final String personalReward,
			final String communityReward, final String predicted_avg)
	{
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.user_id = user_id;
		this.oauthToken = oauthToken;
		this.oauthSecret = oauthSecret;
		this.cluster = cluster;
		this.personalReward = personalReward;
		this.communityReward = communityReward;
		this.predicted_avg = predicted_avg;
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName()
	{
		return displayName;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id()
	{
		return user_id;
	}

	/**
	 * @return the oauthToken
	 */
	public String getOauthToken()
	{
		return oauthToken;
	}

	/**
	 * @return the oauthSecret
	 */
	public String getOauthSecret()
	{
		return oauthSecret;
	}

	/**
	 * @return the cluster
	 */
	public String getCluster()
	{
		return cluster;
	}

	/**
	 * @return the personalReward
	 */
	public String getPersonalReward()
	{
		return personalReward;
	}

	/**
	 * @return the communityReward
	 */
	public String getCommunityReward()
	{
		return communityReward;
	}

	/**
	 * @return the predicited_avg
	 */
	public String getPredicted_Avg()
	{
		return predicted_avg;
	}


}
