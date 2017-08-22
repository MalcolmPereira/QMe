(function () {

    'use strict';

    ngQMe

        .controller('qmeCategoryManagementCtrl', QMeCategoryManagementController);

        QMeCategoryManagementController.$inject = ['$scope','$state','qmeFlashService','qmeCategoryService'];

        function QMeCategoryManagementController($scope,$state,qmeFlashService,qmeCategoryService) {

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

            qmeCategoryManagement.listParentCategories = function(){

                qmeFlashService.Clear();

                qmeCategoryService.listCategory().then(
                    function(res){
                        for (var key in res) {
                            var category = res[key];
                            if (category.hasOwnProperty('categoryId')){
                                if(qmeCategoryManagement.categoryParentsAll.length === 0){
                                    qmeCategoryManagement.categoryParentsAll.push(category);

                                }else{
                                    var isNewCategory =  true;
                                    for(var a in qmeCategoryManagement.categoryParentsAll){
                                        var currentCategory = qmeCategoryManagement.categoryParentsAll[a];
                                        if(category.categoryId === currentCategory.categoryId){
                                            isNewCategory =  false;
                                            break;
                                        }
                                    }
                                    if(isNewCategory){
                                        qmeCategoryManagement.categoryParentsAll.push(category);
                                    }
                                }
                            }
                        }
                        qmeCategoryManagement.categoryParents  = [];
                        for(var b in qmeCategoryManagement.categoryParentsAll){
                            qmeCategoryManagement.categoryParents.push(qmeCategoryManagement.categoryParentsAll[b]);
                        }
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
                qmeFlashService.Clear();
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

                if(qmeCategoryManagement.updateCategoryForm && qmeCategoryManagement.updateCategoryForm.$invalid){
                    return true;
                }

                return true;
            };

            qmeCategoryManagement.deleteCategory = function(categoryid){
                qmeCategoryService.deleteCategory(categoryid)
                    .then(
                        function(res){
                            qmeCategoryManagement.categoryName = "";
                            qmeCategoryManagement.parentId     = "0";
                            if(qmeCategoryManagement.categoryParentsAll.length > 0){
                                for(var a in qmeCategoryManagement.categoryParentsAll){
                                    var currentCategory = qmeCategoryManagement.categoryParentsAll[a];
                                    if(categoryid === currentCategory.categoryId){
                                        delete qmeCategoryManagement.categoryParentsAll[a];
                                        break;
                                    }
                                }
                            }

                            if(qmeCategoryManagement.categoryParents.length > 0){
                                for(var b in qmeCategoryManagement.categoryParents){
                                    var currentParentCategory = qmeCategoryManagement.categoryParents[b];
                                    if(categoryid === currentParentCategory.categoryId){
                                        delete qmeCategoryManagement.categoryParents[b];
                                        break;
                                    }
                                }
                            }

                            if($("#qmeTreeId").jstree(true)){
                                $("#qmeTreeId").jstree(true).refresh();
                            }
                            if(qmeCategoryManagement.updateCategoryForm){
                                qmeCategoryManagement.updateCategoryForm.$setPristine();
                            }
                        },
                        function(error){
                            if(error && error.status && error.status == 403) {
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else if(error && error.status && error.status == 404){
                                qmeFlashService.Error("Oops.....Invalid request Parent Category/Category invalid, not found.");

                            }else if(error && error.status && error.status == 400){
                                qmeFlashService.Error("Oops.....Invalid request, please make sure valid category name is provided.");

                            }else {
                                qmeFlashService.Error("Oops.....Error deleting category , please contact administrator.");
                            }
                        }
                    );
            };

            qmeCategoryManagement.submitAdd = function(){
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

                pleaseWait.showPleaseWait();

                qmeCategoryService.createCategory(category)
                    .then(
                        function(res){
                            pleaseWait.hidePleaseWait();
                            qmeCategoryManagement.categoryName = "";
                            qmeCategoryManagement.parentId     = "0";
                            qmeFlashService.Success("Category submitted successfully, .",true);
                            $state.go('listcategories', {});
                        },
                        function(error){
                            pleaseWait.hidePleaseWait();
                            if(error && error.status && error.status == 403) {
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else if(error && error.status && error.status == 404){
                                qmeFlashService.Error("Oops.....Invalid request Parent Category invalid,not found.");

                            }else if(error && error.status && error.status == 400){
                                qmeFlashService.Error("Oops.....Invalid request, please make sure valid category name is provided.");

                            }else if(error && error.status && error.status == 409){
                                qmeFlashService.Error("Oops.....Invalid request, category with name already exists, please use unique valid category name.");

                            }else {
                                qmeFlashService.Error("Oops.....Error addming new category, please retry in some time.");
                            }
                        }
                    );
            };

            qmeCategoryManagement.cancelAdd = function(){
                qmeFlashService.Clear();
                qmeCategoryManagement.categoryName = "";
                qmeCategoryManagement.parentId     = "0";
                qmeCategoryManagement.addNew       = true;
                qmeCategoryManagement.categoryId   = undefined;
                $state.go('listcategories', {});
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

                    pleaseWait.showPleaseWait();
                    qmeCategoryService.createCategory(category)
                        .then(
                            function(res){
                                pleaseWait.hidePleaseWait();
                                qmeCategoryManagement.categoryName = "";
                                qmeCategoryManagement.parentId     = "0";
                                if($("#qmeTreeId").jstree(true)){
                                    $("#qmeTreeId").jstree(true).refresh();
                                }
                                if(qmeCategoryManagement.updateCategoryForm){
                                    qmeCategoryManagement.updateCategoryForm.$setPristine();
                                }

                            },
                            function(error){
                                pleaseWait.hidePleaseWait();
                                if(error && error.status && error.status == 403) {
                                    qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                                }else if(error && error.status && error.status == 404){
                                    qmeFlashService.Error("Oops.....Invalid request Parent Category invalid,not found.");

                                }else if(error && error.status && error.status == 400){
                                    qmeFlashService.Error("Oops.....Invalid request, please make sure valid category name is provided.");

                                }else if(error && error.status && error.status == 409){
                                    qmeFlashService.Error("Oops.....Invalid request, category with name already exists, please use unique valid category name.");

                                }else {
                                    qmeFlashService.Error("Oops.....Error addming new category, please retry in some time.");
                                }
                            }
                        );

                }else{

                    category.categoryId = qmeCategoryManagement.categoryId;
                    pleaseWait.showPleaseWait();
                    qmeCategoryService.updateCategory(category)
                        .then(
                            function(res){
                                pleaseWait.hidePleaseWait();
                                qmeCategoryManagement.categoryName = "";
                                qmeCategoryManagement.parentId     = "0";
                                if( $("#qmeTreeId").jstree(true)){
                                    $("#qmeTreeId").jstree(true).refresh();
                                }
                                if(qmeCategoryManagement.updateCategoryForm){
                                    qmeCategoryManagement.updateCategoryForm.$setPristine();
                                }
                            },
                            function(error){
                                pleaseWait.hidePleaseWait();
                                if(error && error.status && error.status == 403) {
                                    qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                                }else if(error && error.status && error.status == 404){
                                    qmeFlashService.Error("Oops.....Invalid request Parent Category/Category invalid, not found.");

                                }else if(error && error.status && error.status == 400){
                                    qmeFlashService.Error("Oops.....Invalid request, please make sure valid category name is provided.");

                                }else if(error && error.status && error.status == 409){
                                    qmeFlashService.Error("Oops.....Invalid request, category with name already exists, please use unique valid category name.");

                                }else {
                                    qmeFlashService.Error("Oops.....Error updating category, please retry in some time.");
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
                    qmeCategoryManagement.updateCategoryForm.$setPristine();

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

                        if(qmeCategoryManagement.categoryParentsAll.length === 0){
                            qmeCategoryManagement.categoryParentsAll.push(category);

                        }else{
                            var isNewCategory =  true;
                            for(var a in qmeCategoryManagement.categoryParentsAll){
                                var currentCategory = qmeCategoryManagement.categoryParentsAll[a];
                                if(category.categoryId === currentCategory.categoryId){
                                    isNewCategory =  false;
                                    break;
                                }
                            }
                            if(isNewCategory){
                                qmeCategoryManagement.categoryParentsAll.push(category);
                            }
                        }

                        if(parentId === 0){
                            parentId = "#";
                        }else{
                            parentId = ""+parentId+"";
                        }
                        var categoryNode = {
                            "id": ""+category.categoryId+"",
                            "parent" : parentId,
                            "text": ""+category.categoryName+"",
                            "icon": false,
                            "children": true,
                            "data": category
                        };

                        nodeData.push(categoryNode);
                    }
                }

                qmeCategoryManagement.categoryParents  = [];
                for(var b in qmeCategoryManagement.categoryParentsAll){
                    qmeCategoryManagement.categoryParents.push(qmeCategoryManagement.categoryParentsAll[b]);
                }

                return nodeData;
            };
        }

})();