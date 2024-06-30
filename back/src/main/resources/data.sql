INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');
              
              
              
    INSERT INTO Doktor (ime, prezime, specijalizacija) VALUES 
('Marko', 'Marković', 'Ortopedija'),
('Ana', 'Anić','Kardiologija'),
('Jovan', 'Jovanović', 'Dermatologija');          
              
              INSERT INTO Pacijent (ime, prezime,mesto, datum_Rodjenja, pol, doktor_id, LBO) VALUES 
('Milica', 'Milivojević','Mali Vrnjci', '1995-05-15', 'ZENSKI', 1, '12345678901235'),
('Nikola', 'Nikolić','Novi Sad', '1980-12-10', 'MUSKI', 2, '12345678901236'),
('Jelena', 'Jovanović','Dimitrovgrad', '1973-09-22', 'ZENSKI', 2, '12345678901237');


INSERT INTO Pregled (pacijent_Id, pocetak_Pregleda,kraj_pregleda,simptomi, doktor_id) VALUES 
(2, '2024-03-24 09:30:00','2024-03-24 10:00:00','bol u stomaku',  2);

