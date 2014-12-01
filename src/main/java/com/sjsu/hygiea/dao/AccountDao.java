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
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.hygiea.dto.Account;
import com.sjsu.hygiea.exception.UsernameAlreadyInUseException;

/**
 * @author bhargav
 *
 */
@Repository
public class AccountDao {
	
	private final JdbcTemplate jdbcTemplate;
	
//	private final PasswordEncoder passwordEncoder;
	
	@Inject
	public AccountDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
//		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	public void createAccount(Account user) throws UsernameAlreadyInUseException {
		try {
			jdbcTemplate.update(
					"insert into hyg_user (user_id, create_date, userName, password, oauthToken, oauthSecret, displayName) values (?, ?, ?, ?, ?, ?, ?)",
					user.getUser_id(), new Date(), user.getUsername(),
					user.getPassword(), user.getOauthToken(), user.getOauthSecret(), user.getDisplayName());
		} catch (DuplicateKeyException e) {
			throw new UsernameAlreadyInUseException(user.getUsername());
		}
	}
	
	public Account findAccountByUsername(String userId) {
		return jdbcTemplate.queryForObject("select userName, displayName, user_id from hyg_user where user_id = ?",
				new RowMapper<Account>() {
					public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Account(rs.getString("userName"), null, rs.getString("displayName"), rs
								.getString("user_id"),null,null);
					}
				}, userId);
	}

}
