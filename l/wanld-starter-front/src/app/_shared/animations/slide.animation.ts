import { animate, state, style, transition, trigger, keyframes } from '@angular/animations';

export const translateRightIn = trigger('translateRightIn', [
    transition('void=>*', [
        style({ transform: 'translateX(100%)' }),
        animate('0.4s ease-in')
    ]),
    transition('*=>void', [
        style({ transform: 'translateX(0%)' }),
        animate('0.4s ease-in', style({ transform: 'translateX(100%)' })
        ),
    ])
]);


export const translateX = trigger('translateX', [
    state('true', style({ transform: 'translateX(0)', maxWidth: '*' })),
    state('false', style({ transform: 'translateX(0)', maxWidth: '*' })),
    transition('true=>false',
        [style({ transform: 'translateX(100%)' }), animate('0.3s ease-in')]

    ),
    transition('false=>true',
        [style({ transform: 'translateX(-100%)' }), animate('0.3s ease-in')]

    )
]);

