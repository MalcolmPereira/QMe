(function () {

	"use strict";

    ngQMe

        .directive('qmeTree', function (){
            return {
                restrict: 'E',
                template: '<div id="qmeTreeId"></div>',
                controller: 'qmeTreeCtrl',
                controllerAs: 'qmeTreeCtrl',
                scope: {
                    treeData: '=',
                    treeFunction: '&'
                }
            };
        })
        .controller('qmeTreeCtrl',QMeTreeController);
        QMeTreeController.$inject = ['$scope'];
        function QMeTreeController($scope) {
            var tfunc = $scope.treeFunction;
            var treeConfig = {
                "core" : {
                    "data" : function (obj, cb) {

                        cb.call(this, tfunc()(0));
                    }
                },
                "plugins" : [ "wholerow"]
            };
            //treeConfig.core.data = $scope.treeData
            //console.log("treeData",$scope.treeData);
            $("#qmeTreeId").jstree(treeConfig);
        }

})();