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
                    'data' : function (node, cb) {
                        if(node.id === "#") {
                            cb(tfunc()(0));
                        }
                        else {
                            cb(tfunc()(1));
                        }
                    }
                 },
                "plugins" : [ "wholerow"]
            };
            $("#qmeTreeId").jstree(treeConfig);
            //treeConfig.core.data = $scope.treeData
            //console.log("treeData",$scope.treeData);
            //$("#qmeTreeId").jstree(treeConfig).bind("select_node.jstree", function (event, data) {
                //console.log("got data node id ",data.node.id);
                //var newNode = { state: "open", data: tfunc()(1) };
                //console.log("got newNode ",newNode);
                //$('#qmeTreeId').jstree("create_node", data.node, data.node.id, newNode, false, false);
                //data.node.open_node(this);
                //$('#qmeTreeId').jstree("select_node", data.node, data.node.id, newNode, false, false);
            //});
        }

})();