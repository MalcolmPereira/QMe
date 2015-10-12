(function () {

    'use strict';

    ngQMe

        .directive('qmeNavigation', function() {
            return {
                restrict: 'E',
                controller: 'qmeNavCtrl',
                controllerAs: 'qmeNavCtrl',
                templateUrl: 'js/common/directives/qmenavigation.tmpl.html'
            };
        })

        .controller('qmeNavCtrl', QMeNavigationController);

        QMeNavigationController.$inject = ['$state','qmeUserSession'];

        function QMeNavigationController($state,qmeUserSession) {

        }

})();