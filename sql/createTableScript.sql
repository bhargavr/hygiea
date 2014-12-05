  CREATE  TABLE hyg_user (
  user_id int NOT NULL AUTO_INCREMENT,
  create_date DATE NULL,
  userName VARCHAR(80) NOT NULL,
  password VARCHAR(80) NOT NULL,
  oauthToken VARCHAR(120) NULL,
  oauthSecret VARCHAR(120) NULL,
  displayName VARCHAR(120) NULL,
  cluster VARCHAR(120) NULL, 
  personal_reward VARCHAR(120) NULL, 
  community_reward VARCHAR(120) NULL, 
  predicted_avg VARCHAR(120) NULL,
  wr_d_id VARCHAR(120) NULL,
  PRIMARY KEY (user_id),
  UNIQUE (userName));