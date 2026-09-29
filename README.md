# CSE 1325 Coursework

This repository contains Java coursework from CSE 1325. The most complete application in the collection is **MavTutor**, a command-line tutoring session manager developed in [P10](P10/).

## Featured project: MavTutor

MavTutor organizes courses, students, tutors, and scheduled tutoring sessions. It also supports 1–5 star reviews with written comments, plus local save and load for core records.

- [Project overview, design notes, and build instructions](P10/README.md)
- [MavTutor source code](P10/src/mdi/MavTutor.java)

**Stack:** Java · Object-oriented design · Collections · File I/O · Apache Ant

MavTutor is an academic console application. It is not a web app or production scheduling system.

## Repository layout

- `P01`–`P11`: course programming assignments
- `class1325`: in-class examples
- `P10`: MavTutor application

Some folders contain alternate or bonus assignment solutions. See each folder for its own source and build files.

## Running MavTutor

Requires a JDK and Apache Ant. From the `P10` directory:

```sh
ant compile
java -cp target mdi.MavTutor nosplash
```

See [P10/README.md](P10/README.md) for project scope and notes.
