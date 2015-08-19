(function () {

    "use strict";

    qmeApp.factory('qmeAuthService', QMeAuthService);

    QMeAuthService.$inject = ['$http','qmeSession'];

    function QMeAuthService($http,qmeSession) {
        var qmeAuthService = {};

        qmeAuthService.login = function (credentials) {
            return $http
                .post('/login', credentials)
                .then(function (res) {
                    qmeSession.create(res.data.id, res.data.user.id,
                        res.data.user.role);
                    return res.data.user;
                });
        };

        qmeAuthService.isAuthenticated = function () {
            return !!qmeSession.userId;
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