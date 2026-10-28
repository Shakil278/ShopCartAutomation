
package utilities;

public class LoginDataSet {

    private LoginData validLogin;
    private LoginData invalidLogin;

    public LoginDataSet() {
    }

    public LoginData getValidLogin() {
        return validLogin;
    }

    public LoginData getInvalidLogin() {
        return invalidLogin;
    }

    public void setValidLogin(LoginData validLogin) {
        this.validLogin = validLogin;
    }

    public void setInvalidLogin(LoginData invalidLogin) {
        this.invalidLogin = invalidLogin;
    }
}
