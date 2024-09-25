CREATE TABLE IF NOT EXISTS system.users (
	id bigserial NOT NULL,
	name varchar NOT NULL,
	balance decimal DEFAULT 0.0 NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id),
	CONSTRAINT users_unique UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS system.cashouts (
	id bigserial NOT NULL,
	amount decimal DEFAULT 0.0 NOT NULL,
	user_id bigint NOT NULL,
	CONSTRAINT cashouts_pk PRIMARY KEY (id),
	CONSTRAINT cashouts_users_fk FOREIGN KEY (user_id) REFERENCES system.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);