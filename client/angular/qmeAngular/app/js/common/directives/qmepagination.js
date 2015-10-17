(function () {

    'use strict';

    ngQMe

        .directive('qmePage', function() {
            return {
                restrict: 'E',
                templateUrl: 'js/common/directives/qmepagination.tmpl.html',
                scope: false,
                controller: function(){
                    var qmePaging = this;

                    qmePaging.test = function(){
                        console.log("from controller",scope.qmeTotalCount);
                        console.log("from controller",scope.qmePageResource);
                    }
                },
                controllerAs: 'qmePageCtrl',
                //controller: 'qmePageCtrl'
            };
        })


})();