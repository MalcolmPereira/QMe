(function () {

    'use strict';

    ngQMe

        .controller('qmeCategoryManagementCtrl', QMeCategoryManagementController);

        QMeCategoryManagementController.$inject = ['$state','$scope','qmeFlashService','qmeCategoryService'];

        function QMeCategoryManagementController($state,$scope,qmeFlashService,qmeCategoryService) {

            var qmeCategoryManagement = this;

            qmeCategoryManagement.updateCategoryForm   = undefined;
            qmeCategoryManagement.addNew               = true;
            qmeCategoryManagement.categoryName         = undefined;
            qmeCategoryManagement.categoryId           = undefined;
            qmeCategoryManagement.parentId             = undefined;
            qmeCategoryManagement.categoryParentsAll   = [
                {
                    "categoryId" : 0,
                    "categoryName" : ""

                }
            ];
            qmeCategoryManagement.categoryParents      = [];

            qmeCategoryManagement.listCategories = function(treecallback, parentId){

                qmeFlashService.Clear();

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

            qmeCategoryManagement.addNewCategory = function(){
                qmeFlashService.Clear();
                qmeCategoryManagement.categoryParents  = [];
                for(var a in qmeCategoryManagement.categoryParentsAll){
                    var category = qmeCategoryManagement.categoryParentsAll[a];
                    qmeCategoryManagement.categoryParents.push(category);
                }
                $("#qmeTreeId").jstree("deselect_all");
                qmeCategoryManagement.categoryName = "";
                qmeCategoryManagement.parentId     = "0";
                qmeCategoryManagement.addNew       = true;
                qmeCategoryManagement.categoryId   = undefined;
            };

            qmeCategoryManagement.cancelUpdates = function(){
                $("#qmeTreeId").jstree("deselect_all");
                qmeCategoryManagement.categoryName = "";
                qmeCategoryManagement.parentId     = "0";
                qmeCategoryManagement.addNew       = true;
                qmeCategoryManagement.categoryId   = undefined;
            };

            qmeCategoryManagement.isDeletable = function(){
                if(qmeCategoryManagement.addNew){
                    return true;
                }

                if(qmeCategoryManagement.updateCategoryForm.$invalid){
                    return true;
                }

                return true;
            };

            qmeCategoryManagement.deleteCategory = function(categoryid){
                console.log("to delete category ",categoryid);
            };

            qmeCategoryManagement.submitUpdates = function(){
                qmeFlashService.Clear();
                var category;

                if(qmeCategoryManagement.parentId > 0){
                    category = {
                        "categoryName":qmeCategoryManagement.categoryName,
                        "parentCategoryId":qmeCategoryManagement.parentId
                    };
                }else{
                    category = {
                        "categoryName":qmeCategoryManagement.categoryName
                    };
                }

                if(qmeCategoryManagement.addNew ){

                    qmeCategoryService.createCategory(category)
                        .then(
                            function(res){
                                qmeCategoryManagement.categoryName = "";
                                qmeCategoryManagement.parentId     = "0";
                                $("#qmeTreeId").jstree(true).refresh();
                                qmeCategoryManagement.updateCategoryForm.$setPristine();
                            },
                            function(error){
                                if(error && error.status && error.status == 403) {
                                    qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                                }else if(error && error.status && error.status == 404){
                                    qmeFlashService.Error("Oops.....Invalid request Parent Category invalid,not found.");

                                }else if(error && error.status && error.status == 400){
                                    qmeFlashService.Error("Oops.....Invalid request, please make sure valid category name is provided.");

                                }else if(error && error.status && error.status == 409){
                                    qmeFlashService.Error("Oops.....Invalid request, category with name already exists, please use unique valid category name.");

                                }else {
                                    qmeFlashService.Error("Oops.....Error from service getting category lists, please retry in some time.");
                                }
                            }
                        );

                }else{

                    category.categoryId = qmeCategoryManagement.categoryId;
                    qmeCategoryService.updateCategory(category)
                        .then(
                            function(res){
                                qmeCategoryManagement.categoryName = "";
                                qmeCategoryManagement.parentId     = "0";
                                $("#qmeTreeId").jstree(true).refresh();
                                qmeCategoryManagement.updateCategoryForm.$setPristine();
                            },
                            function(error){
                                if(error && error.status && error.status == 403) {
                                    qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                                }else if(error && error.status && error.status == 404){
                                    qmeFlashService.Error("Oops.....Invalid request Parent Category/Category invalid, not found.");

                                }else if(error && error.status && error.status == 400){
                                    qmeFlashService.Error("Oops.....Invalid request, please make sure valid category name is provided.");

                                }else if(error && error.status && error.status == 409){
                                    qmeFlashService.Error("Oops.....Invalid request, category with name already exists, please use unique valid category name.");

                                }else {
                                    qmeFlashService.Error("Oops.....Error from service getting category lists, please retry in some time.");
                                }
                            }
                        );
                }

            };


            qmeCategoryManagement.selectNode = function(selectedNode){
                qmeFlashService.Clear();
                if(selectedNode !== null && selectedNode !== undefined && selectedNode.data !== null && selectedNode.data !== undefined){
                    qmeCategoryManagement.categoryName = selectedNode.data.categoryName;
                    qmeCategoryManagement.categoryId   = selectedNode.data.categoryId;
                    qmeCategoryManagement.categoryParents  = [];

                    for(var a in qmeCategoryManagement.categoryParentsAll){
                        var category = qmeCategoryManagement.categoryParentsAll[a];
                        if(category.categoryId !== selectedNode.data.categoryId){
                            qmeCategoryManagement.categoryParents.push(category);
                        }
                    }
                    qmeCategoryManagement.parentId     = ""+selectedNode.data.parentCategoryId+"";
                    qmeCategoryManagement.addNew       = undefined;
                    $scope.$digest();
                }
            };

            qmeCategoryManagement.processJsTreeData = function(categorylist,parentId){
                var nodeData = [];

                for (var key in categorylist) {
                    var category = categorylist[key];

                    if (category.hasOwnProperty('categoryId')){

                        qmeCategoryManagement.categoryParentsAll.push(category);

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

                qmeCategoryManagement.categoryParents  = [];
                for(var a in qmeCategoryManagement.categoryParentsAll){
                    qmeCategoryManagement.categoryParents.push(qmeCategoryManagement.categoryParentsAll[a]);
                }

                return nodeData;
            };
        }

})();