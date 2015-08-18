(function () {

    'use strict';

    qmeApp.factory('qmeFlashService', QMeFlashService);

    QMeFlashService.$inject = ['$rootScope'];

    function QMeFlashService($rootScope) {

        var flashService = {};

        flashService.Success = Success;
        flashService.Error = Error;

        initFlashService();

        return flashService;

        function initFlashService() {
            $rootScope.$on('$locationChangeStart', function () {
                clearFlashMessage();
            });

            function clearFlashMessage() {
                var flash = $rootScope.flash;
                if (flash) {
                    if (!flash.keepAfterLocationChange) {
                        delete $rootScope.flash;
                    } else {
                        flash.keepAfterLocationChange = false;
                    }
                }
            }
        }

        function Success(message, keepAfterLocationChange) {
            $rootScope.flash = {
                message: message,
                type: 'success',
                keepAfterLocationChange: keepAfterLocationChange
            };
        }

        function Error(message, keepAfterLocationChange) {
            $rootScope.flash = {
                message: message,
                type: 'error',
                keepAfterLocationChange: keepAfterLocationChange
            };
        }
    }

})();