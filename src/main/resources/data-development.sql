drop table if exists oauth_client_details;
create table oauth_client_details 
	(client_id VARCHAR(200) PRIMARY KEY,resource_ids VARCHAR(256),client_secret VARCHAR(256),
	scope VARCHAR(256),authorized_grant_types VARCHAR(256),web_server_redirect_uri VARCHAR(256),authorities VARCHAR(256),
	access_token_validity INTEGER,refresh_token_validity INTEGER,additional_information TEXT(4096),autoapprove VARCHAR(256));

-- insert client details
INSERT INTO oauth_client_details (client_id,client_secret,scope,authorized_grant_types,authorities,access_token_validity,refresh_token_validity) VALUES 
	('front','$2a$10$aOOMDhSl/QKszJEx9A8Jiu1WOy.u6chNwj7ZGT28DrZ.eC932XCOm','read,write,trust','password,refresh_token','ROLE_CLIENT,ROLE_TRUSTED_CLIENT',300,2592000);

DELETE From user_permission;
DELETE From user;
DELETE From permission;
DELETE FROM person;

INSERT INTO person (id, first_name, last_name) VALUES 
	(1, 'Jonh', 'Nobody');

INSERT INTO `user` (id, email, enabled, password, pending, person_data_id) VALUES
	(1, 'jonh@nobody.com.br', b'0', '$2a$10$E2fyG8RuQUmwzqNqD8KBge6XCTyPKc1Gc55G.mlNcEmcuViW9CsbO', b'0', 1);