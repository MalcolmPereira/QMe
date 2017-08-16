(function () {

    'use strict';

    ngQMe

        .directive('qmeSelectQuestion', function (){
            return {
                restrict: 'E',
                controller: 'qmeQuestionManagementCtrl',
                controllerAs: 'qmeQuestionManagement',
                templateUrl: 'js/admin/question/qmequestionselection.tmpl.html',
                scope: {
                    selectedCategoryid: '='
                }
            };
        })

})();