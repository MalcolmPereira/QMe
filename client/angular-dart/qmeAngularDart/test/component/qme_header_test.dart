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
          ..bind(QMeErrorComponent)
          ..bind(QMeMainComponent)
          ..bind(ResourceResolverConfig, toValue: new ResourceResolverConfig.resolveRelativeUrls(false))
        );
        return loadTemplates(
            [
              'component/qme_header.html',
              'component/qme_error.html',
              'component/qme_main.html'
            ]
        );
      });

      it("Check for Default Components", compileComponent(html(), scope(), (shadowRoot) {

          print(shadowRoot.querySelector(""));

          //expect(shadowRoot.query("input[type=agenda-item]")).toBeNull();
      }));

    });

  });
}
