CREATE TABLE auteur
(
    id_auteur             INT         NOT NULL
        PRIMARY KEY,
    nom_auteur            VARCHAR(50) NULL,
    prenom_auteur         VARCHAR(50) NULL,
    date_naissance_auteur DATE        NULL,
    date_deces_auteur     DATE        NULL
);

CREATE TABLE genre
(
    id_genre  INT         NOT NULL
        PRIMARY KEY,
    nom_genre VARCHAR(50) NULL
);

CREATE TABLE oeuvre
(
    id_oeuvre    INT         NOT NULL
        PRIMARY KEY,
    titre        VARCHAR(50) NULL,
    annee_sortie SMALLINT    NULL,
    isbn         VARCHAR(13) NULL
);

CREATE TABLE cree
(
    id_oeuvre INT NOT NULL,
    id_auteur INT NOT NULL,
    PRIMARY KEY (id_oeuvre, id_auteur),
    CONSTRAINT fk_cree
        FOREIGN KEY (id_oeuvre) REFERENCES oeuvre (id_oeuvre),
    CONSTRAINT fk_cree2
        FOREIGN KEY (id_auteur) REFERENCES auteur (id_auteur)
);

CREATE TABLE genre_oeuvre
(
    id_genre  INT NOT NULL,
    id_oeuvre INT NOT NULL,
    PRIMARY KEY (id_genre, id_oeuvre),
    CONSTRAINT FKmgtwsnk7a9fv7timvtqkyg6uc
        FOREIGN KEY (id_oeuvre) REFERENCES oeuvre (id_oeuvre),
    CONSTRAINT FKs5oyho1xyfj2oj9r20qf1oslk
        FOREIGN KEY (id_genre) REFERENCES genre (id_genre),
    CONSTRAINT fk_genre_oeuvre
        FOREIGN KEY (id_genre) REFERENCES genre (id_genre),
    CONSTRAINT fk_genre_oeuvre2
        FOREIGN KEY (id_oeuvre) REFERENCES oeuvre (id_oeuvre)
);

CREATE TABLE instance_oeuvre
(
    id_oeuvre          INT         NOT NULL,
    code_barre         VARCHAR(50) NOT NULL
        PRIMARY KEY,
    etat_disponibilite INT         NULL,
    CONSTRAINT FKep183g7g2ynv3xispr72wnary
        FOREIGN KEY (id_oeuvre) REFERENCES oeuvre (id_oeuvre),
    CONSTRAINT fk_lig_instance_oeuvre
        FOREIGN KEY (id_oeuvre) REFERENCES oeuvre (id_oeuvre)
);

CREATE TABLE roles
(
    role_id INT AUTO_INCREMENT
        PRIMARY KEY,
    name    VARCHAR(45) NOT NULL
);

CREATE TABLE utilisateur
(
    id_user     INT AUTO_INCREMENT
        PRIMARY KEY,
    adresse     VARCHAR(50)  NULL,
    code_postal VARCHAR(5)   NULL,
    email       VARCHAR(50)  NULL,
    nom         VARCHAR(50)  NULL,
    password    VARCHAR(100) NULL,
    prenom      VARCHAR(50)  NULL,
    ville       VARCHAR(50)  NULL
);

CREATE TABLE demande_reservation
(
    id_user      INT      NOT NULL,
    id_oeuvre    INT      NOT NULL,
    date_demande DATETIME NULL,
    PRIMARY KEY (id_user, id_oeuvre),
    CONSTRAINT fk_demande_reservation
        FOREIGN KEY (id_user) REFERENCES utilisateur (id_user),
    CONSTRAINT fk_demande_reservation2
        FOREIGN KEY (id_oeuvre) REFERENCES oeuvre (id_oeuvre)
);

CREATE TABLE location
(
    id_location            INT         NOT NULL
        PRIMARY KEY,
    code_barre             VARCHAR(50) NOT NULL,
    id_employe             INT         NULL,
    id_utilisateur         INT         NOT NULL,
    date_reservation       DATETIME    NULL,
    date_location          DATETIME    NULL,
    date_rendu_theorique   DATE        NULL,
    etat_physique_location INT         NULL,
    etat_physique_rendu    INT         NULL,
    date_rendu_reel        DATETIME    NULL,
    CONSTRAINT fk_lig_employe_reservation
        FOREIGN KEY (id_employe) REFERENCES utilisateur (id_user),
    CONSTRAINT fk_lig_reservation
        FOREIGN KEY (id_utilisateur) REFERENCES utilisateur (id_user),
    CONSTRAINT fk_lig_reservation_instance
        FOREIGN KEY (code_barre) REFERENCES instance_oeuvre (code_barre)
);

CREATE TABLE notfication_disponible
(
    id_user   INT NOT NULL,
    id_oeuvre INT NOT NULL,
    PRIMARY KEY (id_user, id_oeuvre),
    CONSTRAINT fk_notfication_disponible
        FOREIGN KEY (id_user) REFERENCES utilisateur (id_user),
    CONSTRAINT fk_notfication_disponible2
        FOREIGN KEY (id_oeuvre) REFERENCES oeuvre (id_oeuvre)
);

CREATE TABLE users_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK4jf1hkpfeswf8ijcpqpwko74a
        FOREIGN KEY (user_id) REFERENCES utilisateur (id_user),
    CONSTRAINT FKj6m8fwv7oqv74fcehir1a9ffy
        FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

