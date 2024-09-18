# Car Management System

## Table des matières
1. [Description du projet](#description-du-projet)
2. [Technologies utilisées](#technologies-utilisées)
3. [Architecture du projet](#architecture-du-projet)
4. [Backend (Spring Boot)](#backend-spring-boot)
    - [Structure des entités](#structure-des-entités)
    - [Services](#services)
    - [API REST](#api-rest)
    - [Notification System](#notification-system)
5. [Frontend (Angular)](#frontend-angular)
    - [Pages principales](#pages-principales)
    - [Services Angular](#services-angular)
6. [Installation et démarrage](#installation-et-démarrage)
    - [Prérequis](#prérequis)
    - [Démarrage du backend](#démarrage-du-backend)
    - [Démarrage du frontend](#démarrage-du-frontend)
7. [Contributeurs](#contributeurs)

## Description du projet

Le **Car Management System** est une application web permettant de gérer les voitures d'une entreprise ainsi que leurs maintenances. Ce projet comprend deux parties principales :
- Un backend développé en **Spring Boot** pour gérer la logique métier, les APIs REST et la base de données.
- Un frontend en **Angular** pour offrir une interface utilisateur moderne et intuitive.

## Technologies utilisées

- **Spring Boot** (backend)
- **Angular** (frontend)
- **MySQL** (base de données)
- **Bootstrap** / **CSS** (interface utilisateur)
- **REST API** pour la communication entre le frontend et le backend.

## Architecture du projet

Le projet est organisé en une architecture **monolithique** avec une séparation claire entre le frontend et le backend.

- **Backend (Spring Boot)** : Il gère les données et la logique métier, expose des API REST, et interagit avec la base de données MySQL.
- **Frontend (Angular)** : Interface utilisateur pour les administrateurs et les utilisateurs finaux, qui consomme les API REST du backend.

## Backend (Spring Boot)

### Structure des entités

- **Car** : Gère les informations sur les voitures (numéro de plaque, modèle, statut, etc.).
- **Employee** : Représente les employés, associés à une voiture.
- **Maintenance** : Gère les informations sur les maintenances des voitures.
- **Problem** : Permet de déclarer des problèmes et d'y réagir.

### Services

- **CarService** : Gère la logique métier liée aux voitures.
- **EmployeeService** : Gère la logique liée aux employés.
- **MaintenanceService** : Gestion des maintenances avec calcul des coûts totaux et par voiture.
- **ProblemService** : Gère la déclaration et le suivi des problèmes des voitures.

### API REST

Les principaux endpoints disponibles incluent :

- **Cars** :
    - `GET /api/cars` : Récupère la liste des voitures.
    - `POST /api/cars` : Crée une nouvelle voiture.
    - `PUT /api/cars/{id}` : Modifie les informations d'une voiture.
- **Employees** :
    - `GET /api/employees` : Récupère la liste des employés.
    - `POST /api/employees` : Crée un nouvel employé.
- **Maintenances** :
    - `GET /api/maintenances` : Récupère les maintenances.
    - `POST /api/maintenances` : Ajoute une nouvelle maintenance.
- **Problems** :
    - `POST /api/problems/declare` : Déclare un problème pour une voiture.
    - `POST /api/problems/react` : Réagit à un problème déclaré.

### Notification System

Le système de notification envoie des notifications aux utilisateurs (propriétaires de voiture, administrateurs, employés logistiques) lors de la déclaration de problèmes ou de la maintenance d'une voiture.

## Frontend (Angular)

### Pages principales

- **Dashboard** : Vue d'ensemble des voitures et des maintenances.
- **Car Management** : Permet de lister, ajouter, modifier ou supprimer des voitures.
- **Maintenance Management** : Interface pour gérer les maintenances des voitures.
- **Problem Reporting** : Permet aux employés de déclarer des problèmes.

### Services Angular

- **CarService** : Service pour consommer l'API des voitures.
- **EmployeeService** : Service pour gérer les employés.
- **MaintenanceService** : Service pour récupérer et envoyer les données de maintenance.
- **ProblemService** : Service pour la gestion des problèmes (déclaration, réaction).

## Installation et démarrage

### Prérequis

- **Java 17** (pour le backend Spring Boot)
- **Node.js** et **Angular CLI** (pour le frontend)
- **MySQL** (pour la base de données)

### Démarrage du backend

1. Cloner le dépôt du projet.
2. Configurer la base de données MySQL avec le fichier `application.properties` (ou `application.yml`) dans le dossier `src/main/resources`.
3. Démarrer le backend avec la commande :

    ```bash
    ./mvnw spring-boot:run
    ```

### Démarrage du frontend

1. Naviguer dans le répertoire `frontend`.
2. Installer les dépendances Angular avec la commande :

    ```bash
    npm install
    ```

3. Démarrer le serveur Angular :

    ```bash
    ng serve
    ```

Le frontend sera disponible à l'adresse [http://localhost:4200](http://localhost:4200).

## Contributeurs

- **[Hamdi Ouni](mailto:ounihamdi4@gmail.com)** : Développeur principal du backend et frontend.
