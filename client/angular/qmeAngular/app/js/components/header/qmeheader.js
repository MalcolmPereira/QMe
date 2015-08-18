(function () {
    'use strict';

    qmeApp.controller('qmeHeader', QMeHeaderController);

    QMeHeaderController.$inject = ['qmeFlashService'];

    function QMeHeaderController(qmeFlashService) {

        var qmeHeader = this;

        qmeHeader.isRegistering = false;
        qmeHeader.isResetingPassword = false;
        qmeHeader.signedIn = false;

        qmeHeader.userEmail = "";
        qmeHeader.userPassword = "";

        qmeHeader.performSignIn = performSignIn;
        qmeHeader.routeRegistration = routeRegistration;
        qmeHeader.routeResetPassword = routeResetPassword;
        qmeHeader.cancelResetRegistration = cancelResetRegistration;
        qmeHeader.logout = logout

        function performSignIn(){
            console.log("got useremail " +qmeHeader.userEmail);
            console.log("got userpasword " +qmeHeader.userPassword);
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            qmeHeader.signedIn = true;
            qmeFlashService.Error("testing");
        }

        function routeRegistration(){

        }

        function routeResetPassword(){

        }

        function cancelResetRegistration(){

        }

        function logout(){

        }

    }

})();