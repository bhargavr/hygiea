/**
 * 
 */
package com.sjsu.hygiea.rest;

import javax.inject.Inject;

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


//import org.springframework.core.env.Environment;

/**
 * @author bhargav
 * 
 */
@RestController
public class FetchUserProfileController
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

	private final AccountDao accountDao;

	@Inject
	public FetchUserProfileController(final AccountDao accountDao)
	{
		this.accountDao = accountDao;
	}

	@RequestMapping("/fetchProfile")
	public UserInfo fetchProfile(@RequestParam(value = "oauth_token", defaultValue = "World") final String oauth_token,
			@RequestParam(value = "oauth_verifier", defaultValue = "World") final String oauth_verifier)
	{

		final FitbitAPIClientService<FitbitApiClientAgent> apiClientService = new FitbitAPIClientService<FitbitApiClientAgent>(
				new FitbitApiClientAgent(apiBaseUrl, fitbitSiteBaseUrl, credentialsCache), clientConsumerKey, clientSecret,
				credentialsCache, entityCache, subscriptionStore);

		final String tempTokenReceived = oauth_token;
		final String tempTokenVerifier = oauth_verifier;
		final LocalUserDetail localUser = new LocalUserDetail("-");

		UserInfo userInfo = null;

		final APIResourceCredentials arc = new APIResourceCredentials("-", null, null);
		arc.setAccessToken(tempTokenReceived);
		arc.setAccessTokenSecret(tempTokenVerifier);
		apiClientService.saveResourceCredentials(localUser, arc);
		apiClientService.getClient().getCredentialsCache().saveResourceCredentials(localUser, arc);

		try
		{
			userInfo = apiClientService.getClient().getUserInfo(localUser);

			final Account user = new Account(userInfo.getDisplayName(), userInfo.getDisplayName(), userInfo.getDisplayName(),
					userInfo.getEncodedId(), "", "", "", "", "", "");

			try
			{
				accountDao.createAccount(user);
			}
			catch (final UsernameAlreadyInUseException e)
			{
				e.printStackTrace();
			}

		}
		catch (final FitbitAPIException e)
		{
			e.printStackTrace();
		}


		return userInfo;
	}

}
