--liquibase formatted sql

--changeset alexermakov:1
ALTER TABLE users_aud
DROP CONSTRAINT users_aud_username_key;

--changeset alexermakov:2
ALTER TABLE users_aud
ALTER COLUMN username DROP NOT NULL;