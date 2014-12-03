/**
 * 
 */
package com.sjsu.hygiea.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.hygiea.dto.Account;
import com.sjsu.hygiea.exception.UsernameAlreadyInUseException;


//import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author bhargav
 * 
 */
@Repository
public class AccountDao
{

	private final JdbcTemplate jdbcTemplate;

	//	private final PasswordEncoder passwordEncoder;

	@Inject
	public AccountDao(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
		//		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public void createAccount(final Account user) throws UsernameAlreadyInUseException
	{
		try
		{
			jdbcTemplate
					.update(
							"insert into hyg_user (user_id, create_date, userName, password, oauthToken, oauthSecret, displayName, cluster, personal_reward, community_reward, predicted_avg) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
							user.getUser_id(), new Date(), user.getUsername(), user.getPassword(), user.getOauthToken(),
							user.getOauthSecret(), user.getDisplayName(), user.getCluster(), user.getPersonalReward(),
							user.getCommunityReward(), user.getPredicted_Avg());
		}
		catch (final DuplicateKeyException e)
		{
			throw new UsernameAlreadyInUseException(user.getUsername());
		}
	}

	public Account findAccountByUsername(final String userId)
	{
		return jdbcTemplate.queryForObject("select userName, displayName, user_id from hyg_user where user_id = ?",
				new RowMapper<Account>()
				{
					public Account mapRow(final ResultSet rs, final int rowNum) throws SQLException
					{
						return new Account(rs.getString("userName"), null, rs.getString("displayName"), rs.getString("user_id"), null,
								null, null, null, null, null);
					}
				}, userId);
	}

}
