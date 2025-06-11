DROP TABLE IF EXISTS restaurants;

CREATE TABLE restaurants (
    nom TEXT,
    type TEXT,
    categorie TEXT,
    adresse1 TEXT,
    adresse2 TEXT,
    adresse3 TEXT,
    code_postal INT,
    cedex TEXT,
    bureau_distributeur TEXT,
    commune TEXT,
    code_insee INT,
    latitude DECIMAL,
    longitude DECIMAL,
    situation_offre TEXT,
    mobile TEXT,
    fixe TEXT,
    fax TEXT,
    mail TEXT,
    site_web TEXT,
    url_video TEXT,
    type_plateforme TEXT,
    widget_tripadvisor TEXT,
    code_embed_video TEXT,
    classement_guides TEXT,
    label_classement_logis TEXT,
    label_tourisme_handicap TEXT,
    animal_accepte TEXT,
    complement_info_animal_accepte TEXT,
    labels TEXT,
    servicessur_equipement TEXT,
    nb_max_couverts TEXT,
    nb_salles TEXT,
    nb_max_couverts_terrasse TEXT,
    nb_salles_reu TEXT,
    nb_salles_clim TEXT,
    presents_itineraires_velo TEXT,
    langues_accueil_1 TEXT,
    langues_accueil_2 TEXT,
    nb_min_personnes_groupes TEXT,
    nb_max_personnes_groupes TEXT,
    ouvert_toute_annee_full TEXT,
    horaires TEXT,
    moyens_reservation_direct TEXT,
    centrale_reservation_web TEXT,
    moyens_reservation_web TEXT,
    tarifs TEXT,
    paiements_acceptes TEXT,
    localisation TEXT,
    departement TEXT
);

COPY restaurants
FROM '/docker-entrypoint-initdb.d/offre-touristique-restaurants-rpdl@paysdelaloire.csv'
DELIMITER ';'
CSV HEADER;

ALTER TABLE restaurants ADD COLUMN already_done BOOLEAN DEFAULT FALSE;
ALTER TABLE restaurants ADD COLUMN id SERIAL PRIMARY KEY;
ALTER TABLE restaurants DROP COLUMN type_plateforme;