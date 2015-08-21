(function () {
    qmeApp.factory('qmeUserSession', function () {

        var qmeUserSession = this;

        qmeUserSession.create = function (sessionIdVal, userIdVal, userNameVal, userRoleVal) {
            this.id = sessionIdVal;
            this.userId = userIdVal;
            this.userName = userNameVal;
            this.userRole = userRoleVal;
        };
        qmeUserSession.destroy = function () {
            this.id = null;
            this.userId = null;
            this.userName = null;
            this.userRole = null;
        };

        return qmeUserSession;
    })
})();
