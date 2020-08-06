/// <reference types="Cypress" />

import { Given, When, Then } from 'cypress-cucumber-preprocessor/steps';
import CepElements from '../elements/CepElements';

const url = Cypress.config("baseUrl");

Given('que estou na tela de CEP', () => {
  cy.visit(url + 'cep');
});

When('eu digito o CEP {word}', (cep) => {
  cy.get(CepElements.inputBusca).type(cep);
});

When('eu clico em buscar', () => {
  cy.get(CepElements.botaoBusca).click();
});

When('eu digito o CEP {word} e envio', (cep) => {
  cy.get(CepElements.inputBusca).type(`${cep}{enter}`);
});

Then('a tabela de resultado de CEP será mostrada', () => {
  cy.get(CepElements.tabelaResultado).should('be.visible');
});
