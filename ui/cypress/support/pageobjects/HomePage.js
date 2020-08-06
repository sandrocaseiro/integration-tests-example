/// <reference types="Cypress" />

import HomeElements from '../elements/HomeElements';

const url = Cypress.config("baseUrl");

class HomePage {
  static acessarHome() {
    cy.visit(url);
  }

  static checarMensagem() {
    cy.get(HomeElements.msg).should('contain', 'projeto de exemplo');
  }
}

export default HomePage;
