part of qme;

@Component(
    publishAs: 'qme-header',
    selector: 'qme-header',
    templateUrl: 'packages/qme/component/qme_header.html',
    useShadowDom: false)
class QMeHeaderComponent {
  QMeUser user = new QMeUser();

  NgForm signInForm;

  bool isRegistering;

  bool isResetingPassword;

  bool signedIn = false;

  Router router;

  QMeHeaderComponent(Router router) {
    this.router = router;
  }

  void validateLogin(resp){
    print("in request complete");
    if (resp.readyState == HttpRequest.DONE && resp.status == 200) {
      QmeErrorHolder.instance.removeError();
      Map parsedMap = JSON.decode(resp.responseText);
      if(parsedMap["userFirstName"] == null){
        querySelector("#loginName").text = parsedMap["userEmail"];
      }else{
        querySelector("#loginName").text = parsedMap["userFirstName"] +" "+  parsedMap["userLastName"];
      }
      this.signedIn = true;
    } else if (resp.readyState == HttpRequest.DONE && (resp.status == 401 || resp.status == 404)) {
      QmeErrorHolder.instance.setError("Error connecting to QMe service, please validate credentials and retry request");
      this.signedIn = false;
    } else if (resp.readyState == HttpRequest.DONE && resp.status == 0) {
      QmeErrorHolder.instance.setError("Error connecting to QMe service, please retry request");
      this.signedIn = false;
    }else{
      QmeErrorHolder.instance.setError("Error connecting to QMe service, please retry request");
      this.signedIn = false;
    }
  }

  void performSignIn() {
    QmeErrorHolder.instance.removeError();
    String username = user.userEmail;
    String url = QMeAppModule.user_service_search + "$username";
    QMeAppModule.executeHttp(user.userEmail, user.userPassword,url, QMeAppModule.service_get, "", validateLogin);
  }

  void routeRegistration() {
    this.isRegistering = true;
    this.router.go('register', {});
  }

  void routeResetPassword() {
    this.isResetingPassword = true;
    this.router.go('resetPassword', {});
  }

  void cancelResetRegistration() {
    this.isRegistering = false;
    this.isResetingPassword = false;
    this.router.go('welcome', {});
  }

  void logout() {
    this.isRegistering = false;
    this.isResetingPassword = false;
    this.signedIn = false;
    this.user = new QMeUser();
    this.router.go('welcome', {});
  }
}
