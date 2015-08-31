(function () {

    "use strict";

    angular.module(qmeApp)
        .service('qmeAuthService', QMeAuthService);


    QMeAuthService.$inject = ['$q','$http','$resource','$base64','qmeUserSession','QME_CONSTANTS'];


    function QMeAuthService($q,$http,$resource,$base64,qmeUserSession,QME_CONSTANTS) {

        var qmeAuthService = this;

        qmeAuthService.login = function (credentials) {

            var loginPromise = $q.defer();

            //TODO: Fix Basic Authentication and Session ID
            //Basic Authentication For Now (Need to fix with OAuth and Cache OAuth Token)
            var authToken = 'Basic ' + $base64.encode(credentials.username + ':' + credentials.password);
            $http.defaults.headers.common['Authorization'] = authToken;

            //SessionId is hard coded and is not used need to be fixed.

            $resource(QME_CONSTANTS.authendpoint+credentials.username)

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

        qmeAuthService.registeredUser = function (registeredUser) {

            qmeAuthService.logout();

            //TODO: Fix Basic Authentication and Session ID
            //Basic Authentication For Now (Need to fix with OAuth and Cache OAuth Token)
            var authToken = 'Basic ' + $base64.encode(registeredUser.userEmail + ':' + registeredUser.userPassword);
            $http.defaults.headers.common['Authorization'] = authToken;

            //SessionId is hard coded and is not used need to be fixed.
            qmeUserSession.create(
                    'sessionId123',
                    authToken,
                    registeredUser.userId,
                    registeredUser.userName,
                    registeredUser.userFirstName,
                    registeredUser.userLastName,
                    registeredUser.userEmail,
                    registeredUser.userRoles
            );
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
            qmeUserSession.destroy();
        };

        qmeAuthService.user = function(){
            return qmeUserSession;
        };

        qmeAuthService.username = function(){
            return qmeUserSession.username();
        };
    }
})();