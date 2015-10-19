(function () {

    'use strict';

    ngQMe

        .directive('qmePage', function (){
            return {
                restrict: 'E',
                templateUrl: 'js/common/directives/qmepagination.tmpl.html',
                controller: 'qmePageCtrl',
                controllerAs: 'qmePageCtrl',
                scope: {
                    qmeTotalcount: '=',
                    qmePagingfunction: '&'
                }
            };
        })

        .controller('qmePageCtrl',QMePageController);
        QMePageController.$inject = ['$scope', '$element', '$attrs'];
        function QMePageController($scope) {
            console.log('ctrl.scope url', $scope.qmeTotalcount);
            console.log('ctrl.scope stars', $scope.qmePagingfunction);
        }
})();