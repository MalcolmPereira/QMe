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
        print(user.userEmail);
        print(user.userPassword);
        QmeErrorHolder.instance.setError("some error message");
    }

}
