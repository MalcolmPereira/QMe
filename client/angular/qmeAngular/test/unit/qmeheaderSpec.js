(function () {
    'use strict';

    describe('QMe Header Controller', function() {

        var scope, ctrl;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope, $controller) {
            scope = $rootScope.$new();
            ctrl  = $controller('qmeHeader', {$scope: scope});
        }));

        it('Should have a qmeHeader controller', function() {
            expect(ctrl).toBeDefined();
        });

        describe('QMeHeader Defaults', function(){
            it('Ensure user is not signed - signedIn is false', function() {
                expect(ctrl.signedIn ).toBe(false);
            });
            it('Ensure user is not registering - isRegistering  is false', function() {
                expect(ctrl.isRegistering ).toBe(false);
            });
            it('Ensure user is not resetting password  - isResetingPassword is false', function() {
                expect(ctrl.isResetingPassword ).toBe(false);
            });
            it('Ensure user email is empty - userEmail is empty ', function() {
                expect(ctrl.userEmail ).toBe('');
            });
            it('Ensure user password is empty - userPassword is empty ', function() {
                expect(ctrl.userPassword ).toBe('');
            });
        });

    });

})();