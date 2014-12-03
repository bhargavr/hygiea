/**
 * 
 */
package com.sjsu.hygiea.rest;

import java.sql.SQLException;

import javax.inject.Inject;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitbit.api.client.FitbitAPIEntityCache;
import com.fitbit.api.client.FitbitApiCredentialsCache;
import com.fitbit.api.client.FitbitApiCredentialsCacheMapImpl;
import com.fitbit.api.client.FitbitApiEntityCacheMapImpl;
import com.fitbit.api.client.FitbitApiSubscriptionStorage;
import com.fitbit.api.client.FitbitApiSubscriptionStorageInMemoryImpl;
import com.fitbit.api.common.model.user.UserInfo;
import com.sjsu.hygiea.constants.ApplicationConstants;
import com.sjsu.hygiea.dao.AccountDao;
import com.sjsu.hygiea.dao.RewardDao;
import com.sjsu.hygiea.dto.Reward;


//import org.springframework.core.env.Environment;

/**
 * @author bhargav
 * 
 */
@RestController
public class NewRewardsController
{

	public static final String OAUTH_TOKEN = "oauth_token";
	public static final String OAUTH_VERIFIER = "oauth_verifier";

	//    @Autowired
	//    private Environment env;

	private final FitbitAPIEntityCache entityCache = new FitbitApiEntityCacheMapImpl();
	private final FitbitApiCredentialsCache credentialsCache = new FitbitApiCredentialsCacheMapImpl();
	private final FitbitApiSubscriptionStorage subscriptionStore = new FitbitApiSubscriptionStorageInMemoryImpl();

	//    private String apiBaseUrl = env.getProperty(ApplicationConstants.apiBaseUrl);
	//    private String fitbitSiteBaseUrl = env.getProperty(ApplicationConstants.fitbitSiteBaseUrl);
	//    private String clientConsumerKey = env.getProperty(ApplicationConstants.clientConsumerKey);
	//    private String clientSecret = env.getProperty(ApplicationConstants.clientSecret);

	private final String apiBaseUrl = ApplicationConstants.apiBaseUrl;
	private final String fitbitSiteBaseUrl = ApplicationConstants.fitbitSiteBaseUrl;
	private final String clientConsumerKey = ApplicationConstants.clientConsumerKey;
	private final String clientSecret = ApplicationConstants.clientSecret;

	private final RewardDao rewardDao;

	@Inject
	public NewRewardsController(final RewardDao rewardDao)
	{
		this.rewardDao = rewardDao;
	}
	
	@RequestMapping(value = "/createNewReward" , method = RequestMethod.POST)
    public @ResponseBody String createReward(@RequestBody String jsonString, Model model) throws JSONException, SQLException {
			
			System.out.println(jsonString);
			
			JSONObject rewardobj = new JSONObject(jsonString);
			
			/*
			 reward["sku"] = $("#rewardsku").val();
	    	reward["name"] = $("#rewardname").val();
	    	reward["points"] = $("#rewardpoints").val();
	    	reward["path"] = $("#rewardpath").val();
	    	reward["retailer"] = $("#rewardretailer").val();
	    	reward["expiration"] = $("#rewardexpiration").val();
			 */
			System.out.println(rewardobj.getString("name")+rewardobj.getString("points")+rewardobj.getString("sku")+
					rewardobj.getString("path")+rewardobj.getString("retailer")+rewardobj.getString("expiration"));
			
			Reward reward = new Reward(rewardobj.getString("name"),rewardobj.getString("points"),rewardobj.getString("sku"),
					rewardobj.getString("path"),rewardobj.getString("retailer"),rewardobj.getString("expiration"));
			
////    	orderobject.remove("storeID");   	
			rewardDao.createReward(reward);
//////    	int result = odao.insertOrder(storeID,orderobject.toString());
////    	System.out.println("New Order ID:  "+result);
////    	String orderID = Integer.toString(result);
////    	return orderID;
			return "success";
    }
	
	@RequestMapping(value = "/getRewards", method = RequestMethod.GET)
    String getRewards() 
	{	
			System.out.println("TRIGGERED GET");
			return "Success";
			
    }
	
	
	
//	@RequestMapping("/createNewReward")
//	public UserInfo createReward(@RequestParam(value = "oauth_token", defaultValue = "World") final String oauth_token,
//			@RequestParam(value = "oauth_verifier", defaultValue = "World") final String oauth_verifier)
//	{
//
//		final FitbitAPIClientService<FitbitApiClientAgent> apiClientService = new FitbitAPIClientService<FitbitApiClientAgent>(
//				new FitbitApiClientAgent(apiBaseUrl, fitbitSiteBaseUrl, credentialsCache), clientConsumerKey, clientSecret,
//				credentialsCache, entityCache, subscriptionStore);
//
//		final String tempTokenReceived = oauth_token;
//		final String tempTokenVerifier = oauth_verifier;
//		final LocalUserDetail localUser = new LocalUserDetail("-");
//
//		UserInfo userInfo = null;
//
//		final APIResourceCredentials arc = new APIResourceCredentials("-", null, null);
//		arc.setAccessToken(tempTokenReceived);
//		arc.setAccessTokenSecret(tempTokenVerifier);
//		apiClientService.saveResourceCredentials(localUser, arc);
//		apiClientService.getClient().getCredentialsCache().saveResourceCredentials(localUser, arc);
//
//		try
//		{
//			userInfo = apiClientService.getClient().getUserInfo(localUser);
//
//			final Account user = new Account(userInfo.getDisplayName(), userInfo.getDisplayName(), userInfo.getDisplayName(),
//					userInfo.getEncodedId(), "", "", "", "", "", "");
//
//			try
//			{
//				accountDao.createAccount(user);
//			}
//			catch (final UsernameAlreadyInUseException e)
//			{
//				e.printStackTrace();
//			}
//
//		}
//		catch (final FitbitAPIException e)
//		{
//			e.printStackTrace();
//		}
//
//
//		return userInfo;
//	}

}
