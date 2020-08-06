import { Given, When, Then, Before } from 'cypress-cucumber-preprocessor/steps';

import Common from '../pageobjects/Common';

When('eu clico no menu {string}', (item) => {
  Common.menuClick(item);
});

Then('devo ver o menu', () => {
  Common.menuEhVisivel();
});

Then('o menu deve conter {int} items', (qtd) =>{
  Common.qtdMenuItems(qtd);
});

Then('devo ser redirecionado para {string}', (url) => {
  Common.checarLocation(url);
});

Then('o mostrador de progresso será mostrado', () => {
  Common.loading();
});

Then('o alerta de erro será mostrado', () => {
  Common.errorAlert();
});
