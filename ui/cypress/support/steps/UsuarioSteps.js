import { Given, When, Then } from 'cypress-cucumber-preprocessor/steps';

import ListaUsuarioPage from '../pageobjects/ListaUsuarioPage';
import NovoVisualizarUsuarioPage from '../pageobjects/NovoVisualizarUsuarioPage';
import Common from '../pageobjects/Common';

Given('que estou na tela de lista de usuários', () => {
  ListaUsuarioPage.acessar();
});

Given('que estou na tela de novo usuário', () => {
  NovoVisualizarUsuarioPage.acessarNovo();
});

When('eu acesso a tela de novo usuário', () => {
  NovoVisualizarUsuarioPage.acessarNovo();
});

When('eu acesso a tela de visualizar o usuário {int}', (id) => {
  NovoVisualizarUsuarioPage.acessar(id);
});

When('eu clico no botão novo usuário', () => {
  ListaUsuarioPage.clicarBotaoNovoUsuario();
});

When('eu clico no botão para visualizar o usuário {int}', (id) => {
  ListaUsuarioPage.clicarVisualizar(id);
});

Then('a lista de usuários será mostrada', function() {
  ListaUsuarioPage.tabelaTemResultados();
});

Then('eu sou redirecionado para a tela de lista de usuários', () => {
  Common.checarLocation("/usuarios");
});

Then('o alerta de sucesso de usuário criado será mostrado', () => {
  ListaUsuarioPage.alertaSucesso();
});
