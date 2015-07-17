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

  bool signedIn = false;

  Router router;

  QMeHeaderComponent(Router router) {
    this.router = router;
  }

  void performSignIn() {
    String username = user.userEmail;
    String password = user.userPassword;
    final auth = CryptoUtils.bytesToBase64(UTF8.encode("$username:$password"));
    HttpRequest.request(
         QMeAppModule.user_service_search + "$username",
         method:QMeAppModule.service_get,
         withCredentials:true,
         responseType:QMeAppModule.service_json,
         mimeType:QMeAppModule.service_json,
         requestHeaders:
         {
            "Authorization":"$QMeAppModule.service_auth_basic $auth",
            "content-type":QMeAppModule.service_json,
            "accept":QMeAppModule.service_json
         }
      )
    .then((HttpRequest resp) {
        if (resp.readyState == HttpRequest.DONE && (resp.status == 200 || resp.status == 0)){
          print("this is success !!!");
          print(resp.responseText);
          this.signedIn = true;

        }else if (resp.readyState == HttpRequest.DONE && (resp.status != 200 && resp.status != 0)){
          print("this is error !!!");
          print(resp.responseText);
          this.signedIn = false;
        }
    })
    .catchError((error) {
        print("this is in error!!!");
        print(error.target.responseText); // Current target should be you HttpRequest
        this.signedIn = false;
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
