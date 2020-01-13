package googleCloudSelenideDemo.businessObjects;

public class VariableAndValueForAssertion {

    private String variable;
    private  String value;

    public VariableAndValueForAssertion(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }

    public String getVariable() {
        return variable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
