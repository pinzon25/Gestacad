SET GLOBAL time_zone = '+3:00';
DROP DATABASE IF EXISTS gestacad;
CREATE DATABASE gestacad;
#CREATE USER administrador1 IDENTIFIED BY 'admin1';
GRANT ALL PRIVILEGES ON gestacad.* TO administrador1 WITH GRANT OPTION;
USE gestacad;

CREATE TABLE families( #ID DE LA FAMILIA I EL NOM DE LA SEVA BRANCA TECNICA.
id_familia INTEGER AUTO_INCREMENT,
nom_familia VARCHAR(50) NOT NULL,
PRIMARY KEY(id_familia)
);


CREATE TABLE cicles(
id_cicle INTEGER AUTO_INCREMENT,
id_familia INTEGER NOT NULL,
nom_cicle VARCHAR(30) NOT NULL,
FOREIGN KEY(id_familia) REFERENCES families(id_familia),
PRIMARY KEY(id_cicle)
);


CREATE TABLE grups(#ID DEL GRUP, EL SEU NOM I EL CICLE AL QUE PERTANY EL GRUP.
id_grup INTEGER AUTO_INCREMENT,
nom_grup VARCHAR(30) NOT NULL,
id_cicle INTEGER NOT NULL,
FOREIGN KEY(id_cicle) REFERENCES cicles(id_cicle),
PRIMARY KEY(id_grup)
);


CREATE TABLE professor( #DADES DEL PROFESSOR.
id_professor INTEGER AUTO_INCREMENT,
nom_professor VARCHAR(50) NOT NULL,
cognoms_professor VARCHAR(30) NOT NULL,
dni_professor VARCHAR(9) NOT NULL,
data_naixement DATE NOT NULL,
sexe VARCHAR(1) CHECK (sexe LIKE'H' OR sexe LIKE 'D'),
telefon INTEGER(9),
pro_correu_electronic VARCHAR(50) NOT NULL,
informe INTEGER,
administrador BOOLEAN DEFAULT false,
PRIMARY KEY(id_professor)
);


CREATE TABLE alumne( #DADES DEL ALUMNE I EL GRUP AL QUE PERTANY.
id_alumne INTEGER AUTO_INCREMENT,
nom_alumne VARCHAR(30) NOT NULL,
cognoms_alumne VARCHAR(30) NOT NULL,
dni_alumne VARCHAR(9) NOT NULL UNIQUE,
data_naixement DATE NOT NULL,
sexe VARCHAR(1) CHECK (sexe LIKE 'H' OR sexe LIKE 'D'),
telefon INTEGER(9),
correu_electronic_al VARCHAR(50) NOT NULL,
id_grup INTEGER NOT NULL,
PRIMARY KEY(id_alumne)
);


CREATE TABLE assignatures( #TE EL ID I EL NOM DE LA ASSIGNATURA.
id_assignatura INTEGER AUTO_INCREMENT,
nom_assignatura VARCHAR(50) NOT NULL,
#notes_assignatura FLOAT NOT NULL,
#FOREIGN KEY(id_professor_assignatura) REFERENCES professor(id_professor),
PRIMARY KEY(id_assignatura)
);


CREATE TABLE assignatures_cursades( #TAULA RELACIO ENTRE ALUMNE I ASSIGNATURA, TE COM A ATRIBUT LA NOTA.
id_assignatura_cursada INTEGER NOT NULL,
id_alumne_assignatura INTEGER NOT NULL,
notes FLOAT NOT NULL,
FOREIGN KEY(id_assignatura_cursada) REFERENCES assignatures(id_assignatura),
FOREIGN KEY(id_alumne_assignatura) REFERENCES alumne(id_alumne),
PRIMARY KEY(id_assignatura_cursada,id_alumne_assignatura)
);

CREATE TABLE informe( #Modificat diagrama, un professor pot tenir o no varis informes.
id_informe INTEGER AUTO_INCREMENT,
assumpte VARCHAR(30) NOT NULL,
id_professor_informe INTEGER NOT NULL,
id_alumne_informe INTEGER NOT NULL,
FOREIGN KEY(id_professor_informe) REFERENCES professor(id_professor),
FOREIGN KEY(id_alumne_informe) REFERENCES alumne(id_alumne),
PRIMARY KEY(id_informe)
);

CREATE TABLE assignatures_impartides(
id_professor_docent INTEGER,
id_assignatura_impartida INTEGER,
FOREIGN KEY(id_professor_docent) REFERENCES professor(id_professor),
FOREIGN KEY(id_assignatura_impartida) REFERENCES assignatures(id_assignatura),
PRIMARY KEY(id_professor_docent,id_assignatura_impartida)
);

CREATE TABLE correu(
id_correu INTEGER AUTO_INCREMENT,
id_proffesor_mail INTEGER NOT NULL,
id_alumne_mail INTEGER NOT NULL,
contingut TEXT NOT NULL,
FOREIGN KEY(id_proffesor_mail) REFERENCES professor(id_professor),
FOREIGN KEY(id_alumne_mail) REFERENCES alumne(id_alumne),
PRIMARY KEY(id_correu)
);

CREATE TABLE assignatures_cicle( #Relacio entre cicles i assignatures.
id_cicles INTEGER,
id_assignatures INTEGER,
FOREIGN KEY(id_cicles) REFERENCES cicles(id_cicle),
FOREIGN KEY(id_assignatures) REFERENCES assignatures(id_assignatura),
PRIMARY KEY(id_cicles,id_assignatures)
);

INSERT INTO families VALUES(null,"Informatica"); #1
INSERT INTO families VALUES(null,"Esports"); #2
INSERT INTO families VALUES(null,"Marketing"); #3
select * from families;

INSERT INTO cicles VALUES(null,1,"DAM");
INSERT INTO cicles VALUES(null,1,"ASIX");
INSERT INTO cicles VALUES(null,2,"CFGS d'acondicionament fisic");
INSERT INTO cicles VALUES(null,3,"Marketing i publicitat");
select * from cicles;

INSERT INTO grups VALUES(null,"1r DAM",1);
INSERT INTO grups VALUES(null,"2n DAM",1);
INSERT INTO grups VALUES(null,"1r ASIX",2);
INSERT INTO grups VALUES(null,"2n ASIX",2);
INSERT INTO grups VALUES(null,"1r ESPORTS",3);
INSERT INTO grups VALUES(null,"2n MARKETING",4);
select * from grups;

INSERT INTO professor VALUES(null,"Professor 1","cognoms professor 1","11111111A","1111-01-01",'H',931111111,"correuelectronic1@gmail.com",null,false);
INSERT INTO professor VALUES(null,"Professor 2","cognoms professor 2","22222222A","2222-02-02",'H',932222222,"correuelectronic2@gmail.com",null,true);
INSERT INTO professor VALUES(null,"Professor 3","cognoms professor 3","33333333A","3333-03-03",'D',933333333,"correuelectronic3@gmail.com",0,true);
INSERT INTO professor VALUES(null,"Professor 4","cognoms professor 4","44444444A","4444-04-04",'H',934444444,"correuelectronic4@gmail.com",12,true);
select * from professor;

INSERT INTO alumne VALUES(null,"Josep","Garcia sanchez","12345678P","1991-01-02",'H',931234567,"josepgarciasanchez@gmail.com",1);
INSERT INTO alumne VALUES(null,"Pere","munoz ventura","22222222P","1992-04-01",'H',932222222,"peremunozventura@gmail.com",1);
INSERT INTO alumne VALUES(null,"Laura","Lopez mendez","33333333X","1993-03-02",'D',931234444,"lauralopezmendez@gmail.com",3);
INSERT INTO alumne VALUES(null,"Cristina","marcet artigas","44444444P","1997-01-08",'D',93465448,"cristinamarcetartigas@gmail.com",5);
INSERT INTO alumne VALUES(null,"Antoni","subirana X","99879755A","1995-05-17",'H',934658880,"antonisubiranax@gmail.com",6);
select * from alumne;

INSERT INTO assignatures VALUES(null,"M03");
INSERT INTO assignatures VALUES(null,"M06");
INSERT INTO assignatures VALUES(null,"MP4");
#select * from alumne;
select * from assignatures;

INSERT INTO assignatures_cursades VALUES(1,1,4.3);
INSERT INTO assignatures_cursades VALUES(1,3,5.2);
select * from assignatures_cursades;

INSERT INTO informe VALUES(null,"L'alumne ha faltat a classe.",1,3);
INSERT INTO informe VALUES(null,"L'alumne ha menjat a classe.",4,1);
select * from informe;

INSERT INTO assignatures_impartides VALUES(1,1);
INSERT INTO assignatures_impartides VALUES(1,2);
INSERT INTO assignatures_impartides VALUES(4,3);
select * from assignatures_impartides;

INSERT INTO correu VALUES(null,1,1,"M'has d'entregar la practica pendent.");
INSERT INTO correu VALUES(null,4,1,"Aviat et comunico la data de recuperacio.");
select * from correu;

INSERT INTO assignatures_cicle VALUES(1,1);
INSERT INTO assignatures_cicle VALUES(1,2);
INSERT INTO assignatures_cicle VALUES(3,3);
select * from assignatures_cicle;
