package googleCloudSelenideDemo;

        import googleCloudSelenideDemo.businessObjects.AssertionPair;
        import googleCloudSelenideDemo.businessObjects.AssertionPairs;
        import googleCloudSelenideDemo.pages.IAbstractPage;
        import io.qameta.allure.Step;

public class UtilityBusinessMethods {

    @Step()
    public static boolean checkTheEstimateResultsOnPage(AssertionPairs assertionPairs, IAbstractPage page) {
        boolean hasNoFalseResult = true;
        for (AssertionPair assertionPair : assertionPairs.getAssertionPairs()) {
            if (page.checkTheEstimateWindow(assertionPair.getVariable(), assertionPair.getValue())) {
            } else {
                hasNoFalseResult = false;
                break;
            }
        }

        return hasNoFalseResult;
    }
}
