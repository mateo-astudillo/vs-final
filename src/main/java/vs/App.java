package vs;

import vs.core.persons.Person;
import vs.core.users.User;
import vs.gui.*;
import vs.gui.Window;

import java.time.LocalTime;

public class App {
    private User currentUser;
    private Person currentPerson;
    private final Window window;
    private final PersonView personView;
    private final Results results;
    private LocalTime realOpeningTime;

    public App(Window window) {
        this.window = window;
        personView = new PersonView(this);
        results = new Results(this);
        window.addView(new Opening(this).getPanel(), "opening");
        window.addView(new Validating(this).getPanel(), "validating");
        window.addView(personView.getPanel(), "person");
        window.addView(new Voting(this).getPanel(), "voting");
        window.addView(results.getPanel(), "results");
        window.show("opening");
    }

    public void switchTo(String view) {
        window.show(view);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
        personView.refresh();
    }

    public int getWidth() {
        return window.getWidth();
    }

    public LocalTime getRealOpeningTime() {
        return realOpeningTime;
    }

    public void setRealOpeningTime(LocalTime realOpeningTime) {
        this.realOpeningTime = realOpeningTime;
    }

    public void loadChar() {
        this.results.loadChart();
    }

    public static void main( String[] args ) {
        new App(new Window());
    }
}
