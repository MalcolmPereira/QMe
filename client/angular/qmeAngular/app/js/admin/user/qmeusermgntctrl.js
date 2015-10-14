(function () {

    'use strict';

    ngQMe

        .controller('qmeUserManagementCtrl', QMeUserManagementController);

        QMeUserManagementController.$inject = ['$state','qmeFlashService','qmeUserService'];

        function QMeUserManagementController($state,qmeFlashService,qmeUserService) {

            var qmeUserManagement = this;

        }

})();