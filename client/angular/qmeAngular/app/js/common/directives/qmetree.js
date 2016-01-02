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
                    treeFunction: '&',
                    selectFunction: '&'
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
                "plugins" : ["wholerow"],
                "themes": {"dots": false, "icons": false},
                "multiple": false
            };
            $("#qmeTreeId").jstree(treeConfig);

            $("#qmeTreeId").on("select_node.jstree", function(evt, data){
                   $scope.selectFunction()(data.node);
                }
            );
        }

})();