(function () {

    'use strict';

    ngQMe
        .service('qmeUserSession', function (QME_CONSTANTS) {

        var qmeUserSession = this;

        var _authtoken      = undefined;
        var _userid         = undefined;
        var _username       = undefined;
        var _userfirstname  = undefined;
        var _userlastname   = undefined;
        var _useremail      = undefined;
        var _userrole       = undefined;
        var _userlastlogin  = undefined;
        var _updating       = undefined;


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
            _authtoken = undefined;
            _userid    = undefined;
            _username  = undefined;
            _userfirstname = undefined;
            _userlastname = undefined;
            _useremail = undefined;
            _userlastlogin = undefined;
            _userrole  = undefined;
        };

        qmeUserSession.authtoken = function () {
           return _authtoken;
        };

        qmeUserSession.userid = function () {
           return _userid;
        };

        qmeUserSession.username = function () {
           return _username;
        };

        qmeUserSession.userfirstname= function () {
           return _userfirstname;
        };

        qmeUserSession.setUserFirstname= function (userfirstname) {
            _userfirstname = userfirstname;
        };

        qmeUserSession.userlastname= function () {
                return _userlastname;
        };

        qmeUserSession.setUserLastname= function (userlastname) {
             _userlastname = userlastname;
        };

        qmeUserSession.useremail= function () {
           return _useremail;
        };

        qmeUserSession.userrole = function () {
           return _userrole;
        };

        qmeUserSession.userlastlogin = function () {
           return _userlastlogin;
        };

        qmeUserSession.setUpdating= function () {
            _updating = true;
        };

        qmeUserSession.doneUpdating= function () {
                _updating = false;
        };

        qmeUserSession.isSignedIn = function(){
            return(
                qmeUserSession.userid() &&
                qmeUserSession.userid() != null &&
                qmeUserSession.authtoken() &&
                qmeUserSession.authtoken() != null
            );
        };

        qmeUserSession.isAdmin = function(){
            return (
                qmeUserSession.isSignedIn() &&
                qmeUserSession.userrole() &&
                qmeUserSession.userrole().length > 0 &&
                qmeUserSession.userrole().indexOf(QME_CONSTANTS.adminrole) > -1
            );
        };

        qmeUserSession.isUpdating = function(){
            return _updating;
        };
    })
})();
