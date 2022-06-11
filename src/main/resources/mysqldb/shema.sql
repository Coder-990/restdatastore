drop database if exists  datastock;
create database datastock;
use datastock;

create table firme
(
    IDFirme   int  AUTO_INCREMENT
        primary key NOT NULL,
    OIBFirme   varchar(255) null,
    NazivFirme varchar(255) null
);

create table izdatnica
(
    IDIzdatnice  int  AUTO_INCREMENT
        primary key NOT NULL,
    IDFirme     int      null,
    Datum       datetime null,
    constraint izdatnica_firme__fk
        foreign key (IDFirme) references firme (IDFirme)
);

create table primka
(
    IDPrimke  int  AUTO_INCREMENT
        primary key NOT NULL,
    IDFirme  int      null,
    Datum    datetime null,
    constraint primka_firme__fk
        foreign key (IDFirme) references firme (IDFirme)
);

create table roba
(
    IDRobe      int  AUTO_INCREMENT
        primary key NOT NULL,
    NazivArtikla varchar(255) null,
    Kolicina     int          null,
    Cijena       double       null,
    Opis         mediumtext   null,
    Jmj          varchar(255) null
);

create table stavkaizdatnice
(
    IDStavkaIzdatnice  int  AUTO_INCREMENT
        primary key NOT NULL,
    IDIzdatnice       int null,
    IDRobe            int null,
    Kolicina          int null,
    Storno         boolean null,
    DatumStorno    datetime null,
    constraint stavkaizdatnice_izdatnica__fk
        foreign key (IDIzdatnice) references izdatnica (IDIzdatnice),
    constraint stavkaizdatnice_roba__fk
        foreign key (IDRobe) references roba (IDRobe)
);

create table stavkaprimke
(
    IDStavkaPrimke  int  AUTO_INCREMENT
        primary key NOT NULL,
    IDPrimke       int null,
    IDRobe         int null,
    Kolicina       int null,
    Storno         boolean null,
    DatumStorno    datetime null,
    constraint stavkaprimke_primka__fk
        foreign key (IDPrimke) references primka (IDPrimke),
    constraint stavkaprimke_roba__fk
        foreign key (IDRobe) references roba (IDRobe)
);

create table racun
(
    userId   varchar(255) not null
        primary key,
    password varchar(255) not null
);
