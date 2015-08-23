(function () {
    'use strict';

    describe('Unit: QMe App Configuration', function () {

        var qmeContants;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function(_QME_CONSTANTS_) {
            qmeContants = _QME_CONSTANTS_;
        }));

        it('Unit: Check Valid QMe Constants', function () {
            expect(qmeContants.success).toBe('success');
            expect(qmeContants.error).toBe('error');
            expect(qmeContants.authendpoint).toBe('/login');
            expect(qmeContants.adminrole).toBe('admin');
        });

    });

})();