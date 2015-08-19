(function () {

    "use strict";

    qmeApp.factory('qmeAuthService', QMeAuthService);

    QMeAuthService.$inject = ['$http','qmeSession'];

    function QMeAuthService($http,qmeSession) {
        var qmeAuthService = {};

        qmeAuthService.login = function (credentials, callback) {

            return $http
                .post('/login', credentials)
                .then(function (res) {
                    console.log(res.data);
                    qmeSession.create("123456", res.data.id,
                        res.data.name, res.data.role);
                    return res.data;
            });

        };

        qmeAuthService.isAuthenticated = function () {
            return !qmeSession.userId;
        };

        qmeAuthService.isAuthorized = function (authorizedRoles) {
            if (!angular.isArray(authorizedRoles)) {
                authorizedRoles = [authorizedRoles];
            }
            return (qmeAuthService.isAuthenticated() &&
            authorizedRoles.indexOf(qmeSession.userRole) !== -1);
        };

        return qmeAuthService;

    }

})();