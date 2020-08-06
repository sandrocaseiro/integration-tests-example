class ListaUsuarioElements {
  static tabela = '[data-cy=usuario-tabela]';

  static tabelaItem = ListaUsuarioElements.tabela + ' [data-cy=usuario-tabela-item]';

  static visualizar = (id) => `${ListaUsuarioElements.tabelaItem} [data-cy=usuario-visualizar-${id}]`;

  static botaoNovo = '[data-cy=usuario-novo]';

  static alertaSucesso = '[data-cy=alerta-sucesso]'
}

export default ListaUsuarioElements;
