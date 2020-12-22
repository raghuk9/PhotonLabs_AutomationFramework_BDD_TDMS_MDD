package platforms.Interfaces;

import java.util.List;

public interface FindaCard {

    void clickOnFindaCreditCardlink() throws Exception;

    void validatePageNavigation(String pageTitle) throws InterruptedException;

    void clickOnTryOurCardFinderLink() throws Exception;

    void clickOnPersonalIcon() throws Exception;

    void clickOnRewardsIcon() throws Exception;

    void clickOnCashBackIcon() throws Exception;

    void clickOnNoAnnualFeeIcon() throws Exception;

    void clickOnBalanceTransferIcon() throws Exception;

    void validateCardShowsAsPerTheCriteria(List<String> criteria) throws Exception;

    void clickOnBusinessIcon() throws Exception;

}
