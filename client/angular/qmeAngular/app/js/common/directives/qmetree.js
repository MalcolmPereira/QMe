(function () {

	"use strict";

    ngQMe

        .directive('qmeTree', function() {

            return {
                restrict: 'EA',

                scope: {
                    treeData: '=',
                },

                link: function(scope, element, attributes) {
                    $(function() {

                        var config = {
                            "core" : {
                                "data" : [
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
                            },
                            "plugins" : [ "wholerow"]
                        };

                        this.tree = $(element).jstree(config);
                    });
                },
            };
        })

})();