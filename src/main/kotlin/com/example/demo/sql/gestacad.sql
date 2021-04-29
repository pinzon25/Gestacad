SET GLOBAL time_zone = '+3:00';
DROP DATABASE IF EXISTS gestacad;
CREATE DATABASE gestacad;
#CREATE USER administrador1 IDENTIFIED BY 'admin1';
GRANT ALL PRIVILEGES ON gestacad.* TO administrador1 WITH GRANT OPTION;
USE gestacad;

CREATE TABLE families (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(64) NOT NULL,
    descripcio VARCHAR(128)
);

CREATE TABLE cicles (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_familia INTEGER NOT NULL,
    nom VARCHAR(64) NOT NULL,
    descripcio VARCHAR(128),
    FOREIGN KEY (id_familia)
        REFERENCES families (id)
);

CREATE TABLE moduls (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_cicle INTEGER NOT NULL,
    nom VARCHAR(50) NOT NULL,
    descripcio VARCHAR(128),
    FOREIGN KEY (id_cicle)
        REFERENCES cicles (id)
);

CREATE TABLE ufs (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_modul INTEGER NOT NULL,
    nom VARCHAR(64) NOT NULL,
    descripcio VARCHAR(128),
    FOREIGN KEY (id_modul)
        REFERENCES moduls (id)
);

CREATE TABLE grups (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_cicle INTEGER NOT NULL,
    nom VARCHAR(64) NOT NULL,
    descripcio VARCHAR(128),
    FOREIGN KEY (id_cicle)
        REFERENCES cicles (id)
);

CREATE TABLE professors (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(32) NOT NULL,
    cognoms VARCHAR(64) NOT NULL,
    dni VARCHAR(16) NOT NULL,
    data_naixement DATE NOT NULL,
    sexe VARCHAR(1) CHECK (sexe LIKE 'H' OR sexe LIKE 'D'),
    telefon VARCHAR(32),
    email VARCHAR(64) NOT NULL,
    descripcio VARCHAR(128)
);

CREATE TABLE assignatures_impartides (
    id_professor INTEGER,
    id_modul INTEGER,
    FOREIGN KEY (id_professor)
        REFERENCES professors (id),
    FOREIGN KEY (id_modul)
        REFERENCES moduls (id),
    PRIMARY KEY (id_professor , id_modul)
);

CREATE TABLE alumnes (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(30) NOT NULL,
    cognoms VARCHAR(30) NOT NULL,
    dni VARCHAR(16) NOT NULL UNIQUE,
    data_naixement DATE NOT NULL,
    sexe VARCHAR(1) CHECK (sexe LIKE 'H' OR sexe LIKE 'D'),
    telefon VARCHAR(32),
    email VARCHAR(64) NOT NULL,
    deleted BOOLEAN DEFAULT FALSE,
    descripcio VARCHAR(128)
);

CREATE TABLE matricula (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_alumne INTEGER NOT NULL,
    id_uf INTEGER NOT NULL,
    curs VARCHAR(8) NOT NULL,
    FOREIGN KEY (id_alumne)
        REFERENCES alumnes (id),
    FOREIGN KEY (id_uf)
        REFERENCES ufs (id)
);

CREATE TABLE notes (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_alumne INTEGER NOT NULL,
    id_uf INTEGER NOT NULL,
    puntuacio FLOAT NOT NULL,
    pes DOUBLE DEFAULT 100.0,
    descripcio VARCHAR(128),
    FOREIGN KEY (id_alumne) REFERENCES alumnes(id),
    FOREIGN KEY (id_uf) REFERENCES ufs(id)
);

CREATE TABLE alumne_grup(
id_grup INTEGER NOT NULL,
id_alumne INTEGER NOT NULL,
FOREIGN KEY(id_grup) REFERENCES grups(id),
FOREIGN KEY(id_alumne) REFERENCES alumnes(id),
PRIMARY KEY(id_grup,id_alumne)
);

INSERT INTO families VALUES(null,"Informatica","Cicles relacionats amb les xarxes, desenvolupament de software i desenvolupament web."); #1
INSERT INTO families VALUES(null,"Esports","Cicles relacionats amb l'activitat esportiva."); #2
INSERT INTO families VALUES(null,"Marketing","Cicles relacionats amb la publicitat i venda de productes.");
SELECT
    *
FROM
    families;

INSERT INTO cicles VALUES(null,1,"DAM","Cicle on s'imparteix la creacio de software.");
INSERT INTO cicles VALUES(null,1,"ASIX","Cicle on s'imparteix el coneixement de xarxes.");
INSERT INTO cicles VALUES(null,2,"CFGS d'acondicionament fisic","Cicle on s'imparteix la preparacio fisica.");
INSERT INTO cicles VALUES(null,3,"Marketing i publicitat","Cicle on s'imparteix el coneixement i venda de productes.");
SELECT
    *
FROM
    cicles;

INSERT INTO moduls VALUES(null,1,"M03","Modul basic de codificacio.");
INSERT INTO moduls VALUES(null,1,"M06","Tractament de BBDD.");
INSERT INTO moduls VALUES(null,2,"MA2","Connexio de xarxes.");
INSERT INTO moduls VALUES(null,3,"MPF1","Anatomia muscular.");
INSERT INTO moduls VALUES(null,4,"MP2","Coneixement del producte.");
SELECT
    *
FROM
    moduls;

INSERT INTO ufs VALUES(null,1,"UF2: Recursivitat","Acces recursiu a metodes i el seu us.");
INSERT INTO ufs VALUES(null,1,"UF4: Fonaments de POO","Fonaments de la programacio orientada a objectes.");
INSERT INTO ufs VALUES(null,3,"UF1: Connexio de sistemes","Connexio de sistemes a traves de xarxa.");
INSERT INTO ufs VALUES(null,4,"UF3: Tonificacio muscular","Metodologia de reforc muscular.");
INSERT INTO ufs VALUES(null,5,"UF5: Analisis de producte","Coneixement dels aspectes principals d'un producte.");
SELECT
    *
FROM
    ufs;

INSERT INTO grups VALUES(null,1,"1r DAM","1r any del grau superior de DAM.");
INSERT INTO grups VALUES(null,1,"2n DAM","2n any del grau superior de DAM.");
INSERT INTO grups VALUES(null,2,"1r ASIX","1r any del grau superior d'ASIX.");
INSERT INTO grups VALUES(null,2,"2n ASIX","2n any del grau superior d'ASIX.");
INSERT INTO grups VALUES(null,3,"1r AF","1r any del grau superior de Acondicionament fisic.");
INSERT INTO grups VALUES(null,3,"2n AF","2n any del grau superior de Acondicionament fisic.");
INSERT INTO grups VALUES(null,4,"1r MP","1r any del grau superior de Marketing i publicitat.");
INSERT INTO grups VALUES(null,4,"2n MP","2n any del grau superior de Marketing i publicitat.");
SELECT
    *
FROM
    grups
;

INSERT INTO professors VALUES(null,"Professor 1","cognoms professor 1","11111111A","1111-01-01",'H',931111111,"correuelectronic1@gmail.com","Professor amb grau d'enginyeria informatica.");
INSERT INTO professors VALUES(null,"Professor 2","cognoms professor 2","22222222A","2222-02-02",'H',932222222,"correuelectronic2@gmail.com","Professor amb grau de fisioterapia.");
INSERT INTO professors VALUES(null,"Professor 3","cognoms professor 3","33333333A","3333-03-03",'D',933333333,"correuelectronic3@gmail.com","Professor amb grau d'enginyeria de xarxes.");
INSERT INTO professors VALUES(null,"Professor 4","cognoms professor 4","44444444A","4444-04-04",'H',934444444,"correuelectronic4@gmail.com","Professor amb grau de Marketing.");
SELECT
    *
FROM
    professors;

INSERT INTO assignatures_impartides VALUES(1,1);
INSERT INTO assignatures_impartides VALUES(1,2);
INSERT INTO assignatures_impartides VALUES(2,4);
INSERT INTO assignatures_impartides VALUES(4,5);
SELECT
    *
FROM
    assignatures_impartides;

INSERT INTO alumnes VALUES(null,"Josep","Garcia sanchez","12345678P","1991-01-02",'H',931234567,"josepgarciasanchez@gmail.com",false,"Alumne interessat en desenvolupament de software");
INSERT INTO alumnes VALUES(null,"Pere","munoz ventura","22222222P","1992-04-01",'H',932222222,"peremunozventura@gmail.com",false,"Alumne interessat en sistemes de xarxes.");
INSERT INTO alumnes VALUES(null,"Laura","Lopez mendez","33333333X","1993-03-02",'D',931234444,"lauralopezmendez@gmail.com",false,"Alumne interessada en acondicionament fisic.");
INSERT INTO alumnes VALUES(null,"Cristina","marcet artigas","44444444P","1997-01-08",'D',93465448,"cristinamarcetartigas@gmail.com",false,"Alumne interessada en marketing i publicitat.");
INSERT INTO alumnes VALUES(null,"Antoni","subirana X","99879755A","1995-05-17",'H',934658880,"antonisubiranax@gmail.com",true,"Alumne expulsat del centre degut a faltes de respecte reiterades.");
SELECT
    *
FROM
    alumnes;

INSERT INTO matricula VALUES(null,1,2,"2020/21");
INSERT INTO matricula VALUES(null,1,1,"2020/21");
INSERT INTO matricula VALUES(null,2,3,"2020/21");
INSERT INTO matricula VALUES(null,3,4,"2020/21");
INSERT INTO matricula VALUES(null,4,5,"2020/21");
SELECT
    *
FROM
    matricula;

INSERT INTO notes VALUES(null,1,2,4.0,30.0,"L'alumne ha de reforcar els coneixements de la uf.");
INSERT INTO notes VALUES(null,1,1,7.0,60.0,"L'alumne ha assolit bona part de la uf.");
INSERT INTO notes VALUES(null,2,3,5.0,50.0,"L'alumne ha assolit els coneixements minims, pero pot millorar.");
INSERT INTO notes VALUES(null,3,4,8.0,70.0,"L'alumne ha realitzat bona feina al llarg de la uf.");
INSERT INTO notes VALUES(null,4,5,10.0,100.0,"L'alumne ha assolit per complet els coneixements de la uf.");
select * from notes;

INSERT INTO alumne_grup VALUES(1,1);
INSERT INTO alumne_grup VALUES(3,2);
INSERT INTO alumne_grup VALUES(5,3);
INSERT INTO alumne_grup VALUES(7,4);
select * from alumne_grup;