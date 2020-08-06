import { Given, When, Then } from 'cypress-cucumber-preprocessor/steps';

import CepPage from '../pageobjects/CepPage';

Given('que estou na tela de CEP', () => {
  CepPage.acessar();
});

When('eu digito o CEP {word}', (cep) => {
  CepPage.digitar(cep);
});

When('eu clico em buscar', () => {
  CepPage.enviar();
});

When('eu digito o CEP {word} e envio', (cep) => {
  CepPage.digitarEEnviar(cep);
});

Then('a tabela de resultado de CEP será mostrada', () => {
  CepPage.tabelaResultadoVisivel();
});
