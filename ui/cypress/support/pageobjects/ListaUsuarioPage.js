/// <reference types="Cypress" />

import ListaUsuarioElements from '../elements/ListaUsuarioElements';

const url = Cypress.config("baseUrl");

class ListaUsuarioPage {
  static acessar() {
    cy.visit(url + 'usuarios');
  }

  static tabelaTemResultados() {
    cy.get(ListaUsuarioElements.tabelaItem)
      .should('be.visible');
  }

  static clicarBotaoNovoUsuario() {
    cy.get(ListaUsuarioElements.botaoNovo).click();
  }

  static clicarVisualizar(id) {
    cy.get(ListaUsuarioElements.visualizar(id)).click();
  }

  static alertaSucesso() {
    cy.get(ListaUsuarioElements.alertaSucesso).should('be.visible');
    cy.waitUntil(() => cy.get(ListaUsuarioElements.alertaSucesso));
    cy.get(ListaUsuarioElements.alertaSucesso).should('not.be.visible');
  }
}

export default ListaUsuarioPage;
