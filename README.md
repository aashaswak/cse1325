# CSE 1325 Coursework and MavTutor

This repository contains programming assignments from CSE 1325 and the Java MavTutor tutoring-session manager.

## MavTutor: Java app with a simple browser interface

The browser interface in `P10/web` uses the Java model classes in `P10/src`: courses, students, tutors, tutoring sessions, and reviews. Its interface is plain HTML and CSS; the application logic stays in Java.

To run it on Windows, install a JDK and Apache Ant, then double-click `P10/run-web.bat`. Open [http://127.0.0.1:8080](http://127.0.0.1:8080) in your browser. On macOS or Linux, run these commands from `P10`:

```sh
ant compile
java --add-modules jdk.httpserver -cp target web.MavTutorWeb
```

The Java server binds to your own computer only. It saves data in `~/.mavtutor-web-data.txt` and supports saving, reopening, and downloading a backup. GitHub Pages cannot run Java, so the public Pages address is an HTML launch guide; the actual app runs locally with Java.

- [P10 project documentation and requirements](P10/README.md)
- [Java web server](P10/src/web/MavTutorWeb.java)
- [Plain HTML interface](P10/web/index.html)
- [Plain CSS](P10/web/styles.css)
- [Original console application](P10/src/mdi/MavTutor.java)

Tutor SSNs are not collected or stored. The original coursework data reader remains compatible with files that have the old SSN line.

## Coursework layout

- `P01`–`P11`: programming assignments
- `class1325`: in-class examples
- `P10`: MavTutor Java application and browser interface
- `MavTutorApp`: static launch guide for the local Java web app

Some assignment folders contain alternate or bonus solutions. See each folder for its source and build files.
