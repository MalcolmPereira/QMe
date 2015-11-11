(function () {

    'use strict';

    ngQMe

        .controller('qmeCategoryManagementCtrl', QMeCategoryManagementController);

        QMeCategoryManagementController.$inject = ['$state','qmeFlashService','qmeCategoryService'];

        function QMeCategoryManagementController($state,qmeFlashService,qmeCategoryService) {

            var qmeCategoryManagement = this;

            qmeCategoryManagement.categories;

            var test = {
                "core" : {
                    "animation": 0,
                    "check_callback": true,
                    "themes": {"stripes": true},
                    "default": {
                        "valid_children": ["default", "file"]
                    },
                    "file": {
                        "icon": "glyphicon glyphicon-file",
                        "valid_children": []
                    },
                },
                "plugins" : [
                    "contextmenu", "dnd", "search",
                    "state", "types", "wholerow"
                ]
            };

            qmeCategoryManagement.listCategories = function(){

                $('#categoryTreeContainer').jstree(test);

                qmeCategoryService.listCategoryByParent(0)
                    .then(
                        function(res){
                            qmeCategoryManagement.categories = res;
                        },
                        function(error){
                            if(error && error.status && error.status == 403) {
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else {
                                qmeFlashService.Error("Oops.....Error from service getting category lists, please retry in some time.");
                            }
                        }
                    );
            }
        }

})();