import { h } from 'snabbdom';
import { VNode } from 'snabbdom/vnode';

import { Redraw, Open, bind, header } from './util';

type Theme = string;

export interface ThemeData {
  current: Theme;
  list: Theme[];
}

export interface ThemeCtrl {
  data: ThemeData;
  trans: Trans;
  set(t: Theme): void;
  open: Open;
}

export function ctrl(data: ThemeData, trans: Trans, redraw: Redraw, open: Open): ThemeCtrl {
  return {
    trans,
    data,
    set(t: Theme) {
      data.current = t;
      applyTheme(t, data.list);
      $.post('/pref/theme', {
        theme: t,
      }).fail(() => window.lishogi.announce({ msg: 'Failed to save theme preference' }));
      redraw();
    },
    open,
  };
}

export function view(ctrl: ThemeCtrl): VNode {
  return h('div.sub.theme', [
    header(ctrl.trans.noarg('boardTheme'), () => ctrl.open('links')),
    h('div.list', ctrl.data.list.map(themeView(ctrl.data.current, ctrl.set))),
  ]);
}

function themeView(current: Theme, set: (t: Theme) => void) {
  return (t: Theme) =>
    h(
      'a',
      {
        hook: bind('click', () => set(t)),
        attrs: { title: t },
        class: { active: current === t },
      },
      [h('span.' + t)]
    );
}

function applyTheme(t: Theme, list: Theme[]) {
  $('body').removeClass(list.join(' ')).addClass(t);
}
