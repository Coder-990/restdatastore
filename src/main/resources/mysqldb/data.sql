INSERT INTO datastock.firme (OIBFirme, NazivFirme)
VALUES ('45485474525', 'Prime Software'),
       ('54758545965', 'KoderCro'),
       ('58651000214', 'TechFoot'),
       ('02013025652', 'KiloByte'),
       ('47459652365', 'CyberTech'),
       ('54526589110', 'Luminum'),
       ('45125475856', 'NewCompany');

INSERT INTO datastock.izdatnica (IDFirme, Datum)
VALUES (1, '2022-02-18 00:00:00'),
       (2, '2022-02-26 00:00:00'),
       (3, '2022-03-04 00:00:00');

INSERT INTO datastock.primka (IDFirme, Datum)
VALUES (4, '2022-02-01 00:00:00'),
       (5, '2022-02-23 00:00:00'),
       (6, '2022-01-04 00:00:00'),
       (7, '2022-02-01 00:00:00'),
       (1, '2022-02-18 00:00:00');

INSERT INTO datastock.roba (NazivArtikla, Kolicina, Cijena, Opis, Jmj)
VALUES ('CPU_AMDx2A4_4020', 10, 215, 'Procesor AMD Richland', 'kom'),
       ('CPU_AMDA6_9500', 10, 393, 'Codename: Bristol Ridge', 'kom'),
       ('CPU_AMDx4FX_4300', 10, 425, 'Codename: Vishera', 'kom'),
       ('CPU_AMDx4A8_7650K', 10, 599, 'Codename: Kaveri', 'kom'),
       ('CPU_AMD_a10_9700', 10, 760, 'Codename: Bristol', 'kom'),
       ('CPU_AMDx8FX_8300', 10, 660, 'Codename: Vishera', 'kom'),
       ('CPU_AMDx4FX_4300', 10, 425, 'Codename: Vishera', 'kom'),
       ('CPU_AMDRayzen5_1600X_BOX', 10, 1580, 'Type: (Zen)', 'kom'),
       ('CPU_AMD_Rayzen7_1800X_BOX', 10, 2589.99, 'Type:(Zen)', 'kom'),
       ('MBO_ASUSa68HM_K', 10, 290, 'ASUS A68HM-K, Soc FM2+ ', 'kom'),
       ('MBO_ASUSPrimeA320M_K', 10, 375, 'Chipset: AMD A320', 'kom'),
       ('MBO_ASUS_M5A97_R2.0', 10, 675, 'Chipset: AMD A320', 'kom'),
       ('MBO_ASUS_M5A97_LE_R2.0', 10, 475, 'Chipset: AMD A320', 'kom');

INSERT INTO datastock.stavkaizdatnice (IDIzdatnice, IDRobe, Kolicina, DatumStorno, Storno)
VALUES (1, 1, 2, '2022-02-21', true),
       (3, 2, 4, null, false),
       (2, 3, 4, '2022-02-21', true),
       (1, 4, 4, '2022-02-21', true),
       (2, 5, 2, '2022-02-21', true),
       (3, 6, 2, '2022-02-21', true),
       (3, 7, 2, null, false);

INSERT INTO datastock.stavkaprimke (IDPrimke, IDRobe, Kolicina, DatumStorno, Storno)
VALUES (5, 8, 5, null, false),
       (4, 9, 7, '2022-02-20', true),
       (3, 10, 1, null, false),
       (2, 11, 4, null, false),
       (1, 12, 4, '2022-02-21', true),
       (5, 13, 4, '2022-02-22', true);

INSERT INTO datastock.racun (userId, password)
VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3');
