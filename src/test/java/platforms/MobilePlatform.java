package platforms;

public interface MobilePlatform extends JPMCPlatform{

    void navigateToSignUp() throws Exception;

    void signUpWithEmail(String email, String password) throws Exception;

    void validatePasswordErrorMessage(String message) throws Exception;

    void navigateToPersonalSettings() throws Exception;

    void validateAddress(String state) throws Exception;

    void changeAddress(String state) throws Exception;

    void getStateName(String stateKey) throws Exception;
}
