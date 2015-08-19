(function () {
    'use strict';

    qmeApp.controller('qmeHeader', QMeHeaderController);

    QMeHeaderController.$inject = ['qmeFlashService','qmeAuthService','$location','$rootScope','USER_ROLES','AUTH_EVENTS'];

    function QMeHeaderController(qmeFlashService,qmeAuthService,$location,$rootScope,USER_ROLES,AUTH_EVENTS) {

        var qmeHeader = this;

        qmeHeader.isRegistering = false;
        qmeHeader.isResetingPassword = false;
        qmeHeader.signedIn = false;

        qmeHeader.userEmail = "";
        qmeHeader.userPassword = "";
        qmeHeader.userName = "";

        qmeHeader.performSignIn = function (){

            var credentials = {
                "username": qmeHeader.userEmail,
                "password": qmeHeader.userPassword
            };

            qmeAuthService.login(credentials).then(function (user) {
                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                qmeHeader.userName = user.name;
            }, function () {
                $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
            });


            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            qmeHeader.signedIn = true;

            //TODO:
            //Make http call to service and log in user
            //qmeFlashService.Error("testing");

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