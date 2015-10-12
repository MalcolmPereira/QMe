(function () {

    'use strict';

    ngQMe

        .directive('qmeNavigation', function() {
            return {
                restrict: 'E',
                controller: 'qmeUserCtrl',
                controllerAs: 'qmeUserCtrl',
                templateUrl: 'js/common/directives/qmenavigation.tmpl.html'
            };
        });

})();