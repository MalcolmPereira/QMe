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
                    'someauthtoken',
                    '1234' ,
                    'someuser',
                    'firstname',
                    'lastname',
                    'someuser@some.com',
                    '2015-10-02T14:27:10',
                    ['admin']);
            expect(qmeUser).toBeDefined();
            expect(qmeUser.authtoken()).toBe('someauthtoken');
            expect(qmeUser.userid()).toBe('1234');
            expect(qmeUser.username()).toBe('someuser');
            expect(qmeUser.userfirstname()).toBe('firstname');
            expect(qmeUser.userlastname()).toBe('lastname');
            expect(qmeUser.useremail()).toBe('someuser@some.com');
            expect(qmeUser.userlastlogin()).toBe('2015-10-02T14:27:10');
            expect(qmeUser.userrole()).toContain('admin');
        });

        it('Should have create new qmeUser model for user with multiple roles ', function() {
            qmeUser.create(
                'someauthtoken',
                '1234' ,
                'someuser',
                'firstname',
                'lastname',
                'someuser@some.com',
                '2015-10-02T14:27:10',
                ['admin','user','reviewer']);
            expect(qmeUser).toBeDefined();
            expect(qmeUser.authtoken()).toBe('someauthtoken');
            expect(qmeUser.userid()).toBe('1234');
            expect(qmeUser.username()).toBe('someuser');
            expect(qmeUser.userfirstname()).toBe('firstname');
            expect(qmeUser.userlastname()).toBe('lastname');
            expect(qmeUser.useremail()).toBe('someuser@some.com');
            expect(qmeUser.userlastlogin()).toBe('2015-10-02T14:27:10');
            expect(qmeUser.userrole()).toContain('admin');
            expect(qmeUser.userrole()).toContain('user');
            expect(qmeUser.userrole()).toContain('reviewer');
        });



        it('Should have destroyed qmeUser model', function() {
            qmeUser.create(
                'someauthtoken',
                '1234' ,
                'someuser',
                'firstname',
                'lastname',
                'someuser@some.com',
                '2015-10-02T14:27:10',
                ['admin']);
            expect(qmeUser.authtoken()).toBe('someauthtoken');
            expect(qmeUser.userid()).toBe('1234');
            expect(qmeUser.username()).toBe('someuser');
            expect(qmeUser.userfirstname()).toBe('firstname');
            expect(qmeUser.userlastname()).toBe('lastname');
            expect(qmeUser.useremail()).toBe('someuser@some.com');
            expect(qmeUser.userlastlogin()).toBe('2015-10-02T14:27:10');
            expect(qmeUser.userrole()).toContain('admin');


            qmeUser.destroy();
            expect(qmeUser).toBeDefined();
            expect(qmeUser.authtoken()).toBe(undefined);
            expect(qmeUser.userid()).toBe(undefined);
            expect(qmeUser.username()).toBe(undefined);
            expect(qmeUser.userfirstname()).toBe(undefined);
            expect(qmeUser.userlastname()).toBe(undefined);
            expect(qmeUser.useremail()).toBe(undefined);
            expect(qmeUser.userlastlogin()).toBe(undefined);
            expect(qmeUser.userrole()).toBe(undefined);

        });



        it('Should have valid admin roles for signed in admin user', function() {
            qmeUser.create(
                'someauthtoken',
                '1234' ,
                'someuser',
                'firstname',
                'lastname',
                'someuser@some.com',
                '2015-10-02T14:27:10',
                ['ADMIN']);
            expect(qmeUser.authtoken()).toBe('someauthtoken');
            expect(qmeUser.userid()).toBe('1234');
            expect(qmeUser.username()).toBe('someuser');
            expect(qmeUser.userfirstname()).toBe('firstname');
            expect(qmeUser.userlastname()).toBe('lastname');
            expect(qmeUser.useremail()).toBe('someuser@some.com');
            expect(qmeUser.userlastlogin()).toBe('2015-10-02T14:27:10');
            expect(qmeUser.userrole()).toContain('ADMIN');
            expect(qmeUser.isSignedIn()).toBe(true);
            expect(qmeUser.isAdmin()).toBe(true);

            qmeUser.destroy();
            expect(qmeUser).toBeDefined();
            expect(qmeUser.authtoken()).toBe(undefined);
            expect(qmeUser.userid()).toBe(undefined);
            expect(qmeUser.username()).toBe(undefined);
            expect(qmeUser.userfirstname()).toBe(undefined);
            expect(qmeUser.userlastname()).toBe(undefined);
            expect(qmeUser.useremail()).toBe(undefined);
            expect(qmeUser.userlastlogin()).toBe(undefined);
            expect(qmeUser.userrole()).toBe(undefined);
            expect(qmeUser.isSignedIn()).toBe(undefined);
            expect(qmeUser.isAdmin()).toBe(undefined);
        });


    });

})();

