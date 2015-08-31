(function () {
    'use strict';

    angular.module(qmeApp)
        .controller('qmeHeaderCtrl', QMeHeaderController);

    QMeHeaderController.$inject = ['$state','qmeFlashService','qmeAuthService','qmeUserService'];

    function QMeHeaderController($state,qmeFlashService, qmeAuthService,qmeUserService) {

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

            qmeFlashService.Clear();

            var credentials = {
                "username": qmeHeader.userEmail,
                "password": qmeHeader.userPassword
            };

            qmeAuthService.login(credentials)

                .then(

                function(res){
                    $state.go('home', {});
                },
                function(error){
                    if(error && error.status && error.status == 401){
                        qmeFlashService.Error("Oops.....User not authorized, please register or click on forgot password.");

                    }else if(error && error.status && error.status == 404){
                        qmeFlashService.Error("Error Connecting to service, entered user credential not found.");

                    }else{
                        qmeFlashService.Error("Oops.....Error Connecting to service, please retry in some time.");
                    }
                }
            );
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