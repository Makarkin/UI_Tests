package googleCloudSelenideDemo.businessObjects;

import java.util.ArrayList;

public class AssertionOfVariableAndValue {

    private ArrayList<VariableAndValueForAssertion> variableAndValueForAssertions;

    public AssertionOfVariableAndValue() {
        this.variableAndValueForAssertions = new ArrayList<>();
        variableAndValueForAssertions.add(new VariableAndValueForAssertion("VM class", "Regular"));
        variableAndValueForAssertions.add(new VariableAndValueForAssertion("Instance type", "n1-standard-8"));
        variableAndValueForAssertions.add(new VariableAndValueForAssertion("Region", "Frankfurt"));
        variableAndValueForAssertions.add(new VariableAndValueForAssertion("Commitment term", "1 Year"));
    }

    public ArrayList<VariableAndValueForAssertion> getVariableAndValueForAssertions() {
        return variableAndValueForAssertions;
    }
}
