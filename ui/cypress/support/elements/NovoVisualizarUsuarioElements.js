class NovoVisualizarUsuarioElements {
  static form = '[data-cy=usuario-form]';

  static formGroup = (campo) => `${NovoVisualizarUsuarioElements.form} [data-cy=usuario-${campo}]`

  static input = (campo) => `${NovoVisualizarUsuarioElements.formGroup(campo)} input`;

  static helper = (campo) => `${NovoVisualizarUsuarioElements.formGroup(campo)} [data-cy=input-error-msg]`;

  static botaoSalvar = `${NovoVisualizarUsuarioElements.form} [data-cy=usuario-salvar]`;
}

export default NovoVisualizarUsuarioElements;
