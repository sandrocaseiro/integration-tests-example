/// <reference types="Cypress" />

import CommonElements from '../elements/CommonElements';
import CepElements from '../elements/CepElements';

const url = Cypress.config("baseUrl");

class CepPage {
  static acessar() {
    cy.visit(url + 'cep');
  }

  static digitarEEnviar(texto) {
    cy.get(CepElements.inputBusca).type(`${texto}{enter}`);
  }

  static digitar(texto) {
    cy.get(CepElements.inputBusca).type(texto);
  }

  static enviar() {
    cy.get(CepElements.botaoBusca).click();
  }

  static tabelaResultadoVisivel() {
    cy.get(CepElements.tabelaResultado).should('be.visible');
  }
}

export default CepPage;
