INSERT INTO CONTACT.STATUS (ID, NAME) VALUES (1, 'Active');
INSERT INTO CONTACT.STATUS (ID, NAME) VALUES (2, 'Inactive');
-- INSERT INTO STATUS (ID, NAME) VALUES (3, 'WTF');

-- INSERT INTO companies (name, phone, web_url,status_id) VALUES
--   ('Lokesh', '567890', 'abc@gmail.com',1),
--   ('Deja', '234567', 'xyz@email.com',2),
--   ('Caption', '4567890', 'cap@marvel.com',1);


--  in schema.sql file

-- CREATE TABLE status (
--   id INT AUTO_INCREMENT  PRIMARY KEY,
--   name VARCHAR(250) NOT NULL
-- );
--
-- CREATE TABLE companies (
--   id INT AUTO_INCREMENT  PRIMARY KEY,
--   name VARCHAR(250) NOT NULL,
--   phone VARCHAR(250) NOT NULL,
--   web_url VARCHAR(250) DEFAULT NULL,
--   status_id INT
-- );