import { Given, When, Then } from 'cypress-cucumber-preprocessor/steps';

import NovoVisualizarUsuarioPage from '../pageobjects/NovoVisualizarUsuarioPage';

When('eu coloco o focus no campo {string}', (campo) => {
  NovoVisualizarUsuarioPage.campoFocus(campo);
});

When('tiro o focus do campo {string}', (campo) => {
  NovoVisualizarUsuarioPage.campoUnFocus(campo);
});

When('eu digito o valor {string} no campo {string}', (valor, campo) => {
  NovoVisualizarUsuarioPage.campoDigitar(campo, valor);
});

Then('os dados do usuário será mostrado', () => {
  NovoVisualizarUsuarioPage.formVisivel();
});

Then('o formulário de cadastro de usuário deve ser mostrado', () => {
  NovoVisualizarUsuarioPage.formVisivel();
});

Then('o campo {string} do usuário terá o valor {string}', (campo, valor) => {
  NovoVisualizarUsuarioPage.valorCampo(campo, valor);
});

Then('o campo {string} terá uma mensagem de erro contendo {string}', (campo, texto) => {
  NovoVisualizarUsuarioPage.campoHelperContemTexto(campo, texto);
});

Then('o botão salvar usuário deve estar visível', () => {
  NovoVisualizarUsuarioPage.botaoSalvarVisivel();
});

Then('o botão salvar usuário não deve estar visível', () => {
  NovoVisualizarUsuarioPage.botaoSalvarNaoVisivel();
});

Then('o campo {string} estará com o status de erro', (campo) => {
  NovoVisualizarUsuarioPage.campoTemErro(campo);
});

Then('eu clico no botão salvar usuário', () => {
  NovoVisualizarUsuarioPage.clicarBotaoSalvar();
});
