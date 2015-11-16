(function () {

    'use strict';

    ngQMe
        .service('qmeFlashService', QMeFlashService);

    QMeFlashService.$inject = ['$rootScope'];

    function QMeFlashService($rootScope) {

        var qmeFlashService = this;

        initQMeFlashService();

        function initQMeFlashService() {
            $rootScope.$on('$locationChangeStart', function () {
                qmeFlashService.Clear();
            });
        }

        qmeFlashService.Clear   = function() {
            var flash = $rootScope.flash;
            if (flash) {
                if (!flash.keepAfterLocationChange) {
                    delete $rootScope.flash;
                } else {
                    flash.keepAfterLocationChange = false;
                }
            }
        };

        qmeFlashService.Success = function(message, keepAfterLocationChange) {
            $rootScope.flash = {
                message: message,
                type: 'success',
                keepAfterLocationChange: keepAfterLocationChange
            };
        };

        qmeFlashService.Error   = function(message, keepAfterLocationChange) {
            $rootScope.flash = {
                message: message,
                type: 'error',
                keepAfterLocationChange: keepAfterLocationChange
            };
        };
    }
})();