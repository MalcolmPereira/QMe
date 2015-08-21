(function () {
    'use strict';

    qmeApp.controller('qmeHeaderCtrl', QMeHeaderController);

    QMeHeaderController.$inject = ['$state','qmeAuthService'];

    function QMeHeaderController($state,qmeAuthService) {

        var qmeHeader = this;

        qmeHeader.isRegistering = false;
        qmeHeader.isResetingPassword = false;
        qmeHeader.userEmail = "";
        qmeHeader.userPassword = "";
        qmeHeader.userName = "";

        qmeHeader.isSignedIn = function(){
            return qmeAuthService.isSignedIn();
        }

        qmeHeader.isAdmin = function(){
            return qmeAuthService.isAdminUser();
        }

        qmeHeader.performSignIn = function (){
            var credentials = {
                "username": qmeHeader.userEmail,
                "password": qmeHeader.userPassword
            };
            qmeAuthService.login(credentials).then(function (user) {
                qmeHeader.userName = user.name;
            });
        }

        qmeHeader.logout = function (){
            qmeAuthService.logout();
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            qmeHeader.userEmail = "";
            qmeHeader.userPassword = "";
            qmeHeader.userName = "";
        }

        qmeHeader.routeRegistration = function (){
            qmeHeader.isRegistering = true;
            $state.go('register', {});
        }

        qmeHeader.routeResetPassword = function (){
            qmeHeader.isResetingPassword = true;
            $state.go('reset', {});
        }

        qmeHeader.cancelResetRegistration = function (){
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            $state.go('home', {});
        }

    }

})();