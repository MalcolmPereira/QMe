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
