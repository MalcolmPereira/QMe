(function () {
    'use strict';

    qmeApp.controller('qmeHeader', QMeHeaderController);

    QMeHeaderController.$inject = ['qmeFlashService','$location'];

    function QMeHeaderController(qmeFlashService,$location) {

        var qmeHeader = this;

        qmeHeader.isRegistering = false;
        qmeHeader.isResetingPassword = false;
        qmeHeader.signedIn = false;

        qmeHeader.userEmail = "";
        qmeHeader.userPassword = "";
        qmeHeader.userName = "";

        qmeHeader.performSignIn = performSignIn;
        qmeHeader.routeRegistration = routeRegistration;
        qmeHeader.routeResetPassword = routeResetPassword;
        qmeHeader.cancelResetRegistration = cancelResetRegistration;
        qmeHeader.logout = logout

        function performSignIn(){
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            qmeHeader.signedIn = true;

            //TODO:
            //Make http call to service and log in user
            //qmeFlashService.Error("testing");
            qmeHeader.userName = "tocallservice";
        }

        function routeRegistration(){
            qmeHeader.isRegistering = true;
            $location.path( '/register' );
        }

        function routeResetPassword(){
            qmeHeader.isResetingPassword = true;
            $location.path( '/resetpassword' );
        }

        function cancelResetRegistration(){
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            $location.path( '/' );
        }

        function logout(){
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            qmeHeader.signedIn = false;
            qmeHeader.userEmail = "";
            qmeHeader.userPassword = "";
            qmeFlashService.Clear();
        }
    }

})();