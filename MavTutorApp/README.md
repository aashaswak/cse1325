# MavTutor browser launch guide

This folder contains the static GitHub Pages guide for the Java MavTutor app in [P10](../P10/README.md). The guide is plain HTML and CSS. The working app is served by Java on the computer that starts it.

## Run the app

On Windows, install a JDK and Apache Ant, download the repository, then double-click `P10/run-web.bat`. On macOS or Linux, run from the `P10` directory:

```sh
ant compile
java --add-modules jdk.httpserver -cp target web.MavTutorWeb
```

Open [http://127.0.0.1:8080](http://127.0.0.1:8080) in a browser. The Java server listens on the local computer only and saves records in `~/.mavtutor-web-data.txt`.

## What it does

The browser interface uses the Java classes from P10 to manage courses, students, tutors, sessions, and 1–5 star reviews. The app includes local save/open and backup download actions. It does not request or store tutor SSNs.

GitHub Pages cannot execute Java, so it hosts this launch guide rather than the application server. See the [Java web server source](../P10/src/web/MavTutorWeb.java) and [P10 documentation](../P10/README.md).
