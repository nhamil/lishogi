import { h } from 'snabbdom';
import { VNode } from 'snabbdom/vnode';

import { DasherCtrl, Mode } from './dasher';
import { view as pingView } from './ping';
import { bind } from './util';

export default function (ctrl: DasherCtrl): VNode {
  const d = ctrl.data,
    trans = ctrl.trans,
    noarg = trans.noarg;

  function userLinks(): VNode | null {
    return d.user
      ? h('div.links', [
          h(
            'a.user-link.online.text.is-green',
            linkCfg(`/@/${d.user.name}`, d.user.patron ? '' : ''),
            noarg('profile')
          ),

          d.inbox ? h('a.text', linkCfg('/inbox', 'e'), noarg('inbox')) : null,

          h(
            'a.text',
            linkCfg('/account/preferences/game-display', '%', ctrl.opts.playing ? { target: '_blank' } : undefined),
            noarg('preferences')
          ),

          !d.coach ? null : h('a.text', linkCfg('/coach/edit', ':'), 'Coach manager'),

          !d.streamer ? null : h('a.text', linkCfg('/streamer/edit', ''), noarg('streamerManager')),

          h(
            'form.logout',
            {
              attrs: { method: 'post', action: '/logout' },
            },
            [
              h(
                'button.text',
                {
                  attrs: {
                    type: 'submit',
                    'data-icon': 'w',
                  },
                },
                noarg('logOut')
              ),
            ]
          ),
        ])
      : null;
  }

  const langs = h('a.sub', modeCfg(ctrl, 'langs'), noarg('language'));

  const sound = h('a.sub', modeCfg(ctrl, 'sound'), noarg('sound'));

  const background = h('a.sub', modeCfg(ctrl, 'background'), noarg('background'));

  const theme = h('a.sub', modeCfg(ctrl, 'theme'), noarg('boardTheme'));

  const piece = h('a.sub', modeCfg(ctrl, 'piece'), noarg('pieceSet'));

  const notation = h('a.sub', modeCfg(ctrl, 'notation'), noarg('notationSystem'));

  const zenToggle = ctrl.opts.playing
    ? h('div.zen.selector', [
        h(
          'a.text',
          {
            attrs: {
              'data-icon': 'K',
              title: 'Keyboard: z',
            },
            hook: bind('click', () => window.lishogi.pubsub.emit('zen')),
          },
          noarg('zenMode')
        ),
      ])
    : null;

  return h('div', [
    userLinks(),
    h('div.subs', [langs, sound, background, theme, piece, notation, zenToggle]),
    pingView(ctrl.ping),
  ]);
}

function linkCfg(href: string, icon: string, more: any = undefined): any {
  const cfg: any = {
    attrs: {
      href,
      'data-icon': icon,
    },
  };
  if (more) for (let i in more) cfg.attrs[i] = more[i];
  return cfg;
}

function modeCfg(ctrl: DasherCtrl, m: Mode): any {
  return {
    hook: bind('click', () => ctrl.setMode(m)),
    attrs: { 'data-icon': 'H' },
  };
}
