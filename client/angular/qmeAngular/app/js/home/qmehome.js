(function () {
    'use strict';

    ngQMe
        .controller('qmeHomeCtrl', QMeHomeController);

    QMeHomeController.$inject = ['$state','qmeFlashService','qmeUserSession'];

    function QMeHomeController($state,qmeFlashService,qmeUserSession) {

        var qmeHome = this;

        qmeHome.userdetails = function(){
            return qmeUserSession.username();
        }


    }

})();
