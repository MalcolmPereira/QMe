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
                    treeFunction: '&'
                }
            };
        })
        .controller('qmeTreeCtrl',QMeTreeController);
        QMeTreeController.$inject = ['$scope'];
        function QMeTreeController($scope) {

           var treeConfig = {
                "core" : {
                    'data' : function (node, cb) {
                        if(node.id === "#") {
                            $scope.treeFunction()(cb,0);
                        }
                        else {
                            $scope.treeFunction()(cb,node.id);
                        }
                    }
                 },
                "plugins" : [ "wholerow"],
                "themes": {"dots": false},
                "multiple": false
            };
            $("#qmeTreeId").jstree(treeConfig);
        }

})();