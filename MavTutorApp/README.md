# MavTutor Desk

MavTutor Desk is a small, practical tutoring-session organizer built from the course and people/session model in the CSE 1325 MavTutor coursework. It turns that model into a browser app a tutoring group organizer can use without installing a server.

## Try it

Download `index.html` and open it in a current desktop or mobile browser. No build step, account, or internet connection is required after you have the file.

## Use it

1. Add the courses you support.
2. Add tutors and students with only the contact details needed to coordinate.
3. Schedule a session by choosing its course, tutor, date, time, format, place or meeting link, and attending students.
4. Mark a scheduled session complete when it is done.
5. Export a JSON backup regularly, or import one to restore your records on this browser.

The app checks for tutor schedule overlaps before saving a session and prevents removing a course, tutor, or student while sessions still refer to it.

## Privacy and scope

MavTutor Desk stores its records in this browser's local storage. It does not send them to a server. Data is separate in each browser/device, so use **Export backup** to move or back up your data. Anyone who can use this browser profile may be able to access the records; avoid storing sensitive student information. The app does not request or store Social Security numbers.

This is a single-organizer utility, not a shared multi-user service, institutional student system, or production authentication platform.

## Built from the CSE 1325 project

The app adapts MavTutor's core domain—courses, students, tutors, and scheduled sessions—into a lightweight organizer. It is a new browser interface and local workflow built around those ideas, rather than a claim that the original command-line application was already a web product.

**Skills:** JavaScript · HTML · CSS · Data modeling · Browser storage · Responsive UI

**Source project:** [CSE 1325 P10 MavTutor](../P10/README.md)
