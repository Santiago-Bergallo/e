--INSERT INTO local_user(email, first_name, last_name, password, username, verified)
--VALUES("userA@junit.com", "userA-firstname", "userA-lastname", "$2y$10$AEpIOOYvweL02zRUMIBMiOQ91.dyS2P1pzjkZfeVzs9G5pL4rvHP.", "userA", true)
--
--INSERT INTO local_user(email, first_name, last_name, password, username, verified)
--VALUES("userB@junit.com", "userB-firstname", "userB-lastname", "$2y$10$AEpIOOYvweL02zRUMIBMiOQ91.dyS2P1pzjkZfeVzs9G5pL4rvHP.", "userB", false)
-- This file allows us to load static data into the test database before tests are run.

-- Passwords are in the format: Password<UserLetter>123. Unless specified otherwise.
-- Encrypted using https://www.javainuse.com/onlineBcrypt
INSERT INTO local_user (email, first_name, last_name, password, username, is_email_verified)
    VALUES ('UserA@junit.com', 'UserA-FirstName', 'UserA-LastName', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'UserA', true)
    , ('UserB@junit.com', 'UserB-FirstName', 'UserB-LastName', '$2a$10$TlYbg57fqOy/1LJjispkjuSIvFJXbh3fy0J9fvHnCpuntZOITAjVG', 'UserB', false);