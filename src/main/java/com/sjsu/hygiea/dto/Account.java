/**
 * 
 */
package com.sjsu.hygiea.dto;

/**
 * @author bhargav
 *
 */
public class Account {
	
	private final String username;

	private final String password;

	private final String displayName;

	private final String user_id;
	
	private final String oauthToken;

	private final String oauthSecret;

	public Account(String username, String password, String displayName, String user_id, String oauthToken, String oauthSecret) {
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.user_id = user_id;
		this.oauthToken = oauthToken;
		this.oauthSecret = oauthSecret;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @return the oauthToken
	 */
	public String getOauthToken() {
		return oauthToken;
	}

	/**
	 * @return the oauthSecret
	 */
	public String getOauthSecret() {
		return oauthSecret;
	}


}
