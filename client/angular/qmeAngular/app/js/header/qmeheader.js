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

        qmeHeader.isSignedIn = function(){
            return qmeAuthService.isSignedIn();
        }

        qmeHeader.isAdmin = function(){
            return qmeAuthService.isAdmin();
        }

        qmeHeader.userName = function (){
            if(qmeHeader.isSignedIn()){
                return qmeAuthService.username();
            }
            return "";
        }

        qmeHeader.performSignIn = function (){
            var credentials = {
                "username": qmeHeader.userEmail,
                "password": qmeHeader.userPassword
            };
            var username = qmeAuthService.login(credentials);
            if(username && username !== null ){
                qmeHeader.userName = username;
            }else{
                qmeHeader.isRegistering = false;
                qmeHeader.isResetingPassword = false;
            }
        }

        qmeHeader.logout = function (){
            qmeAuthService.logout();
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            qmeHeader.userEmail = "";
            qmeHeader.userPassword = "";
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