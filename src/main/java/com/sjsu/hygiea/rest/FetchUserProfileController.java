/**
 * 
 */
package com.sjsu.hygiea.rest;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitbit.api.FitbitAPIException;
import com.fitbit.api.client.FitbitAPIEntityCache;
import com.fitbit.api.client.FitbitApiClientAgent;
import com.fitbit.api.client.FitbitApiCredentialsCache;
import com.fitbit.api.client.FitbitApiCredentialsCacheMapImpl;
import com.fitbit.api.client.FitbitApiEntityCacheMapImpl;
import com.fitbit.api.client.FitbitApiSubscriptionStorage;
import com.fitbit.api.client.FitbitApiSubscriptionStorageInMemoryImpl;
import com.fitbit.api.client.LocalUserDetail;
import com.fitbit.api.client.service.FitbitAPIClientService;
import com.fitbit.api.common.model.user.UserInfo;
import com.fitbit.api.model.APIResourceCredentials;
import com.sjsu.hygiea.constants.ApplicationConstants;
import com.sjsu.hygiea.dao.AccountDao;
import com.sjsu.hygiea.dto.Account;
import com.sjsu.hygiea.exception.UsernameAlreadyInUseException;

/**
 * @author bhargav
 *
 */
@RestController
public class FetchUserProfileController {
	
    public static final String OAUTH_TOKEN = "oauth_token";
    public static final String OAUTH_VERIFIER = "oauth_verifier";
    
//    @Autowired
//    private Environment env;

    private FitbitAPIEntityCache entityCache = new FitbitApiEntityCacheMapImpl();
    private FitbitApiCredentialsCache credentialsCache = new FitbitApiCredentialsCacheMapImpl();
    private FitbitApiSubscriptionStorage subscriptionStore = new FitbitApiSubscriptionStorageInMemoryImpl();

//    private String apiBaseUrl = env.getProperty(ApplicationConstants.apiBaseUrl);
//    private String fitbitSiteBaseUrl = env.getProperty(ApplicationConstants.fitbitSiteBaseUrl);
//    private String clientConsumerKey = env.getProperty(ApplicationConstants.clientConsumerKey);
//    private String clientSecret = env.getProperty(ApplicationConstants.clientSecret);
    
    private String apiBaseUrl = ApplicationConstants.apiBaseUrl;
    private String fitbitSiteBaseUrl = ApplicationConstants.fitbitSiteBaseUrl;
    private String clientConsumerKey = ApplicationConstants.clientConsumerKey;
    private String clientSecret = ApplicationConstants.clientSecret;
    
	private final AccountDao accountDao;

	@Inject
	public FetchUserProfileController(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
    
    @RequestMapping("/fetchProfile")
    public UserInfo fetchProfile(@RequestParam(value="oauth_token", defaultValue="World") String oauth_token,@RequestParam(value="oauth_verifier", defaultValue="World") String oauth_verifier ) {
    	
        FitbitAPIClientService<FitbitApiClientAgent> apiClientService = new FitbitAPIClientService<FitbitApiClientAgent>(
                new FitbitApiClientAgent(apiBaseUrl, fitbitSiteBaseUrl, credentialsCache),
                clientConsumerKey,
                clientSecret,
                credentialsCache,
                entityCache,
                subscriptionStore
        );
        
        String tempTokenReceived = oauth_token;
        String tempTokenVerifier = oauth_verifier;
        LocalUserDetail localUser = new LocalUserDetail("-");
        
        UserInfo userInfo = null;

        APIResourceCredentials arc = new APIResourceCredentials("-", null, null);
        arc.setAccessToken(tempTokenReceived);
        arc.setAccessTokenSecret(tempTokenVerifier);
        apiClientService.saveResourceCredentials(localUser, arc);
        apiClientService.getClient().getCredentialsCache().saveResourceCredentials(localUser, arc);

        try {
			userInfo = apiClientService.getClient().getUserInfo(localUser);
			
			Account user = new Account(userInfo.getDisplayName(), userInfo.getDisplayName(), userInfo.getDisplayName(), userInfo.getEncodedId(), "", "");
			
			try {
				accountDao.createAccount(user);
			} catch (UsernameAlreadyInUseException e) {
				e.printStackTrace();
			}
			
		} catch (FitbitAPIException e) {
			e.printStackTrace();
		}
    	
    	
        return userInfo;
    }

}
