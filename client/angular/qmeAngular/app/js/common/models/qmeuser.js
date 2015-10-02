(function () {

    'use strict';

    ngQMe
        .factory('qmeUserSession', function () {

        var qmeUserSession = {};

        var _authtoken = null;
        var _userid    = null;
        var _username  = null;
        var _userfirstname = null;
        var _userlastname = null;
        var _useremail = null;
        var _userrole  = null;
        var _userlastlogin  = null;

        qmeUserSession.create = function (
                authtoken,
                userid ,
                username,
                userfirstname,
                userlastname,
                useremail,
                userlastlogin,
                userrole)
        {
            _authtoken = authtoken;
            _userid = userid ;
            _username = username;
            _userfirstname = userfirstname;
            _userlastname = userlastname;
            _useremail = useremail;
            _userlastlogin = userlastlogin;
            _userrole = userrole;
        };

        qmeUserSession.destroy = function () {
            _authtoken = null;
            _userid    = null;
            _username  = null;
            _userfirstname = null;
            _userlastname = null;
            _useremail = null;
            _userlastlogin = null;
            _userrole  = null;
        };

        qmeUserSession.authtoken = function () {
           return _authtoken
        };

        qmeUserSession.userid = function () {
           return _userid
        };

        qmeUserSession.username = function () {
           return _username
        };

        qmeUserSession.userfirstname= function () {
           return _userfirstname
        };

        qmeUserSession.userlastname= function () {
           return _userlastname
        };

        qmeUserSession.useremail= function () {
           return _useremail
        };

        qmeUserSession.userrole = function () {
           return _userrole
        };

        qmeUserSession.userlastlogin = function () {
           return _userlastlogin
        };

        return qmeUserSession;
    })
})();
