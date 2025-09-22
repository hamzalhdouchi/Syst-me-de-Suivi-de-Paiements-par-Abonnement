USE abonnements;

CREATE TABLE Abonnement (
    id varchar(255) NOT NULL PRIMARY KEY,
    nomService VARCHAR(100) NOT NULL,
    montantMensuel DECIMAL(10,2) NOT NULL CHECK (montantMensuel >= 0),
    dateDebut DATE NOT NULL,
    dateFin DATE NOT NULL,
    statut ENUM('ACTIVE','SUSPENDU','RESILIE') NOT NULL,
    typeAbonnement ENUM('AVEC_ENGAGEMENT','SANS_ENGAGEMENT') NOT NULL,
    dureeEngagementMois INT DEFAULT NULL
);


CREATE TABLE Paiement (
    idPaiement VARCHAR(255) NOT NULL PRIMARY KEY, -- UUID
    idAbonnement varchar(255) NOT NULL,
    dateEcheance DATE NOT NULL,
    datePaiement DATE DEFAULT NULL,
    typePaiement ENUM('CB','PAYPAL','VIREMENT','AUTRE') NOT NULL,
    statut ENUM('PAYE','NON_PAYE','EN_RETARD') NOT NULL,
    montant DECIMAL(10,2) NOT NULL CHECK (montant >= 0),
    CONSTRAINT fk_abonnement
        FOREIGN KEY (idAbonnement) REFERENCES Abonnement(id)
        ON DELETE CASCADE
);

