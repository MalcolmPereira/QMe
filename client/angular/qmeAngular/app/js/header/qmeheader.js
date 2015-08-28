(function () {
    'use strict';

    angular.module(qmeApp)
        .controller('qmeHeaderCtrl', QMeHeaderController);

    QMeHeaderController.$inject = ['$state','qmeAuthService','qmeUserService'];

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
                if(qmeAuthService.user() && qmeAuthService.user().userfirstname() && qmeAuthService.user().userfirstname().length > 0
                    && qmeAuthService.user().userlastname() && qmeAuthService.user().userlastname().length > 0
                ){
                   return   qmeAuthService.user().userfirstname() + " "+ qmeAuthService.user().userlastname();

                }else if(qmeAuthService.user() && qmeAuthService.user().userfirstname() && qmeAuthService.user().userfirstname().length > 0){
                    return   qmeAuthService.user().userfirstname();

                }else{
                    return qmeAuthService.username();
                }
            }
            return "";
        }

        qmeHeader.performSignIn = function (){
            var credentials = {
                "username": qmeHeader.userEmail,
                "password": qmeHeader.userPassword
            };
            qmeAuthService.login(credentials);
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