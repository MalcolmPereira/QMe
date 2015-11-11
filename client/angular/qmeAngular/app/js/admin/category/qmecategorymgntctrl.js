(function () {

    'use strict';

    ngQMe

        .controller('qmeCategoryManagementCtrl', QMeCategoryManagementController);

        QMeCategoryManagementController.$inject = ['$state','qmeFlashService','qmeCategoryService'];

        function QMeCategoryManagementController($state,qmeFlashService,qmeCategoryService) {

            var qmeCategoryManagement = this;

            qmeCategoryManagement.categories;

            qmeCategoryManagement.listCategories = function(){

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