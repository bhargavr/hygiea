  CREATE  TABLE hyg_user (
  user_id VARCHAR(80) NOT NULL ,
  create_date DATE NULL,
  userName VARCHAR(80) NOT NULL,
  password VARCHAR(80) NOT NULL,
  oauthToken VARCHAR(120) NULL,
  oauthSecret VARCHAR(120) NULL,
  displayName VARCHAR(120) NULL,
  PRIMARY KEY (user_id));