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
                    treeData: '='
                }
            };
        })
        .controller('qmeTreeCtrl',QMeTreeController);
        QMeTreeController.$inject = ['$scope'];
        function QMeTreeController($scope) {
            var treeConfig = {
                "core" : {
                    "data" : []
                },
                "plugins" : [ "wholerow"]
            };
            treeConfig.core.data = $scope.treeData
            console.log("treeData",$scope.treeData);
            $("#qmeTreeId").jstree(treeConfig);
        }

})();