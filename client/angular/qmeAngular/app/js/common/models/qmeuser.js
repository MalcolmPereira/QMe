(function () {
    qmeApp.factory('qmeUserSession', function () {

        var qmeUserSession = {};

        var _sessionid = null;
        var _userid    = null;
        var _username  = null;
        var _userrole  = null;

        qmeUserSession.create = function (sessionid, userid , username, userrole) {
            _sessionid = sessionid;
            _userid = userid ;
            _username = username;
            _userrole = userrole;
        };
        qmeUserSession.destroy = function () {
            _sessionid = null;
            _userid    = null;
            _username  = null;
            _userrole  = null;
        };

        qmeUserSession.sessionid = function () {
            return _sessionid
        }

        qmeUserSession.userid = function () {
            return _userid
        }

        qmeUserSession.username = function () {
            return _username
        }

        qmeUserSession.userrole = function () {
            return _userrole
        }

        return qmeUserSession;
    })
})();
