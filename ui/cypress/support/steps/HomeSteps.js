/// <reference types="Cypress" />

import { Given, Then } from 'cypress-cucumber-preprocessor/steps';
import HomeElements from '../elements/HomeElements';

const url = Cypress.config("baseUrl");

Given('que acesso ao site', () => {
  cy.visit(url);
});

Then('devo ver a mensagem da home', () => {
  cy.get(HomeElements.msg).should('contain', 'projeto de exemplo');
});
