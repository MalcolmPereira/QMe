(function () {

    'use strict';

    describe('Model: QMe User Model', function() {

        var qmeUser;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function(_qmeUserSession_) {
            qmeUser  = _qmeUserSession_;
        }));

        it('Should have a qmeUser model', function() {
            expect(qmeUser).toBeDefined();
        });

        it('Should have create new qmeUser model', function() {
            qmeUser.create('session1234', '1234' , 'someuser', 'admin');
            expect(qmeUser).toBeDefined();
            expect(qmeUser.sessionid()).toBe('session1234');
            expect(qmeUser.userid()).toBe('1234');
            expect(qmeUser.username()).toBe('someuser');
            expect(qmeUser.userrole()).toBe('admin');
        });
    });

})();
