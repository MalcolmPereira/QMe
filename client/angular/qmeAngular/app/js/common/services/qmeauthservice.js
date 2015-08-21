(function () {

    "use strict";

    qmeApp.service('qmeAuthService', QMeAuthService);

    QMeAuthService.$inject = ['$http','qmeUserSession','QME_CONSTANTS'];

    function QMeAuthService($http,qmeUserSession,QME_CONSTANTS) {

        var qmeAuthService = this;

        qmeAuthService.login = function (credentials) {
            return $http
                .post(QME_CONSTANTS.authendpoint, credentials)
                .then(function (res) {
                    qmeUserSession.create('sessionId123',res.data.id,res.data.name,res.data.role);
                    return res.data;
                });
        };

        qmeAuthService.logout = function () {
            qmeUserSession.destroy();
        };

        qmeAuthService.isSignedIn= function () {
            return qmeUserSession.userId;
        };

        qmeAuthService.isAdminUser= function () {
            return (qmeAuthService.isSignedIn() &&
            qmeUserSession.userRole === QME_CONSTANTS.adminrole);
        };

    }

})();