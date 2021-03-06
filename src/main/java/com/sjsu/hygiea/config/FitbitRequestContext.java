/**
 * 
 */
package com.sjsu.hygiea.config;

import org.joda.time.LocalDate;

import com.fitbit.api.client.FitbitApiClientAgent;
import com.fitbit.api.client.LocalUserDetail;
import com.fitbit.api.client.service.FitbitAPIClientService;

/**
 * @author bhargav
 *
 */
public class FitbitRequestContext {

    private LocalDate parsedLocalDate = new LocalDate();
    private FitbitAPIClientService<FitbitApiClientAgent> apiClientService;
    private LocalUserDetail ourUser;

    public LocalDate getParsedLocalDate() {
        return parsedLocalDate;
    }

    public FitbitAPIClientService<FitbitApiClientAgent> getApiClientService() {
        return apiClientService;
    }

    public void setApiClientService(FitbitAPIClientService<FitbitApiClientAgent> apiClientService) {
        this.apiClientService = apiClientService;
    }

    public LocalUserDetail getOurUser() {
        return ourUser;
    }

    public void setOurUser(LocalUserDetail ourUser) {
        this.ourUser = ourUser;
    }
	
}
