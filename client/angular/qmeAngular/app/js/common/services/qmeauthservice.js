(function () {

    "use strict";

    qmeApp.service('qmeAuthService', QMeAuthService);

    QMeAuthService.$inject = ['$http','QME_CONSTANTS'];

    function QMeAuthService($http,QME_CONSTANTS) {

        var qmeAuthService = this;

        qmeAuthService.login = function (credentials) {
            return $http.post(QME_CONSTANTS.authendpoint, credentials);
        };
    }
})();