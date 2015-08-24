(function () {

    "use strict";

    qmeApp.service('qmeAuthService', QMeAuthService);

    QMeAuthService.$inject = ['$http','qmeFlashService','qmeUserSession','QME_CONSTANTS'];

    function QMeAuthService($http,qmeFlashService,qmeUserSession,QME_CONSTANTS) {

        var qmeAuthService = this;

        qmeAuthService.login = function (credentials) {
            $http
                .post(QME_CONSTANTS.authendpoint, credentials)
                .then(function (res) {
                    if(res && res.status && res.status === 200){
                        qmeUserSession.create('sessionId123',res.data.id,res.data.name,res.data.role);
                    }else{
                        qmeFlashService.Error("User not found");
                    }
                })
                .catch(function (error) {
                    if(error && error.status && error.status == 404){
                        qmeFlashService.Error("User not found");
                    }else{
                        qmeFlashService.Error("Error Connecting to service");
                    }
                })
                .finally(function () {
                    //TODO - some logging
                    console.log("finally cleaning up ");
                });
            ;
        };

        qmeAuthService.isSignedIn = function(){
            if(qmeUserSession.userid() && qmeUserSession.userid() !== null){
                return true;
            }else{
                return false;
            }
        }

        qmeAuthService.isAdmin = function(){
            return (qmeAuthService.isSignedIn() &&
            qmeUserSession.userrole() === QME_CONSTANTS.adminrole);
        }

        qmeAuthService.logout = function (){
            qmeUserSession.destroy();
        }

        qmeAuthService.user = function(){
            return qmeUserSession;
        }

        qmeAuthService.username = function(){
            return qmeUserSession.username();
        }
    }
})();