class CommonElements {
  static menu = '[data-cy=menu]';

  static menuItens = CommonElements.menu + ' [data-cy=menu-item]';

  static menuItemText = CommonElements.menuItens + ' div + div span';

  static globalAlert = '[data-cy=global-alert]';

  static loader = '[data-cy=loader]';
}

export default CommonElements;
