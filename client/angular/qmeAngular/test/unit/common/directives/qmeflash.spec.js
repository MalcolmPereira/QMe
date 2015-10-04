(function () {

    'use strict';

    describe('Unit: QMe Flash Directive', function () {

        var qmeFlash,compile,rootScope;;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($compile, $rootScope) {
            compile = $compile;
            rootScope = $rootScope;
        }));

        it('Should have a QMe Flash Directive', function() {
            expect(compile).toBeDefined();
            expect(rootScope).toBeDefined();
            qmeFlash = compile("<qme-flash></qme-flash>")(rootScope)
            rootScope.$digest();
            expect(qmeFlash.html()).toContain('<div class="container-fluid">');
        });
    });

})();