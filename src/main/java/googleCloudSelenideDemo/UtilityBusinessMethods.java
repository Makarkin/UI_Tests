package googleCloudSelenideDemo;

import googleCloudSelenideDemo.businessObjects.VariableAndValueForAssertion;
import googleCloudSelenideDemo.businessObjects.AssertionOfVariableAndValue;
import googleCloudSelenideDemo.pages.GoogleCloudCalculatorPage;
import io.qameta.allure.Step;

public class UtilityBusinessMethods {

    @Step("Check estimate results")
    public static boolean checkTheEstimateResultsOnPage(AssertionOfVariableAndValue assertionOfVariableAndValue, GoogleCloudCalculatorPage page) {
        boolean hasNoFalseResult = true;
        for (VariableAndValueForAssertion variableAndValueForAssertion : assertionOfVariableAndValue.getVariableAndValueForAssertions()) {
            if (!page.checkTheEstimateWindow(variableAndValueForAssertion.getVariable(), variableAndValueForAssertion.getValue())) {
                hasNoFalseResult = false;
                break;
            }
        }

        return hasNoFalseResult;
    }
}
