part of qme;

@Component(
    selector: 'qme-header',
    templateUrl: 'packages/qme/component/qme_header.html',
    useShadowDom: false)
class QMeHeaderComponent {
  QMeUser user = new QMeUser();

  NgForm signInForm;

  bool isRegistering;

  bool isResetingPassword;

  Router router;

  QMeHeaderComponent(Router router) {
    this.router = router;
  }

  void performSignIn() {

    print("hello");

    HttpRequest request = new HttpRequest();
    var url = "http://localhost:8080/qme/user";
    String username = "testadmin";
    String password = "testtest";
    final auth = CryptoUtils.bytesToBase64(UTF8.encode("$username:$password"));
    request.open("GET", url, false,"","");
    request.setRequestHeader('authorization',"Basic $auth");
    request.setRequestHeader('content-type',"application/json");
    request.setRequestHeader("accept", "application/json");
    request.setRequestHeader("Access-Control-Allow-Origin", "*");
    request.send();




    print(user.userEmail);
    print(user.userPassword);
    QmeErrorHolder.instance.setError("some error message");
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
}
