(function () {

    'use strict';

    describe('Unit: QMe Tree Directive', function () {

        var qmeTree,compile,rootScope,qmeTreeCtrl;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($compile, $rootScope, $controller) {
            compile = $compile;
            rootScope = $rootScope.$new();
            qmeTreeCtrl  = $controller('qmeTreeCtrl', {
                $scope: rootScope
            });
        }));

        it('Should have a QMe Tree Directive', function() {
            expect(compile).toBeDefined();
            expect(rootScope).toBeDefined();
            qmeTree = compile("<qme-tree tree-function='mocktreefunction'  select-function='mockselectfunction'></qme-tree>")(rootScope);
            rootScope.$digest();
            expect(qmeTree.html()).toContain("qmeTreeId");
        });

    });

})();