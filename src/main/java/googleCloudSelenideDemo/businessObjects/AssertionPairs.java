package googleCloudSelenideDemo.businessObjects;

import java.util.ArrayList;

public class AssertionPairs {

    private ArrayList<AssertionPair> assertionPairs;

    public AssertionPairs() {
        this.assertionPairs = new ArrayList<>();
        assertionPairs.add(new AssertionPair("VM class", "Regular"));
        assertionPairs.add(new AssertionPair("Instance type", "n1-standard-8"));
        assertionPairs.add(new AssertionPair("Region", "Frankfurt"));
        assertionPairs.add(new AssertionPair("Commitment term", "1 Year"));
    }

    public ArrayList<AssertionPair> getAssertionPairs() {
        return assertionPairs;
    }
}
