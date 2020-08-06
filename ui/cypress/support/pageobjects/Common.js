/// <reference types="Cypress" />

import CommonElements from '../elements/CommonElements';

class Common {

  static checarLocation(url) {
    cy.location('pathname').should('eq', url);
  }

  static menuEhVisivel() {
    cy.get(CommonElements.menu).should('be.be.visible');
  }

  static qtdMenuItems(qtd) {
    cy.get(CommonElements.menuItens).its('length').should('eq', qtd);
  }

  static menuClick(item) {
    cy.get(CommonElements.menuItemText).contains(item).then($el => {
        cy.wrap($el).parentsUntil('a').click();
    });
  }

  static loading() {
    cy.get(CommonElements.loader).should('be.visible');
    cy.waitUntil(() => cy.get(CommonElements.loader));
    cy.get(CommonElements.loader).should('not.be.visible');
  }

  static errorAlert() {
    cy.get(CommonElements.globalAlert).should('be.visible');
    cy.waitUntil(() => cy.get(CommonElements.globalAlert));
    cy.get(CommonElements.globalAlert).should('not.be.visible');
  }
}

export default Common;
