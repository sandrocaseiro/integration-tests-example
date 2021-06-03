/// <reference types="Cypress" />

import { Before } from 'cypress-cucumber-preprocessor/steps';

Before(() => {
  if (Cypress.env('E2E') == 'Y') {
    cy.request({
      url: '/v1/resetdb',
      headers: {
        Authorization: 'teste'
      }
    });

    return;
  }

  cy.server({
    delay: 2000
  });

  cy.route({
    url: '/v1/usuarios',
    method: 'GET',
    status: 200,
    response: 'fixture:lista-usuarios.json'
  }).as('lista-usuarios')

  cy.route({
    url: '/v1/cep/01451001',
    method: 'GET',
    status: 200,
    response: 'fixture:cep-ok.json'
  }).as('cep');

  cy.route({
    url: '/v1/cep/99999999',
    method: 'GET',
    status: 404,
    response: 'fixture:cep-not-found.json'
  }).as('cep');

  cy.route({
    url: '/v1/cep/(?!\b01451001\b|\b99999999\b)$',
    method: 'GET',
    status: 500,
    response: 'fixture:cep-invalid.json'
  }).as('cep');

  cy.route({
    url: '/v1/usuarios/1',
    method: 'GET',
    status: 200,
    response: 'fixture:usuario.json'
  }).as('usuario');

  cy.route({
    url: '/v1/usuarios/(?!1$)\\d+$',
    method: 'GET',
    status: 404,
    response: 'fixture:usuario-not-found.json'
  }).as('usuario');

  cy.route({
    url: '/v1/usuarios',
    method: 'POST',
    status: 201,
    response: 'fixture:criar-usuario.json'
  }).as('criar-usuario');
});
