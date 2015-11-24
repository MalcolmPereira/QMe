(function () {

    'use strict';

    describe('Unit: QMe Confirm Dialog Directive', function () {

        var qmeConfirm,compile,rootScope,qmeConfirmCtrl;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($compile, $rootScope, $controller) {
            compile = $compile;
            rootScope = $rootScope.$new();
            qmeConfirmCtrl  = $controller('qmeConfirmCtrl', {
                $scope: rootScope
            });
        }));

        it('Should have a QMe Confirm Dialog Directive', function() {
            expect(compile).toBeDefined();
            expect(rootScope).toBeDefined();
            qmeConfirm = compile("<qme-confirm header-message='Delete' body-message='Delete' function-call='mockdelete' function-param='1'></qme-confirm>")(rootScope);
            rootScope.$digest();
            expect(qmeConfirm.html()).toContain("qmeCofirmModalLabel");
        });

    });

})();