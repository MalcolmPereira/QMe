(function () {

    'use strict';

    ngQMe

        .controller('qmeCategoryManagementCtrl', QMeCategoryManagementController);

        QMeCategoryManagementController.$inject = ['$state','qmeFlashService','qmeCategoryService'];

        function QMeCategoryManagementController($state,qmeFlashService,qmeCategoryService) {

            var qmeCategoryManagement = this;

            qmeCategoryManagement.listCategories = function(treecallback, parentId){

                console.log("got treecallback",treecallback)

                console.log("got parentId",parentId)

                qmeCategoryService.listCategoryByParent(parentId)
                    .then(
                        function(res){
                            treecallback(qmeCategoryManagement.processJsTreeData(res));

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

            qmeCategoryManagement.processJsTreeData = function(categorylist){
                var nodeData = [];
                for (var key in categorylist) {
                    var category = categorylist[key];
                    if (category.hasOwnProperty('categoryId')){

                        var categoryNode = {
                            "id": ""+category.categoryId+"",
                            "parent" : "#",
                            "text": ""+category.categoryName+"",
                            "children": true
                        };

                        nodeData.push(categoryNode);
                    }
                }
                return nodeData;
            }

        }

})();