part of qme;

@Component(
    selector: 'qme-register',
    templateUrl: 'packages/qme/component/qme_register.html',
    useShadowDom: false)
class QMeRegisterComponent {

    QMeUser user = new QMeUser();

    NgForm registerForm;

    //Router router;

    //QMeRegisterComponent(Router router) {
    //    this.router = router;
    //}

    void registerUser() {
      if (!QmeErrorHolder.instance.isError) {
        print(user.userEmail);
        print(user.userPassword);
        QmeErrorHolder.instance.setError("some error message");
      }
    }

    void validatePassword() {
        if(user.userPassword != user.userPasswordConfirm){
          QmeErrorHolder.instance.setError("Password do not match, please confirm password");
          user.userPasswordConfirm ="";
        }else{
          QmeErrorHolder.instance.removeError();
        }

    }


}
