part of qme_test;

testHeaderComponent() {

  describe("[HeaderComponent]", () {

    setUpAngular(
        injectables: [TestBed,QMeHeaderComponent],
        templates: ["component/qme_header.html"]);

    html() => '<qme-header></qme-header>';

    scope() => {
                "signedIn": false,
                "isRegistering" : false,
                "isResetingPassword":false
              };

    it("should display the given address", compileComponent(
        html(),
        scope(),
        (qmeHeader, tb){
          print(qmeHeader.querySelector("#loginName"));
          expect(qmeHeader.querySelector("#emailField")).toBeNotNull();
          expect(qmeHeader.querySelector("#passwordField")).toBeNotNull();
          expect(qmeHeader.querySelector("#signInButton")).toBeNotNull();
          expect(qmeHeader.querySelector("#registerButton")).toBeNotNull();
          expect(qmeHeader.querySelector("#forgotButton")).toBeNotNull();
          expect(qmeHeader.querySelector("#loginName")).toBeNotNull();

        }
    ));
});
}
