# MavTutor — Tutoring Session Manager

MavTutor is a Java application for organizing tutoring activity around university courses. It models courses, students, tutors, and scheduled sessions, then lets a user review students, tutors, and sessions with a 1–5 star rating and written comment. The `web` package adds a small browser interface that uses the same Java model classes.

> Academic project for CSE 1325. This repository contains coursework and instructor-provided course framework code; MavTutor is one project within it.

## What it does

- Create and browse courses, students, tutors, and tutoring sessions.
- Associate students and tutors with courses, then schedule sessions by date, time, and duration.
- Add students to a session.
- Review a student, tutor, or session with a star rating and written feedback.
- Create a new data set, open and save a local data file, and use Save As.
- Warn before replacing unsaved data.

## Design

The project separates the model into Java packages:

- `people`: people, students, and tutors
- `session`: courses, sessions, and date ranges
- `rating`: the `Rateable` interface, ratings, and comments
- `menu`: the command-line menu framework
- `mdi`: the `MavTutor` console application and its main workflow
- `web`: a local HTTP server that presents the same model in a basic HTML/CSS interface

The project applies object-oriented design with classes, inheritance, interfaces, collections, validation, and file input/output. Both interfaces use the same Java model classes. The browser version binds only to `127.0.0.1`, so it is for personal use on the computer running Java; GitHub Pages cannot run its Java server.

## Build and run

Requires a JDK and Apache Ant.

From this directory:

```sh
ant compile
java -cp target mdi.MavTutor nosplash

# Or start the local browser interface from this directory
java --add-modules jdk.httpserver -cp target web.MavTutorWeb
```

Omit `nosplash` to show the startup banner. For the browser version, open `http://127.0.0.1:8080` after starting the server. The web version automatically saves its local data to `~/.mavtutor-web-data.txt`; use the Overview page to save, reopen, start a new data set, or download a backup. The Ant build also generates Javadoc under `target/doc/api`.

## Scope and notes

This is a learning project, not a production scheduling or identity system. Reviews are kept in memory during a run; the current save/load format persists core course, student, tutor, and session records, not the review history. Tutor SSNs are not collected or stored. The `Tutor` reader can still open older project data files by skipping the legacy SSN line. The browser interface intentionally listens on the local computer only; it has no authentication and should not be exposed directly to the public internet.

## LinkedIn project entry

**Title:** MavTutor — Java Tutoring Session Manager  
**Associated with:** CSE 1325 coursework  
**Description:** Built a Java command-line application to organize tutoring courses, students, tutors, and scheduled sessions. Added course and participant management, session scheduling, 1–5 star reviews with written comments, and local save/load workflows. Structured the code into model packages and applied object-oriented programming, interfaces, collections, validation, and file I/O.

**Skills:** Java · Object-Oriented Programming · Collections · File I/O · Software Design

Source: [P10](https://github.com/aashaswak/cse1325/tree/main/P10)
