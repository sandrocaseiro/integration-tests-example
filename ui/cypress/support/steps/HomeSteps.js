import { Given, When, Then } from 'cypress-cucumber-preprocessor/steps';

import HomePage from '../pageobjects/HomePage';

Given('que acesso ao site', () => {
  HomePage.acessarHome();
});

Then('devo ver a mensagem da home', () => {
  HomePage.checarMensagem();
});
