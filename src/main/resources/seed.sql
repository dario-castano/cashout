TRUNCATE "system".users CASCADE;

INSERT INTO "system".users(id, "name", balance) VALUES(1, 'Alice', 100900.0);
INSERT INTO "system".users(id, "name", balance) VALUES(2, 'Bob', 200.22);
INSERT INTO "system".users(id, "name", balance) VALUES(3, 'Charlie', 300000.34);
INSERT INTO "system".users(id, "name", balance) VALUES(4, 'David', 40055.567);
INSERT INTO "system".users(id, "name", balance) VALUES(5, 'Eve', 500.1);

INSERT INTO "system".cashouts (amount, user_id) VALUES(100.0, 1);
INSERT INTO "system".cashouts (amount, user_id) VALUES(200.34, 1);
INSERT INTO "system".cashouts (amount, user_id) VALUES(3.5, 1);