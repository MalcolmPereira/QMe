(function () {

    'use strict';

    ngQMe

        .controller('qmeQuestionManagementCtrl', QMeQuestionManagementController);

        QMeQuestionManagementController.$inject = ['$state','qmeFlashService','qmeUserService'];

        function QMeQuestionManagementController($state,qmeFlashService,qmeUserService) {

            var qmeQuestionManagement = this;

        }

})();