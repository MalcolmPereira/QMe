(function () {

    "use strict";

    angular.module(qmeApp)
        .service('qmeAuthService', QMeAuthService);


    QMeAuthService.$inject = ['$http','$resource','$base64','qmeFlashService','qmeUserSession','QME_CONSTANTS'];


    function QMeAuthService($http,$resource,$base64,qmeFlashService,qmeUserSession,QME_CONSTANTS) {

        var qmeAuthService = this;

        qmeAuthService.login = function (credentials) {

            //TODO: Fix Basic Authentication and Session ID
            //Basic Authentication For Now (Need to fix with OAuth and Cache OAuth Token)
            var authToken = 'Basic ' + $base64.encode(credentials.username + ':' + credentials.password);
            $http.defaults.headers.common['Authorization'] = authToken;

            //SessionId is hard coded and is not used need to be fixed.

            $resource(QME_CONSTANTS.authendpoint+credentials.username).get(function(res){
                console.log("got res",res);

                qmeUserSession.create('sessionId123',authToken,res.id,res.name,res.role);

            },function(error){
                if(error && error.status && error.status == 404){
                    qmeFlashService.Error("User not found");
                }else{
                    qmeFlashService.Error("Error Connecting to service");
                }
             });
        }

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