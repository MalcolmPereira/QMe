(function () {
    'use strict';

    ngQMe
        .controller('qmeLoginCtrl', QMeLoginController);

    QMeLoginController.$inject = ['$state','qmeFlashService','qmeAuthService','qmeUserService'];

    function QMeLoginController($state,qmeFlashService, qmeAuthService) {

        var qmeHeader = this;

        qmeHeader.userEmail = "";
        qmeHeader.userPassword = "";

        qmeHeader.isSignedIn = function(){
            return qmeAuthService.isSignedIn();
        };

        qmeHeader.isAdmin = function(){
            return qmeAuthService.isAdmin();
        };

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
        };

        qmeHeader.performSignIn = function (){

            var credentials;
            qmeFlashService.Clear();

            credentials = {
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
        };

        qmeHeader.logout = function (){
            qmeAuthService.logout();
            qmeAuthService.endRegistering();
            qmeAuthService.endResetting();
            qmeHeader.userEmail = "";
            qmeHeader.userPassword = "";
            qmeHeader.signInForm.$setPristine()
        };

        qmeHeader.routeRegistration = function (){
            qmeAuthService.startRegistering();
            $state.go('register', {});
        };

        qmeHeader.routeResetPassword = function (){
            qmeAuthService.startResetting();
             $state.go('reset', {});
        };

        qmeHeader.cancelResetRegistration = function (){
            qmeAuthService.endRegistering();
            qmeAuthService.endResetting();
            qmeHeader.signInForm.$setPristine()
            $state.go('home', {});
        };

        qmeHeader.isRegistering = function(){
            return  qmeAuthService.isRegistering();
        }

        qmeHeader.isResetingPassword = function(){
            return  qmeAuthService.isResetting();
        }
    }
})();