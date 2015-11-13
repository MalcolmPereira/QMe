(function () {
    'use strict';

    ngQMe
        .controller('qmeHomeCtrl', QMeHomeController);

    QMeHomeController.$inject = ['$state','qmeFlashService','qmeUserSession'];

    function QMeHomeController($state,qmeFlashService,qmeUserSession) {

        var qmeHome = this;

        qmeHome.userdetails = function(){
            return qmeUserSession.username();
        };

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
        ];

        qmeHome.nodeData = function(parentid){
            console.log("got parentid",parentid) ;

            if(parentid === 0){
                return [
                    {
                        "id": "1",
                        "parent" : "#",
                        "text": "Category 1",
                        "children": true
                    },
                    {
                        "id": "2",
                        "parent" : "#",
                        "text": "Category 2",
                        "children": true
                    },
                    {
                        "id": "3",
                        "parent" : "#",
                        "text": "Category 3",
                        "children": true
                    }
                ];

            }else{

            }

        };

    }

})();
