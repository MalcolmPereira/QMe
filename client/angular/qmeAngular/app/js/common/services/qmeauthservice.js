(function () {

    "use strict";

    ngQMe
        .service('qmeAuthService', QMeAuthService);


    QMeAuthService.$inject = ['$q','qmeUserResource','$base64','qmeUserSession','QME_CONSTANTS'];


    function QMeAuthService($q,qmeUserResource,$base64,qmeUserSession,QME_CONSTANTS) {

        var qmeAuthService = this;

        qmeAuthService.registering = false;

        qmeAuthService.resetting   = false;

        qmeAuthService.login = function (credentials) {

            var loginPromise = $q.defer();

            //TODO: Fix Basic Authentication and Session ID
            //Basic Authentication For Now (Need to fix with OAuth and Cache OAuth Token)
            var authToken = 'Basic ' + $base64.encode(credentials.username + ':' + credentials.password);
            //SessionId is hard coded and is not used need to be fixed.

            qmeUserResource.userAuthResource(authToken,credentials.username)

                .get(
                function(res){

                    qmeUserSession.create(
                       'sessionId123',
                       authToken,
                       res.userId,
                       res.userName,
                       res.userFirstName,
                       res.userLastName,
                       res.userEmail,
                       res.userRoles
                    );

                    loginPromise.resolve(res);

                },
                function(error){
                    loginPromise.reject(error);

                });

            return loginPromise.promise;
        };

        qmeAuthService.isSignedIn = function(){
            if (qmeUserSession.userid() && qmeUserSession.userid() !== null){
                return true;
            }else{
                return false;
            }
        };

        qmeAuthService.isAdmin = function(){
            return (
            qmeAuthService.isSignedIn() &&
            qmeUserSession.userrole() &&
            qmeUserSession.userrole().length > 0 &&
            qmeUserSession.userrole().indexOf(QME_CONSTANTS.adminrole) > -1);
        };

        qmeAuthService.logout = function (){
            qmeUserResource.logoutResource().save();
            qmeUserSession.destroy();
        };

        qmeAuthService.user = function(){
            return qmeUserSession;
        };

        qmeAuthService.authToken = function(){
            return qmeUserSession.authtoken();
        };

        qmeAuthService.username = function(){
            return qmeUserSession.username();
        };

        qmeAuthService.startRegistering = function(){
            qmeAuthService.registering = true;
        };

        qmeAuthService.endRegistering = function(){
            qmeAuthService.registering = false;
        };

        qmeAuthService.isRegistering = function(){
            return qmeAuthService.registering;
        };

        qmeAuthService.startResetting = function(){
            qmeAuthService.resetting  = true;
        };

        qmeAuthService.endResetting = function(){
            qmeAuthService.resetting   = false;
        };

        qmeAuthService.isResetting = function(){
            return qmeAuthService.resetting;
        };

    }
})();