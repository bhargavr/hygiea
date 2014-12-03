package com.sjsu.hygiea.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjsu.hygiea.dao.AccumulatedAwardsDao;
import com.sjsu.hygiea.dto.AccumulatedAwards;
import com.sjsu.hygiea.exception.UsernameAlreadyInUseException;
//import org.springframework.core.env.Environment;



public class AddRewardsController
{

	private final AccumulatedAwardsDao awardDao;

	@Inject
	public AddRewardsController(final AccumulatedAwardsDao awardDao)
	{
		this.awardDao = awardDao;
	}

	@RequestMapping("/insertRewards")
	public void insertRewards(@RequestParam(value = "user_id", defaultValue = "") final String user_id,
			@RequestParam(value = "rewards_id", defaultValue = "") final String rewards_id,
			@RequestParam(value = "retailer_id", defaultValue = "") final String retailer_id,
			@RequestParam(value = "expiry_date", defaultValue = "") final String expiry_date,
			@RequestParam(value = "reward_points", defaultValue = "") final String reward_points)
	{


		final AccumulatedAwards award = new AccumulatedAwards(user_id, rewards_id, retailer_id, "", expiry_date, reward_points);

		try
		{
			awardDao.createAwards(award);
		}
		catch (final UsernameAlreadyInUseException e)
		{
			e.printStackTrace();
		}



	}
}
