DROP TABLE IF EXISTS taskly_user CASCADE;
DROP TABLE IF EXISTS taskly_deal CASCADE;
DROP TYPE IF EXISTS  DEAL_STATUS;

CREATE TABLE IF NOT EXISTS taskly_user (
    user_id SERIAL PRIMARY KEY,
    user_login VARCHAR UNIQUE NOT NULL,
    user_email VARCHAR UNIQUE NOT NULL,
    user_password VARCHAR NOT NULL,
    registration_date TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER SEQUENCE taskly_user_user_id_seq RESTART WITH 3;


CREATE TABLE IF NOT EXISTS taskly_deal (
    deal_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NUll,
    deal_name VARCHAR NOT NULL,
    deal_description VARCHAR NOT NULL,
    deal_status VARCHAR NOT NULL DEFAULT 'IN_PROCESS',
    deal_date_start TIMESTAMP NOT NULL DEFAULT NOW(),
    deal_date_end TIMESTAMP,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES taskly_user (user_id) ON DELETE CASCADE
);
ALTER SEQUENCE taskly_deal_deal_id_seq RESTART WITH 5;



