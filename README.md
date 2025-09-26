
##markdown
# Subscription Management Console App

## Description

This Java 8 console application centralizes the management of personal and professional subscriptions. It enables users and financial managers to track subscription deadlines, detect missed payments, and generate financial reports for real and forecasted costs.

## Technologies Used

- Java 8 (Stream API, lambda, Optional, Collectors)
- JDBC
- PostgreSQL or MySQL
- IntelliJ IDEA
- Git

## Project Structure

```
src/
├── entity/
│   ├── Abonnement.java
│   ├── AbonnementAvecEngagement.java
│   ├── AbonnementSansEngagement.java
│   └── Paiement.java
├── dao/
│   ├── AbonnementDAO.java
│   └── PaiementDAO.java
├── service/
│   ├── AbonnementService.java
│   └── PaiementService.java
├── util/
│   ├── DateUtils.java
│   ├── FormatUtils.java
│   └── ValidationUtils.java
├── ui/
│   └── ConsoleMenu.java
└── ui.Main.java

```
- **entity/**: Domain models for subscriptions and payments.
- **dao/**: Data access objects for database operations via JDBC.
- **service/**: Business logic for managing subscriptions and payments.
- **util/**: Utility classes for date handling, formatting, and validation.
- **ui/**: Console-based user interface and ui.menuPaiements navigation.
- **ui.Main.java**: Application entry point.

## Features

- Create, modify, delete, and terminate subscriptions (with or without commitment).
- List all subscriptions and view details.
- Display payments for a subscription.
- Record, modify, and delete payments.
- Detect missed payments and show total unpaid amount (for committed subscriptions).
- Show total paid amount for a subscription.
- Display the last 5 payments.
- Generate financial reports (monthly, annual, unpaid).
- Automatic generation of payment deadlines.
- Exception handling with clear messages.
- Bonus: Log saving to file, CSV/JSON report export.

## Prerequisites

- Java 8+
- PostgreSQL or MySQL database
- IntelliJ IDEA (recommended)
- Git

## Setup

1. Clone the repository.
2. Configure your database connection in the DAO classes.
3. Build and run the application from `ui.Main.java`.

## Screenshots

_Add screenshots of the console ui.menuPaiements, subscription list, payment list, and reports here._


##SCREENSHOT
**Main Menu**
<img width="1799" height="778" alt="Capture d'écran 2025-09-26 100051" src="https://github.com/user-attachments/assets/bbfd2588-0460-4ead-b1eb-532723b28076" />
**Créer un abonnement**
<img width="1765" height="556" alt="Capture d'écran 2025-09-26 101042" src="https://github.com/user-attachments/assets/cc889436-6190-459b-b048-fa75f7e8ffda" />
**Modifier un abonnement**
<img width="1781" height="552" alt="image" src="https://github.com/user-attachments/assets/a2f67a0d-b390-4bed-b133-5a1aa06c4be1" />
**Supprimer un abonnement**
<img width="1751" height="308" alt="Capture d'écran 2025-09-26 101425" src="https://github.com/user-attachments/assets/627f082b-f73b-413e-ae39-0be004b25b80" />
**Consulter la liste des abonnements**
<img width="1785" height="650" alt="Capture d'écran 2025-09-26 101504" src="https://github.com/user-attachments/assets/a48030f9-88f8-438a-a545-1dfbddfa5d66" />
**Résilier un abonnement**
<img width="1832" height="337" alt="Capture d'écran 2025-09-26 101611" src="https://github.com/user-attachments/assets/98359471-94b5-4750-80f4-48c70ebd3b79" />
**Enregistrer un paiement**
<img width="1755" height="195" alt="Capture d'écran 2025-09-26 110124" src="https://github.com/user-attachments/assets/7b78e413-073a-4a44-931e-5d427ad92099" />
**Modifier un paiement**
<img width="1786" height="204" alt="Capture d'écran 2025-09-26 103033" src="https://github.com/user-attachments/assets/cc9fd169-3f40-4778-9834-9fce6754d375" />
**Supprimer un paiement**
<img width="1794" height="130" alt="Capture d'écran 2025-09-26 103125" src="https://github.com/user-attachments/assets/6d89ea4c-c19f-4381-9cde-8ece0549356d" />
**Afficher les paiements d’un abonnement**
<img width="1787" height="540" alt="Capture d'écran 2025-09-26 103204" src="https://github.com/user-attachments/assets/e028ff84-5360-48b4-acb0-ec42c8e91194" />
**Consulter les paiements manqués et montant total impayé**
<img width="1800" height="604" alt="Capture d'écran 2025-09-26 103226" src="https://github.com/user-attachments/assets/f279ab2e-4204-4caa-b5ed-55561a575029" />
**Afficher la somme payée d’un abonnement**
<img width="1792" height="409" alt="Capture d'écran 2025-09-26 103302" src="https://github.com/user-attachments/assets/bc798df9-5818-4799-8c97-56ceab5d9115" />
**Afficher les 5 derniers paiements**
<img width="1796" height="540" alt="Capture d'écran 2025-09-26 103334" src="https://github.com/user-attachments/assets/4628a920-31d2-4408-a006-9e74f1f40780" />

















