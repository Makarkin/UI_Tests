package restAssuredDemo.deserialization;

public class StartInfo {

    private long id = 0;
    private String greeting = null;
    private User user = null;

    public StartInfo(long id, String greetings, User user) {
        this.id = id;
        this.greeting = greetings;
        this.user = user;
    }

    public StartInfo() {
    }

    public long getId() {
        return id;
    }

    public String getGreeting() {
        return greeting;
    }

    public User getUser() {
        return user;
    }
}
