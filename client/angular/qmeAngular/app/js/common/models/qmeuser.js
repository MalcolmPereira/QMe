(function () {

    'use strict';

    ngQMe
        .factory('qmeUserSession', function () {

        var qmeUserSession = {};

        var _sessionid = null;
        var _authtoken = null;

        var _userid    = null;
        var _username  = null;
        var _userfirstname = null;
        var _userlastname = null;
        var _useremail = null;
        var _userrole  = null;

        qmeUserSession.create = function (
                sessionid,
                authtoken,
                userid ,
                username,
                userfirstname,
                userlastname,
                useremail,
                userrole)
        {
            _sessionid = sessionid;
            _authtoken = authtoken;
            _userid = userid ;
            _username = username;
            _userfirstname = userfirstname;
            _userlastname = userlastname;
            _useremail = useremail;
            _userrole = userrole;
        };
        qmeUserSession.destroy = function () {
            _sessionid = null;
            _authtoken = null;
            _userid    = null;
            _username  = null;
            _userfirstname = null;
            _userlastname = null;
            _useremail = null;
            _userrole  = null;
        };

        qmeUserSession.sessionid = function () {
            return _sessionid
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

        return qmeUserSession;
    })
})();
