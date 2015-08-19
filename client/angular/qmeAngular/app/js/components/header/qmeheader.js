(function () {
    'use strict';

    qmeApp.controller('qmeHeader', QMeHeaderController);

    QMeHeaderController.$inject = ['qmeFlashService','qmeAuthService','$location','$scope','USER_ROLES','AUTH_EVENTS'];

    function QMeHeaderController(qmeFlashService,qmeAuthService,$location,$scope,USER_ROLES,AUTH_EVENTS) {

        var qmeHeader = this;

        qmeHeader.isRegistering = false;
        qmeHeader.isResetingPassword = false;
        qmeHeader.signedIn = false;

        qmeHeader.userEmail = "";
        qmeHeader.userPassword = "";
        qmeHeader.userName = "";

        qmeHeader.performSignIn = function (){
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            qmeHeader.signedIn = true;

            //TODO:
            //Make http call to service and log in user
            //qmeFlashService.Error("testing");
            qmeHeader.userName = "tocallservice";
        }

        qmeHeader.routeRegistration = function (){
            qmeHeader.isRegistering = true;
            $location.path( '/register' );
        }

        qmeHeader.routeResetPassword = function (){
            qmeHeader.isResetingPassword = true;
            $location.path( '/reset' );
        }

        qmeHeader.cancelResetRegistration = function (){
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            $location.path( '/' );
        }

        qmeHeader.logout = function (){
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            qmeHeader.signedIn = false;
            qmeHeader.userEmail = "";
            qmeHeader.userPassword = "";
            qmeFlashService.Clear();
        }
    }

})();