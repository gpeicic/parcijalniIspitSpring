
insert into users (id, username, password)
values
    (1, 'user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
    (2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'); -- password = admin

insert into authority (id, authority_name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER');

insert into users_authority (user_id, authority_id)
values
    (1, 2),
    (2, 1);

INSERT INTO Polaznik (ime, prezime) VALUES
('Marko', 'Marković'),
('Ana', 'Anić');

INSERT INTO program_obrazovanja (naziv, csvet) VALUES
                                                  ('Java Programiranje', 5),
                                                  ('Web Dizajn', 7);


INSERT INTO upis (program_obrazovanja_program_obrazovanja_id, polaznik_polaznik_id) VALUES
 (1, 1),
 (2, 2);