/// <reference types="Cypress" />

import { Given, When, Then } from 'cypress-cucumber-preprocessor/steps';

import ListaUsuarioElements from '../elements/ListaUsuarioElements';

const url = Cypress.config("baseUrl");

Given('que estou na tela de lista de usuários', () => {
  cy.visit(url + 'usuarios');
});

Given('que estou na tela de novo usuário', () => {
  cy.visit(`${url}usuarios/novo`);
});

When('eu acesso a tela de novo usuário', () => {
  cy.visit(`${url}usuarios/novo`);
});

When('eu acesso a tela de visualizar o usuário {int}', (id) => {
  cy.visit(`${url}usuarios/${id}`);
});

When('eu clico no botão novo usuário', () => {
  cy.get(ListaUsuarioElements.botaoNovo).click();
});

When('eu clico no botão para visualizar o usuário {int}', (id) => {
  cy.get(ListaUsuarioElements.visualizar(id)).click();
});

Then('a lista de usuários será mostrada', function() {
  cy.get(ListaUsuarioElements.tabelaItem)
      .should('be.visible');
});

Then('eu sou redirecionado para a tela de lista de usuários', () => {
  cy.location('pathname').should('eq', '/usuarios');
});

Then('o alerta de sucesso de usuário criado será mostrado', () => {
  cy.get(ListaUsuarioElements.alertaSucesso).should('be.visible');
  cy.waitUntil(() => cy.get(ListaUsuarioElements.alertaSucesso));
  cy.get(ListaUsuarioElements.alertaSucesso).should('not.be.visible');
});
