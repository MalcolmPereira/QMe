(function () {
    'use strict';

    qmeApp.controller('qmeHeaderCtrl', QMeHeaderController);

    QMeHeaderController.$inject = ['$state','qmeAuthService','qmeFlashService','qmeUserSession','QME_CONSTANTS'];

    function QMeHeaderController($state,qmeAuthService,qmeFlashService,qmeUserSession,QME_CONSTANTS) {

        var qmeHeader = this;

        qmeHeader.isRegistering = false;
        qmeHeader.isResetingPassword = false;
        qmeHeader.userEmail = "";
        qmeHeader.userPassword = "";
        qmeHeader.userName = "";

        qmeHeader.isSignedIn = function(){
            if(qmeUserSession.userid() && qmeUserSession.userid() !== null){
                return true;
            }else{
                return false;
            }
        }

        qmeHeader.isAdmin = function(){
            return (qmeHeader.isSignedIn() &&
            qmeUserSession.userrole() === QME_CONSTANTS.adminrole);
        }

        qmeHeader.performSignIn = function (){
            var credentials = {
                "username": qmeHeader.userEmail,
                "password": qmeHeader.userPassword
            };
            qmeAuthService.login(credentials)
                .then(function (res) {
                    if(res && res.status && res.status === 200){
                        qmeUserSession.create('sessionId123',res.data.id,res.data.name,res.data.role);
                        qmeHeader.userName = res.data.name;

                    }else{
                        qmeFlashService.Error("User not found");
                        qmeHeader.isRegistering = false;
                        qmeHeader.isResetingPassword = false;
                    }
                })
                .catch(function (error) {
                    if(error && error.status && error.status == 404){
                        qmeFlashService.Error("User not found");
                    }else{
                        qmeFlashService.Error("Error Connecting to service");
                    }
                    qmeHeader.isRegistering = false;
                    qmeHeader.isResetingPassword = false;
                })
                .finally(function () {
                    //TODO - some logging
                });
        }

        qmeHeader.logout = function (){
            qmeUserSession.destroy();
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