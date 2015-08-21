(function () {

    'use strict';

    qmeApp.service('qmeFlashService', QMeFlashService);

    QMeFlashService.$inject = ['$rootScope'];

    function QMeFlashService($rootScope) {

        var qmeFlashService = this;

        qmeFlashService.Success = Success;
        qmeFlashService.Error   = Error;
        qmeFlashService.Clear   = clearFlashMessage;

        initQMeFlashService();

        function initQMeFlashService() {
            $rootScope.$on('$locationChangeStart', function () {
                clearFlashMessage();
            });
        }

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