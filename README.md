
```markdown
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

---

```
