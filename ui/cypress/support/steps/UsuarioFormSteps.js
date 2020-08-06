/// <reference types="Cypress" />

import { When, Then } from 'cypress-cucumber-preprocessor/steps';

import NovoVisualizarUsuarioElements from '../elements/NovoVisualizarUsuarioElements';

When('eu coloco o focus no campo {string}', (campo) => {
  cy.get(NovoVisualizarUsuarioElements.input(campo))
    .focus();
});

When('tiro o focus do campo {string}', (campo) => {
  cy.get(NovoVisualizarUsuarioElements.input(campo))
    .blur();
});

When('eu digito o valor {string} no campo {string}', (valor, campo) => {
  cy.get(NovoVisualizarUsuarioElements.input(campo)).type(valor);
});

Then('os dados do usuário será mostrado', () => {
  cy.get(NovoVisualizarUsuarioElements.form).should('be.visible');
});

Then('o formulário de cadastro de usuário deve ser mostrado', () => {
  cy.get(NovoVisualizarUsuarioElements.form).should('be.visible');
});

Then('o campo {string} do usuário terá o valor {string}', (campo, valor) => {
  cy.get(NovoVisualizarUsuarioElements.input(campo))
    .should('be.visible')
    .invoke('val')
    .should('eq', valor);
});

Then('o campo {string} terá uma mensagem de erro contendo {string}', (campo, texto) => {
  cy.get(NovoVisualizarUsuarioElements.helper(campo))
    .contains(texto);
});

Then('o botão salvar usuário deve estar visível', () => {
  cy.get(NovoVisualizarUsuarioElements.botaoSalvar).should('be.visible');
});

Then('o botão salvar usuário não deve estar visível', () => {
  cy.get(NovoVisualizarUsuarioElements.botaoSalvar).should('not.be.visible');
});

Then('o campo {string} estará com o status de erro', (campo) => {
  cy.get(NovoVisualizarUsuarioElements.formGroup(campo))
    .find('.Mui-error')
    .should('exist');
});

Then('eu clico no botão salvar usuário', () => {
  cy.get(NovoVisualizarUsuarioElements.botaoSalvar).click();
});

Then('o formulário de cadastro de usuário é submetido', () => {
  cy.wait('@criar-usuario');
  cy.get('@criar-usuario').its('request.body').should('deep.equal', {
    id: '',
    nome: 'usuario4',
    email: 'usuario4@mail.com'
  });
});
