(function () {

    'use strict';

    ngQMe

        .directive('qmePage', function(QME_CONSTANTS) {
            return {
                restrict: 'AE',
                scope: {
                    count :  '=',
                    resource: '='
                },
                templateUrl: 'js/common/directives/qmepagination.tmpl.html'
            };
        })
        //.controller('qmePageCtrl', QMePaginationController);
        //QMePaginationController.$inject = ['$state','qmeUserSession'];

})();