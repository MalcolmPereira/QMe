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
            qmeUser.create(
                    'session1234',
                    'someauthtoken',
                    '1234' ,
                    'someuser',
                    'some',
                    'user',
                    'someuser@some.com',
                    'admin');
            expect(qmeUser).toBeDefined();
            expect(qmeUser.sessionid()).toBe('session1234');
            expect(qmeUser.authtoken()).toBe('someauthtoken');
            expect(qmeUser.userid()).toBe('1234');
            expect(qmeUser.username()).toBe('someuser');
            expect(qmeUser.userrole()).toBe('admin');
        });

        it('Should have destroyed qmeUser model', function() {
            qmeUser.create(
                    'session1234',
                    'someauthtoken',
                    '1234' ,
                    'someuser',
                    'some',
                    'user',
                    'someuser@some.com',
                    'admin');
            expect(qmeUser).toBeDefined();
            expect(qmeUser.sessionid()).toBe('session1234');
            expect(qmeUser.authtoken()).toBe('someauthtoken');
            expect(qmeUser.userid()).toBe('1234');
            expect(qmeUser.username()).toBe('someuser');
            expect(qmeUser.userrole()).toBe('admin');

            qmeUser.destroy();
            expect(qmeUser).toBeDefined();
            expect(qmeUser.sessionid()).toBe(null);
            expect(qmeUser.authtoken()).toBe(null);
            expect(qmeUser.userid()).toBe(null);
            expect(qmeUser.username()).toBe(null);
            expect(qmeUser.userrole()).toBe(null);
        });

    });

})();

