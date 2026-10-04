# EMU SCT Mobile App — Master's Graduation Project

**Originally developed: 2014**

**Academic project:** Master's graduation project in Information Technology at Eastern Mediterranean University (EMU).

This is a legacy full-stack Android application developed for the **School of Computing and Technology (SCT)** at Eastern Mediterranean University.

The project was designed to provide students and prospective students with information about academic programs, staff, admissions and announcements, while also providing a basic administration system for managing announcements and contact messages.

## Features

- Academic program and department information
- Graduate, undergraduate and diploma program pages
- Staff information
- Admission requirements
- Useful links
- About section
- Contact form
- Announcements
- Admin login
- Contact message management
- Add announcements
- Update announcements
- Delete announcements

## Client-Server Architecture

The project uses a simple full-stack client-server architecture:

Android Application
        |
        v
      HTTP
        |
        v
   PHP Backend
        |
        v
   MySQL Database

The Android application communicates with PHP endpoints and exchanges data using JSON.

## Backend Features

The PHP backend handles:

- Admin authentication
- Announcement retrieval
- Announcement creation
- Announcement updates
- Announcement deletion
- Contact form submissions
- Contact message retrieval

## Technology Stack

### Android Application

- Java
- Android SDK
- Eclipse
- XML layouts
- Apache HttpClient
- AsyncTask
- JSON

### Backend

- PHP
- MySQL

## Academic Programs

The application contains information about programs such as:

- Master of Information Technology
- B.S. Information Technology
- Computer Programming
- Electrical and Electronics Technology
- Biomedical Equipment Technology
- Construction Technology
- Banking and Insurance
- Computer Aided Technical Drawing
- Accounting and Taxation Applications
- Mapping and Cadastral Survey
- Office Management
- Medical Documentation and Office Management

## Academic Context

This application was originally developed in **2014 as my master's graduation project**.

It represents my early work with full-stack mobile application development, including:

- Native Android development
- Client-server communication
- Backend development
- Relational databases
- JSON-based data exchange
- Authentication
- CRUD operations

## Project Status

This project is a **legacy academic project** and is no longer actively maintained.

The repository is preserved largely in its original architecture to document the technologies, design decisions and development practices used at the time.

## Legacy and Security Notice

This application was developed using technologies and practices that were common during its original development period.

Some parts of the project should not be used in a modern production application, including:

- Plain HTTP communication
- Legacy PHP `mysql_*` functions
- Client-side credential comparison
- Older Android networking APIs

A modern implementation should use secure authentication, HTTPS, parameterized database access and server-side authorization.

## Possible Modern Architecture

A modern version could be implemented using:

.NET MAUI
    |
    v
ASP.NET Core Web API
    |
    v
SQL Server

with HTTPS, secure authentication, dependency injection, modern REST API practices and server-side authorization.
