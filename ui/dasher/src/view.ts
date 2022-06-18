import { h } from 'snabbdom';
import { VNode } from 'snabbdom/vnode';

import { DasherCtrl } from './dasher';
import links from './links';
import { view as langsView } from './langs';
import { view as soundView } from './sound';
import { view as backgroundView } from './background';
import { view as themeView } from './theme';
import { view as pieceView } from './piece';
import { view as notationView } from './notation';
import { spinner } from './util';

export function loading(): VNode {
  return h('div#dasher_app.dropdown', h('div.initiating', spinner()));
}

export function loaded(ctrl: DasherCtrl): VNode {
  let content: VNode | undefined;
  switch (ctrl.mode()) {
    case 'langs':
      content = langsView(ctrl.subs.langs);
      break;
    case 'sound':
      content = soundView(ctrl.subs.sound);
      break;
    case 'background':
      content = backgroundView(ctrl.subs.background);
      break;
    case 'theme':
      content = themeView(ctrl.subs.theme);
      break;
    case 'piece':
      content = pieceView(ctrl.subs.piece);
      break;
    case 'notation':
      content = notationView(ctrl.subs.notation);
      break;
    default:
      content = links(ctrl);
  }
  return h('div#dasher_app.dropdown', content);
}
