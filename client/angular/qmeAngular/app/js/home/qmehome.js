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

        qmeHome.data = [
                    'Simple root node',
                    {
                        'text' : 'Root node 2',
                        'state' : {
                            'opened' : true,
                            'selected' : true
                        },
                        'children' : [
                            { 'text' : 'Child 1' },
                            'Child 2'
                        ]
                    }
        ]


    }

})();
