(function () {

    "use strict";

    ngQMe
        .service('qmeAuthService', QMeAuthService);


    QMeAuthService.$inject = ['$q','qmeUserResource','qmeUserSession','QME_CONSTANTS'];


    function QMeAuthService($q,qmeUserResource,qmeUserSession,QME_CONSTANTS) {

        var qmeAuthService = this;

        qmeAuthService.registering = false;

        qmeAuthService.resetting   = false;

        qmeAuthService.login = function (credentials) {

            var loginPromise = $q.defer();

            qmeUserResource.userAuthResource()

                .save(credentials,

                function(res){

                    qmeUserSession.create(
                       res.authToken,
                       res.userID,
                       res.userName,
                       res.userFirstName,
                       res.userLastName,
                       res.userEmail,
                       res.userLastLoginDate,
                       res.userRoles
                    );

                    loginPromise.resolve(res);

                },
                function(error){
                    loginPromise.reject(error);

                });

            return loginPromise.promise;
        };

        qmeAuthService.registeredUser = function (user) {

            qmeUserSession.create(
                user.authToken,
                user.userID,
                user.userName,
                user.userFirstName,
                user.userLastName,
                user.userEmail,
                user.userLastLoginDate,
                user.userRoles
            );
        };

        qmeAuthService.isSignedIn = function(){
            if (qmeUserSession.userid() && qmeUserSession.userid() !== null && qmeUserSession.authtoken() && qmeUserSession.authtoken() !== null){
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
            qmeUserResource.logoutResource(qmeUserSession.authtoken()).save();
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