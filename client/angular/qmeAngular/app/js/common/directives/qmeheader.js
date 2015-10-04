(function () {

    'use strict';

    ngQMe

        .directive('qmeHeader', function() {
            return {
                restrict: 'E',
                controller: 'qmeUserCtrl',
                controllerAs: 'qmeUserCtrl',
                templateUrl: 'js/common/directives/qmeheader.tmpl.html'
            };
        });

})();
