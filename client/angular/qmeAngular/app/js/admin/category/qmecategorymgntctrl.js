(function () {

    'use strict';

    ngQMe

        .controller('qmeCategoryManagementCtrl', QMeCategoryManagementController);

        QMeCategoryManagementController.$inject = ['$state','qmeFlashService','qmeUserService'];

        function QMeCategoryManagementController($state,qmeFlashService,qmeUserService) {

            var qmeCategoryManagement = this;

            qmeCategoryManagement.categories;

            qmeCategoryManagement.listCategories = function(){
            }
        }

})();