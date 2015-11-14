(function () {

    'use strict';

    ngQMe

        .controller('qmeCategoryManagementCtrl', QMeCategoryManagementController);

        QMeCategoryManagementController.$inject = ['$state','$scope','qmeFlashService','qmeCategoryService'];

        function QMeCategoryManagementController($state,$scope,qmeFlashService,qmeCategoryService) {

            var qmeCategoryManagement = this;

            qmeCategoryManagement.categoryName      = undefined;
            qmeCategoryManagement.parentId          = undefined;
            qmeCategoryManagement.categoryParents   = [
                {
                    "categoryId" : 0,
                    "categoryName" : ""

                }
            ];

            qmeCategoryManagement.listCategories = function(treecallback, parentId){

                qmeCategoryService.listCategoryByParent(parentId)
                    .then(
                        function(res){
                            treecallback(qmeCategoryManagement.processJsTreeData(res,parentId));
                        },
                        function(error){
                            if(error && error.status && error.status == 403) {
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else {
                                qmeFlashService.Error("Oops.....Error from service getting category lists, please retry in some time.");
                            }

                        }
                    );
            };

            qmeCategoryManagement.selectNode = function(selectedNode){
                if(selectedNode !== null && selectedNode !== undefined && selectedNode.data !== null && selectedNode.data !== undefined){
                    qmeCategoryManagement.categoryName = selectedNode.data.categoryName;
                    qmeCategoryManagement.parentId  =   ""+selectedNode.data.parentCategoryId+"";
                    $scope.$digest();
                }
            };

            qmeCategoryManagement.processJsTreeData = function(categorylist,parentId){
                var nodeData = [];

                for (var key in categorylist) {
                    var category = categorylist[key];

                    if (category.hasOwnProperty('categoryId')){

                        qmeCategoryManagement.categoryParents.push(category);

                        if(parentId === 0){
                            parentId = "#";
                        }else{
                            parentId = ""+parentId+"";
                        }
                        var categoryNode = {
                            "id": ""+category.categoryId+"",
                            "parent" : parentId,
                            "text": ""+category.categoryName+"",
                            "icon": "glyphicon glyphicon-link",
                            "children": true,
                            "data": category
                        };

                        nodeData.push(categoryNode);
                    }
                }
                return nodeData;
            }

        }

})();