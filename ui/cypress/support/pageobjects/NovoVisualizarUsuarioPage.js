/// <reference types="Cypress" />

import NovoVisualizarUsuarioElements from '../elements/NovoVisualizarUsuarioElements';

const url = Cypress.config("baseUrl");

class NovoVisualizarUsuarioPage {
  static acessar(id) {
    cy.visit(`${url}usuarios/${id}`);
  }

  static acessarNovo() {
    cy.visit(`${url}usuarios/novo`);
  }

  static formVisivel() {
    cy.get(NovoVisualizarUsuarioElements.form).should('be.visible');
  }

  static campoFocus(campo) {
    cy.get(NovoVisualizarUsuarioElements.input(campo))
      .focus();
  }

  static campoUnFocus(campo) {
    cy.get(NovoVisualizarUsuarioElements.input(campo))
      .blur();
  }

  static valorCampo(campo, valor) {
    cy.get(NovoVisualizarUsuarioElements.input(campo))
      .should('be.visible')
      .invoke('val')
      .should('eq', valor);
  }

  static campoTemErro(campo) {
    cy.get(NovoVisualizarUsuarioElements.formGroup(campo))
      .find('.Mui-error')
      .should('exist');
  }

  static campoHelperContemTexto(campo, texto) {
    cy.get(NovoVisualizarUsuarioElements.helper(campo))
      .contains(texto);
  }

  static campoDigitar(campo, valor) {
    cy.get(NovoVisualizarUsuarioElements.input(campo))
      .type(valor);
  }

  static clicarBotaoSalvar() {
    cy.get(NovoVisualizarUsuarioElements.botaoSalvar).click();
  }

  static botaoSalvarVisivel() {
    cy.get(NovoVisualizarUsuarioElements.botaoSalvar).should('be.visible');
  }

  static botaoSalvarNaoVisivel() {
    cy.get(NovoVisualizarUsuarioElements.botaoSalvar).should('not.be.visible');
  }
}

export default NovoVisualizarUsuarioPage;
