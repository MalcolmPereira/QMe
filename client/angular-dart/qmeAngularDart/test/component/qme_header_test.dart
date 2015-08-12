part of qme_test;

testHeaderComponent() {

  describe("[HeaderComponent]", () {

    beforeEach(setUpInjector);

    afterEach(tearDownInjector);

    describe("[Default Header Mode]", () {

      html() => '<qme-header></qme-header>';

      scope() => {};

      beforeEach(() {

        module((Module _) => _
          ..bind(TestBed)
          ..bind(QMeHeaderComponent)
        );
      });

      beforeEach(loadTemplates(["component/qme_header.html"]));

      it("Check for Default Components", compileComponent(html(), scope(), (shadowRoot) {
          expect(shadowRoot.querySelector("#signInButton")).toBeNotNull();

          print(shadowRoot.querySelector("#signInButton"));

          //expect(shadowRoot.query("input[type=agenda-item]")).toBeNull();
      }));
    });

  });
}
