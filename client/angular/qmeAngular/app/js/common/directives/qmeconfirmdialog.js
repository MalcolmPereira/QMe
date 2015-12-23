(function () {

    'use strict';

    ngQMe

     .directive('qmeConfirm', function (){
        return {
            restrict: 'E',
            controller: 'qmeConfirmCtrl',
            controllerAs: 'qmeConfirmCtrl',
            templateUrl: 'js/common/directives/qmeconfirmdialog.tmpl.html',
            scope: {
                functionCall: '&',
                headerMessage: '=',
                bodyMessage: '=',
                functionParam: '='
            }
        };
    })
    .controller('qmeConfirmCtrl',QMeConfirmController);
    QMeConfirmController.$inject = ['$scope'];
    function QMeConfirmController($scope) {

        var qmeConfirm = this;

        qmeConfirm.header = function(){
            return $scope.headerMessage;
        };

        qmeConfirm.message = function(){
            return $scope.bodyMessage;
        };

        qmeConfirm.callFunction = function(){
            $('#qmeConfirmModel').modal('hide');
            $('.modal-backdrop').remove();
            $scope.functionCall()($scope.functionParam);
        };
    }

})();