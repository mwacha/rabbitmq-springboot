DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (
  client_id               VARCHAR(256) PRIMARY KEY,
  resource_ids            VARCHAR(256),
  client_secret           VARCHAR(256),
  scope                   VARCHAR(256),
  authorized_grant_types  VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities             VARCHAR(256),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(256)
);

DROP TABLE IF EXISTS oauth_client_token;
CREATE TABLE oauth_client_token (
  token_id          VARCHAR(256),
  token             bytea,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name         VARCHAR(256),
  client_id         VARCHAR(256)
);

DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id          VARCHAR(256),
  token             bytea,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name         VARCHAR(256),
  client_id         VARCHAR(256),
  authentication    bytea,
  refresh_token     VARCHAR(256),
  expiration        TIMESTAMP NULL DEFAULT NULL
);

DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id       VARCHAR(256),
  token          bytea,
  authentication bytea
);

DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code (
  code           VARCHAR(256),
  authentication bytea
);

DROP TABLE IF EXISTS oauth_approvals;
CREATE TABLE oauth_approvals (
  userId         VARCHAR(256),
  clientId       VARCHAR(256),
  scope          VARCHAR(256),
  status         VARCHAR(10),
  expiresAt      TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

-- customized oauth2_client_details table
DROP TABLE IF EXISTS oauth2_client_details;
CREATE TABLE oauth2_client_details (
  appId                  VARCHAR(256) PRIMARY KEY,
  resourceIds            VARCHAR(256),
  appSecret              VARCHAR(256),
  scope                  VARCHAR(256),
  grantTypes             VARCHAR(256),
  redirectUrl            VARCHAR(256),
  authorities            VARCHAR(256),
  access_token_validity  INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation  VARCHAR(4096),
  autoApproveScopes      VARCHAR(256)
);

CREATE OR REPLACE FUNCTION trigger_set_updated_at()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TABLE IF EXISTS profiles;
CREATE TABLE profiles (
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  tenant_id bigint NOT NULL,
  profile_name varchar(50) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  updated_at TIMESTAMP WITH TIME ZONE NULL,
  deleted_at TIMESTAMP WITH TIME ZONE,
  PRIMARY KEY (id),
  UNIQUE (profile_name)
);

CREATE TABLE users(
  id UUID NOT NULL DEFAULT uuid_generate_v4() ,
  profile_id UUID NOT NULL,
  tenant_id bigint NOT NULL,
  email varchar(100) DEFAULT NULL,
  pass varchar(200) DEFAULT NULL,
  user_name varchar(200) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  updated_at TIMESTAMP WITH TIME ZONE NULL,
  deleted_at TIMESTAMP WITH TIME ZONE,
  PRIMARY KEY (id),
  CONSTRAINT FK_USER_PROF FOREIGN KEY (profile_id) REFERENCES profiles (id)
  );



DROP TRIGGER IF EXISTS users_tgr_bu ON public.users;

CREATE TRIGGER users_tgr_bu
    BEFORE UPDATE
    ON public.users
    FOR EACH ROW
EXECUTE PROCEDURE public.trigger_set_updated_at();

DROP TRIGGER IF EXISTS users_tgr_bu ON public.profiles;

CREATE TRIGGER profiles_tgr_bu
    BEFORE UPDATE
    ON public.profiles
    FOR EACH ROW
EXECUTE PROCEDURE public.trigger_set_updated_at();