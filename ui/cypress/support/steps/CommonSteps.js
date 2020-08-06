/// <reference types="Cypress" />

import { When, Then } from 'cypress-cucumber-preprocessor/steps';
import CommonElements from '../elements/CommonElements';

When('eu clico no menu {string}', (item) => {
  cy.get(CommonElements.menuItemText).contains(item).then($el => {
    cy.wrap($el).parentsUntil('a').click();
  });
});

Then('devo ver o menu', () => {
  cy.get(CommonElements.menu).should('be.be.visible');
});

Then('o menu deve conter {int} items', (qtd) => {
  cy.get(CommonElements.menuItens).its('length').should('eq', qtd);
});

Then('devo ser redirecionado para {string}', (url) => {
  cy.location('pathname').should('eq', url);
});

Then('o mostrador de progresso será mostrado', () => {
  cy.get(CommonElements.loader).should('be.visible');
  cy.waitUntil(() => cy.get(CommonElements.loader));
  cy.get(CommonElements.loader).should('not.be.visible');
});

Then('o alerta de erro será mostrado', () => {
  cy.get(CommonElements.globalAlert).should('be.visible');
  cy.waitUntil(() => cy.get(CommonElements.globalAlert));
  cy.get(CommonElements.globalAlert).should('not.be.visible');
});
