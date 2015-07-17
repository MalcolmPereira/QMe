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
    String username = user.userEmail;
    String password = user.userPassword;
    print(username );
    print(password );
    final auth = CryptoUtils.bytesToBase64(UTF8.encode("$username:$password"));
    HttpRequest.request(
         "http://localhost:8080/qme/user/search/$username",
         method:"GET",
         withCredentials:true,
         responseType:"application/json",
         mimeType:"application/json",
         requestHeaders:
         {
            "Authorization":"Basic $auth",
            "content-type":"text/plain",
            "accept":"application/json"
         }
      )
    .then((HttpRequest resp) {

    });
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
