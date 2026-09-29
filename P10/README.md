# MavTutor — Tutoring Session Manager

MavTutor is a Java command-line application for organizing tutoring activity around university courses. It models courses, students, tutors, and scheduled sessions, then lets a user review students, tutors, and sessions with a 1–5 star rating and written comment.

> Academic project for CSE 1325. This repository contains coursework and instructor-provided course framework code; MavTutor is one project within it.

## What it does

- Create and browse courses, students, tutors, and tutoring sessions.
- Associate students and tutors with courses, then schedule sessions by date, time, and duration.
- Add students to a session.
- Review a student, tutor, or session with a star rating and written feedback.
- Create a new data set, open and save a local data file, and use Save As.
- Warn before replacing unsaved data.

## Design

The application separates the model into Java packages:

- `people`: people, students, and tutors
- `session`: courses, sessions, and date ranges
- `rating`: the `Rateable` interface, ratings, and comments
- `menu`: the command-line menu framework
- `mdi`: the `MavTutor` application and its main workflow

The project applies object-oriented design with classes, inheritance, interfaces, collections, validation, and file input/output. It is a console application; it does not currently provide a web or graphical interface.

## Build and run

Requires a JDK and Apache Ant.

From this directory:

```sh
ant compile
java -cp target mdi.MavTutor nosplash
```

Omit `nosplash` to show the startup banner. The Ant build also generates Javadoc under `target/doc/api`.

## Scope and notes

This is a learning project, not a production scheduling or identity system. Reviews are kept in memory during a run; the current save/load format persists core course, student, tutor, and session records, not the review history. The current tutor workflow also asks for an SSN, so remove that field and its handling before using real data or making the source repository public.

## LinkedIn project entry

**Title:** MavTutor — Java Tutoring Session Manager  
**Associated with:** CSE 1325 coursework  
**Description:** Built a Java command-line application to organize tutoring courses, students, tutors, and scheduled sessions. Added course and participant management, session scheduling, 1–5 star reviews with written comments, and local save/load workflows. Structured the code into model packages and applied object-oriented programming, interfaces, collections, validation, and file I/O.

**Skills:** Java · Object-Oriented Programming · Collections · File I/O · Software Design

Source: [P10](https://github.com/aashaswak/cse1325/tree/main/P10)
