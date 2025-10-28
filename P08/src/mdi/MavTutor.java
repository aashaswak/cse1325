package mdi;

import menu.Menu;
import menu.MenuItem;
import people.Tutor;
import people.Student;
import session.Course;
import session.Session;
import java.util.ArrayList;
import java.util.List;  


public class MavTutor {
    private Menu menu;
    private List<?>View = ViewCourses;

    private final List<Course> courses=new ArrayList<>();
    private final List<Student> students=new ArrayList<>();
    private final List<Tutor> tutors =new ArrayList<>();
    private final List<Session> sessions =new ArrayList<>();
    
    public static void main(String[] args)
    {
        new MavTutor();
    }
    public MavTutor()
    {
        menu=new Menu("MavTutor Menu");

        menu.add(new MenuItem("New Course",()->newCourse()));
        menu.add(new MenuItem("New Student",()->newStudent()));
        menu.add(new MenuItem("New Tutor",()->newTutor()));
        menu.add(new MenuItem("New Session",()->newSession()));
        menu.add(new MenuItem("View Data",()->selectView()));
        menu.add(new MenuItem("Quit",()->quit()));

        menu.result.append("Welcome to MavTutor!\n");


        menu.run();
    }
    private void newCourse() 
    {
        
    }

    private void newStudent() {
        
    }

    private void newTutor() {
        
    }

    private void newSession() {
        
    }

    private void selectView(List list) 
    {
        view=list;
    }

    private void quit() 
    {
         menu.result = null;
    }

    @Override
    public String toString()
    {
    StringBuilder sb = new StringBuilder();
    String header = "Data";

    if (currentViewList == courses) header = "Courses";
    else if (currentViewList == students) header = "Students";
    else if (currentViewList == tutors) header = "Tutors";
    else if (currentViewList == sessions) header = "Sessions";

    sb.append(header).append(":\n");
    for (Object obj : currentViewList) 
        {
            sb.append(obj.toString()).append("\n");
        }
    return sb.toString();
    
    }

}


