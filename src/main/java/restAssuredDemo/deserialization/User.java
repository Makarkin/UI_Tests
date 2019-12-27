package restAssuredDemo.deserialization;

public class User {
    private String name;
    private String secondName;
    private int coinCount;

    public User(String name, String secondName, String coinCount) {
        this.name = name;
        this.secondName = secondName;
        this.coinCount = Integer.valueOf(coinCount);
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public double getCoinCount() {
        return coinCount;
    }
}
