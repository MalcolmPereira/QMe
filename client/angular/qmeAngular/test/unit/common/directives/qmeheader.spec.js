(function () {

    'use strict';

    describe('Unit: QMe Header Directive', function () {

        var qmeHeader,compile,rootScope;;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($compile, $rootScope) {
            compile = $compile;
            rootScope = $rootScope;
        }));

        it('Should have a QMe Header Directive', function() {
            expect(compile).toBeDefined();
            expect(rootScope).toBeDefined();
            qmeHeader = compile("<qme-header></qme-header>")(rootScope)
            rootScope.$digest();
            expect(qmeHeader.html()).toContain("<span>QMe Application</span>");
        });
    });


})();